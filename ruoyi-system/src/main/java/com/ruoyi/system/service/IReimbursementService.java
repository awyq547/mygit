package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Reimbursement;

/**
 * reimbursementService接口
 * 
 * @author 王
 * @date 2023-01-16
 */
public interface IReimbursementService 
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
     * 批量删除reimbursement
     * 
     * @param ids 需要删除的reimbursement主键集合
     * @return 结果
     */
    public int deleteReimbursementByIds(String[] ids);

    /**
     * 删除reimbursement信息
     * 
     * @param id reimbursement主键
     * @return 结果
     */
    public int deleteReimbursementById(String id);

    public int selectCommonBackCount(Reimbursement app);
}
