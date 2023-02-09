package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Reimbursement;

/**
 * reimbursementMapper接口
 * 
 * @author 王
 * @date 2023-01-16
 */
public interface ReimbursementMapper 
{
    /**
     * 查询reimbursement
     * 
     * @param id reimbursement主键
     * @return reimbursement
     */
    public Reimbursement selectReimbursementById(String id);

    /**
     * 查询reimbursement列表
     * 
     * @param reimbursement reimbursement
     * @return reimbursement集合
     */
    public List<Reimbursement> selectReimbursementList(Reimbursement reimbursement);

    /**
     * 新增reimbursement
     * 
     * @param reimbursement reimbursement
     * @return 结果
     */
    public int insertReimbursement(Reimbursement reimbursement);

    /**
     * 修改reimbursement
     * 
     * @param reimbursement reimbursement
     * @return 结果
     */
    public int updateReimbursement(Reimbursement reimbursement);

    /**
     * 删除reimbursement
     * 
     * @param id reimbursement主键
     * @return 结果
     */
    public int deleteReimbursementById(String id);

    /**
     * 批量删除reimbursement
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteReimbursementByIds(String[] ids);

    public int selectCommonBackCount(Reimbursement app);
}
