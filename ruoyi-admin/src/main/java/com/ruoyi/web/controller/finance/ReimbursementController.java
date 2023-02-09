package com.ruoyi.web.controller.finance;

import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ITicketService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Reimbursement;
import com.ruoyi.system.service.IReimbursementService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * reimbursementController
 * 
 * @author 王
 * @date 2023-01-16
 */
@RestController
@RequestMapping("/system/reimbursement")
public class ReimbursementController extends BaseController
{
    @Autowired
    private IReimbursementService reimbursementService;

    @Autowired
    private ITicketService ticketService;

    @Autowired
    private ISysRoleService roleService;

    /**
     * 查询reimbursement列表
     */
    @GetMapping("/list")
    public TableDataInfo list(Reimbursement reimbursement)
    {
        startPage();
        SysUser sysUser = SecurityUtils.getLoginUser().getUser();
        String role = roleService.selectRolePermissionByUserId(sysUser.getUserId()).toString();
        if("[common]".equals(role)){
            reimbursement.setUserName(sysUser.getUserName());
        }
        if("[manager]".equals(role)){
            reimbursement.setApproval(0);
        }
        if("[finance]".equals(role)){
            reimbursement.setApproval(1);
        }
        if("[boss]".equals(role)){
            reimbursement.setApproval(2);
        }
        List<Reimbursement> list = reimbursementService.selectReimbursementList(reimbursement);
        return getDataTable(list);
    }

    /**
     * 导出reimbursement列表
     */
    @Log(title = "reimbursement", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Reimbursement reimbursement)
    {
        List<Reimbursement> list = reimbursementService.selectReimbursementList(reimbursement);
        ExcelUtil<Reimbursement> util = new ExcelUtil<Reimbursement>(Reimbursement.class);
        util.exportExcel(response, list, "reimbursement数据");
    }

    /**
     * 获取reimbursement详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(reimbursementService.selectReimbursementById(id));
    }

    /**
     * 新增reimbursement
     */
    @Log(title = "reimbursement", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Reimbursement reimbursement)
    {
        return toAjax(reimbursementService.insertReimbursement(reimbursement));
    }

    /**
     * 修改reimbursement
     */
    @Log(title = "reimbursement", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Reimbursement reimbursement)
    {
        return toAjax(reimbursementService.updateReimbursement(reimbursement));
    }

    /**
     * 删除reimbursement
     */
    @Log(title = "reimbursement", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(reimbursementService.deleteReimbursementByIds(ids));
    }

    //驳回
    @PostMapping("/checkNo")
    public AjaxResult checkNo(@RequestBody JSONObject json){
        Reimbursement reimbursement = new Reimbursement();
        reimbursement.setId(json.getString("id"));
        reimbursement.setApproval(Integer.parseInt(json.getString("approval")));
        reimbursement.setRemake(json.getString("value"));
        reimbursementService.updateReimbursement(reimbursement);
        ticketService.resetCheckTicket(json.getString("id").trim());
        return AjaxResult.success();
    }

    @GetMapping("getRole")
    public String getRole(){
        return roleService.selectRolePermissionByUserId(SecurityUtils.getLoginUser().getUser().getUserId()).toString();
    }


    @GetMapping("getmsg")
    public JSONObject getmsg(){
        SysUser sysUser = SecurityUtils.getLoginUser().getUser();
        JSONObject jsonObject = new JSONObject();
        String role = roleService.selectRolePermissionByUserId(sysUser.getUserId()).toString();
        Reimbursement reimbursement = new Reimbursement();
        if("[admin]".equals(role)){
            return null;
        }
        if("[common]".equals(role)){
            reimbursement.setUserName(sysUser.getUserName());
            int count = reimbursementService.selectCommonBackCount(reimbursement);
//            if(reimbursements == null || reimbursements.size() == 0){
//                jsonObject.put("count",0);
//            }else{
//                jsonObject.put("count",reimbursements.size());
//            }
            jsonObject.put("count",count);
            jsonObject.put("isM",false);
        }else{
            if("[manager]".equals(role)){
                reimbursement.setApproval(0);
            }
            if("[finance]".equals(role)){
                reimbursement.setApproval(1);
            }
            if("[boss]".equals(role)){
                reimbursement.setApproval(2);
            }
            List<Reimbursement> reimbursements = reimbursementService.selectReimbursementList(reimbursement);
            if(reimbursements == null || reimbursements.size() == 0){
                jsonObject.put("count",0);
            }else{
                jsonObject.put("count",reimbursements.size());
            }
            jsonObject.put("isM",true);
        }
        return jsonObject;
    }
}
