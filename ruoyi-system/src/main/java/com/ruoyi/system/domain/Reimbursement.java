package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * reimbursement对象 reimbursement
 * 
 * @author 王
 * @date 2023-01-16
 */
public class Reimbursement extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;

    /** 工号 */
    @Excel(name = "工号")
    private String userName;

    /** 出发时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Excel(name = "出发时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date timeBegin;

    /** 到达时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Excel(name = "到达时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date timeEnd;

    /** 出差天数 */
    @Excel(name = "出差天数")
    private BigDecimal days;

    /** 出差说明 */
    @Excel(name = "出差说明")
    private String remake;

    /** 报销时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Excel(name = "报销时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date timeReimbursement;

    /** 补助金额 */
    @Excel(name = "补助金额")
    private BigDecimal amountSubsidy;

    /** 报销总额 */
    @Excel(name = "报销总额")
    private BigDecimal amountAll;

    /** 报销进度 */
    @Excel(name = "报销进度")
    private Integer approval;

    public Integer getApproval() {
        return approval;
    }

    public void setApproval(Integer approval) {
        this.approval = approval;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setTimeBegin(Date timeBegin) 
    {
        this.timeBegin = timeBegin;
    }

    public Date getTimeBegin() 
    {
        return timeBegin;
    }
    public void setTimeEnd(Date timeEnd) 
    {
        this.timeEnd = timeEnd;
    }

    public Date getTimeEnd() 
    {
        return timeEnd;
    }
    public void setDays(BigDecimal days) 
    {
        this.days = days;
    }

    public BigDecimal getDays() 
    {
        return days;
    }
    public void setRemake(String remake) 
    {
        this.remake = remake;
    }

    public String getRemake() 
    {
        return remake;
    }
    public void setTimeReimbursement(Date timeReimbursement) 
    {
        this.timeReimbursement = timeReimbursement;
    }

    public Date getTimeReimbursement() 
    {
        return timeReimbursement;
    }
    public void setAmountSubsidy(BigDecimal amountSubsidy)
    {
        this.amountSubsidy = amountSubsidy;
    }

    public BigDecimal getAmountSubsidy()
    {
        return amountSubsidy;
    }
    public void setAmountAll(BigDecimal amountAll)
    {
        this.amountAll = amountAll;
    }

    public BigDecimal getAmountAll()
    {
        return amountAll;
    }

    public List<Ticket> getList() {
        return list;
    }

    public void setList(List<Ticket> list) {
        this.list = list;
    }

    public List<Ticket> list;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userName", getUserName())
            .append("timeBegin", getTimeBegin())
            .append("timeEnd", getTimeEnd())
            .append("days", getDays())
            .append("remake", getRemake())
            .append("timeReimbursement", getTimeReimbursement())
            .append("amountSubsidy", getAmountSubsidy())
            .append("amountAll", getAmountAll())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
