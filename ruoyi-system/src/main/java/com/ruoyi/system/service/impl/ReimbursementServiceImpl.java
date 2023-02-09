package com.ruoyi.system.service.impl;

import java.util.List;
import java.util.UUID;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.Ticket;
import com.ruoyi.system.mapper.TicketMapper;
import com.ruoyi.system.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ReimbursementMapper;
import com.ruoyi.system.domain.Reimbursement;
import com.ruoyi.system.service.IReimbursementService;

/**
 * reimbursementService业务层处理
 * 
 * @author 王
 * @date 2023-01-16
 */
@Service
public class ReimbursementServiceImpl implements IReimbursementService 
{
    @Autowired
    private ReimbursementMapper reimbursementMapper;
    @Autowired
    private TicketMapper ticketMapper;



    /**
     * 查询reimbursement
     * 
     * @param id reimbursement主键
     * @return reimbursement
     */
    @Override
    public Reimbursement selectReimbursementById(String id)
    {
        return reimbursementMapper.selectReimbursementById(id);
    }

    /**
     * 查询reimbursement列表
     * 
     * @param reimbursement reimbursement
     * @return reimbursement
     */
    @Override
    public List<Reimbursement> selectReimbursementList(Reimbursement reimbursement)
    {
        return reimbursementMapper.selectReimbursementList(reimbursement);
    }

    /**
     * 新增reimbursement
     * 
     * @param reimbursement reimbursement
     * @return 结果
     */
    @Override
    public int insertReimbursement(Reimbursement reimbursement)
    {
        reimbursement.setCreateTime(DateUtils.getNowDate());
        reimbursement.setId(UUID.randomUUID().toString());
        List<Ticket> list = reimbursement.getList();
        for(Ticket t:list){
            Ticket ticket = new Ticket();
            ticket.setId(t.getId());
            ticket.setTravelId(reimbursement.getId());
            ticketMapper.updateTicket(ticket);
        }
        return reimbursementMapper.insertReimbursement(reimbursement);
    }

    /**
     * 修改reimbursement
     * 
     * @param reimbursement reimbursement
     * @return 结果
     */
    @Override
    public int updateReimbursement(Reimbursement reimbursement)
    {
        reimbursement.setUpdateTime(DateUtils.getNowDate());
        return reimbursementMapper.updateReimbursement(reimbursement);
    }

    /**
     * 批量删除reimbursement
     * 
     * @param ids 需要删除的reimbursement主键
     * @return 结果
     */
    @Override
    public int deleteReimbursementByIds(String[] ids)
    {
        return reimbursementMapper.deleteReimbursementByIds(ids);
    }

    /**
     * 删除reimbursement信息
     * 
     * @param id reimbursement主键
     * @return 结果
     */
    @Override
    public int deleteReimbursementById(String id)
    {
        return reimbursementMapper.deleteReimbursementById(id);
    }

    @Override
    public int selectCommonBackCount(Reimbursement app)
    {
        return reimbursementMapper.selectCommonBackCount(app);
    }
}
