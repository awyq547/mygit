package com.ruoyi.web.controller.finance;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Ticket;
import com.ruoyi.system.service.ITicketService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 车票Controller
 * 
 * @author 王
 * @date 2023-01-16
 */
@RestController
@RequestMapping("/system/ticket")
public class TicketController extends BaseController
{
    @Autowired
    private ITicketService ticketService;

    /**
     * 查询车票列表
     */
    @GetMapping("/list")
    public TableDataInfo list(Ticket ticket)
    {
        startPage();
        List<Ticket> list = ticketService.selectTicketList(ticket);
        return getDataTable(list);
    }

    /**
     * 导出车票列表
     */
    @Log(title = "车票", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Ticket ticket)
    {
        List<Ticket> list = ticketService.selectTicketList(ticket);
        ExcelUtil<Ticket> util = new ExcelUtil<Ticket>(Ticket.class);
        util.exportExcel(response, list, "车票数据");
    }

    /**
     * 获取车票详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(ticketService.selectTicketById(id));
    }

    /**
     * 新增车票
     */
    @Log(title = "车票", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Ticket ticket)
    {
        return toAjax(ticketService.insertTicket(ticket));
    }

    /**
     * 修改车票
     */
    @Log(title = "车票", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Ticket ticket)
    {
        return toAjax(ticketService.updateTicket(ticket));
    }

    /**
     * 删除车票
     */
    @Log(title = "车票", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)

    {
        return toAjax(ticketService.deleteTicketByIds(ids));
    }

    /**
     * 校验车票
     */
    @PostMapping("/checkTicket")
    public JSONObject checkTicket(@RequestBody Long[] ids){
        JSONObject jsonObject = new JSONObject();
        if("".equals(ids) || ids == null){
            return null;
        }
        List<Ticket> list = new ArrayList<>();//选中的车票
        //获取选择的车票
        for(Long i:ids){
            Ticket ticket = ticketService.selectTicketById(i);
            list.add(ticket);
        }
        //对车票进行校验
        if(ticketService.checkTicket(list)){

            BigDecimal amount = new BigDecimal(0);//报销总金额
            double days = 0;//天数
            Date begindate = new Date();//出发时间
            Date enddate = new Date();//到达时间

            for(Ticket ticket:list){
                amount = amount.add(ticket.getAmount());
                if("济南".equals(ticket.getPlaceBegin().trim())){
                    begindate = ticket.getTimeBegin();
                }
                if("济南".equals(ticket.getPlaceEnd().trim())){
                    enddate = ticket.getTimeEnd();
                }
            }
            days = getSubDate(begindate,enddate);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH");
            if(Integer.parseInt(simpleDateFormat.format(begindate)) > 12){
                days-=0.5;
            }
            if(Integer.parseInt(simpleDateFormat.format(enddate)) < 12){
                days-=0.5;
            }

            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            jsonObject.put("list",list);
            jsonObject.put("amount",amount);
            jsonObject.put("days",days);
            jsonObject.put("amountSubsidy",new BigDecimal(days).multiply(new BigDecimal(200)));
            jsonObject.put("begindate",f.format(begindate));
            jsonObject.put("enddate",f.format(enddate));
            jsonObject.put("now",f.format(new Date()));
            return jsonObject;
        }else{
            return null;
        }

    }

    //日期
    public double getSubDate(Date date1,Date date2){
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2) //同一年
        {
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++)
            {
                if(i%4==0 && i%100!=0 || i%400==0) //闰年
                {
                    timeDistance += 366;
                }
                else //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2-day1)+1 ;
        }
        else //不同年
        {
            System.out.println("判断day2 - day1 : " + (day2-day1));
            return day2-day1 +1;
        }

    }


    /*获取获取登录用户昵称*/
    @GetMapping("/getname")
    public String getCheckList(){
       return SecurityUtils.getLoginUser().getUser().getNickName();
    }

}
