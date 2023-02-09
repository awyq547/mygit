package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.token.TokenService;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TicketMapper;
import com.ruoyi.system.domain.Ticket;
import com.ruoyi.system.service.ITicketService;

/**
 * 车票Service业务层处理
 * 
 * @author 王
 * @date 2023-01-16
 */
@Service
public class TicketServiceImpl implements ITicketService 
{
    @Autowired
    private TicketMapper ticketMapper;


    /**
     * 查询车票
     * 
     * @param id 车票主键
     * @return 车票
     */
    @Override
    public Ticket selectTicketById(Long id)
    {
        return ticketMapper.selectTicketById(id);
    }


    /**
     * 查询车票列表
     * 
     * @param ticket 车票
     * @return 车票
     */
    @Override
    public List<Ticket> selectTicketList(Ticket ticket)
    {

        if(SecurityUtils.isAdmin(SecurityUtils.getUserId())){
            return ticketMapper.selectTicketList(ticket);
        }else {
            ticket.setUserName(SecurityUtils.getUsername());
            return ticketMapper.selectTicketList(ticket);
        }

    }

    /**
     * 新增车票
     * 
     * @param ticket 车票
     * @return 结果
     */
    @Override
    public int insertTicket(Ticket ticket)
    {
        ticket.setCreateTime(DateUtils.getNowDate());
        ticket.setUserName(SecurityUtils.getUsername());
        if(ticket.getImg() == null){
            ticket.setImgTag("0");
        }else {
            ticket.setImgTag("1");
        }
        return ticketMapper.insertTicket(ticket);
    }

    /**
     * 修改车票
     * 
     * @param ticket 车票
     * @return 结果
     */
    @Override
    public int updateTicket(Ticket ticket)
    {
        ticket.setUpdateTime(DateUtils.getNowDate());
        return ticketMapper.updateTicket(ticket);
    }

    /**
     * 批量删除车票
     * 
     * @param ids 需要删除的车票主键
     * @return 结果
     */
    @Override
    public int deleteTicketByIds(Long[] ids)
    {
        return ticketMapper.deleteTicketByIds(ids);
    }

    /**
     * 删除车票信息
     * 
     * @param id 车票主键
     * @return 结果
     */
    @Override
    public int deleteTicketById(Long id)
    {
        return ticketMapper.deleteTicketById(id);
    }

    @Override
    public boolean checkTicket(List<Ticket> list){
        ArrayList<String> str1 = new ArrayList<>();//出发地点
        ArrayList<String> str2 = new ArrayList<>();//返回地点
        int begincount = 0;    //判断济南出现几次
        int endcount = 0;
        for(Ticket ticket:list){
            str1.add(ticket.getPlaceBegin().trim());
            str2.add(ticket.getPlaceEnd().trim());
            if("济南".equals(ticket.getPlaceBegin().trim())){
                begincount++;
            }
            if("济南".equals(ticket.getPlaceEnd().trim())){
                endcount++;
            }
        }
        if(begincount == 0 || begincount >1 || endcount == 0 || endcount >1){
            return false;
        }
        Collections.sort(str1);
        Collections.sort(str2);
        return str1.equals(str2);

    }

    @Override
    public int resetCheckTicket(String id){
        return ticketMapper.resetCheckTicket(id);
    }
}
