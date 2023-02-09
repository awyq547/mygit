package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Ticket;

/**
 * 车票Mapper接口
 * 
 * @author 王
 * @date 2023-01-16
 */
public interface TicketMapper 
{
    /**
     * 查询车票
     * 
     * @param id 车票主键
     * @return 车票
     */
    public Ticket selectTicketById(Long id);

    /**
     * 查询车票列表
     * 
     * @param ticket 车票
     * @return 车票集合
     */
    public List<Ticket> selectTicketList(Ticket ticket);

    /**
     * 新增车票
     * 
     * @param ticket 车票
     * @return 结果
     */
    public int insertTicket(Ticket ticket);

    /**
     * 修改车票
     * 
     * @param ticket 车票
     * @return 结果
     */
    public int updateTicket(Ticket ticket);

    /**
     * 删除车票
     * 
     * @param id 车票主键
     * @return 结果
     */
    public int deleteTicketById(Long id);

    /**
     * 批量删除车票
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTicketByIds(Long[] ids);

    public int resetCheckTicket(String id);
}
