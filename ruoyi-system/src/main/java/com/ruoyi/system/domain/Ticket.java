package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 车票对象 ticket
 * 
 * @author 王
 * @date 2023-01-16
 */
public class Ticket extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 员工工号 */
    @Excel(name = "员工工号")
    private String userName;

    /** 出差类型 */
    @Excel(name = "出差类型")
    private String travelType;

    /** 出发地点 */
    @Excel(name = "出发地点")
    private String placeBegin;

    /** 到达地点 */
    @Excel(name = "到达地点")
    private String placeEnd;

    /** 出发时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Excel(name = "出发时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date timeBegin;

    /** 到达时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Excel(name = "到达时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date timeEnd;

    /** 交通工具 */
    @Excel(name = "交通工具")
    private String vehicle;

    /** 报销总金额 */
    @Excel(name = "报销总金额")
    private BigDecimal totalAmount;

    /** 出差说明 */
    @Excel(name = "出差说明")
    private String explain;

    /** 出差类型 */
    @Excel(name = "出差类型")
    private String type;

    /** 是否有图片 */
    @Excel(name = "是否有图片")
    private String imgTag;

    /** 车票照片 */
    @Excel(name = "车票照片")
    private String img;

    /** 差旅关联信息 */
    @Excel(name = "差旅关联信息")
    private String travelId;

    /** 车票金额 */
    @Excel(name = "车票金额")
    private BigDecimal amount;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
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
    public void setPlaceBegin(String placeBegin) 
    {
        this.placeBegin = placeBegin;
    }

    public String getPlaceBegin() 
    {
        return placeBegin;
    }
    public void setPlaceEnd(String placeEnd) 
    {
        this.placeEnd = placeEnd;
    }

    public String getPlaceEnd() 
    {
        return placeEnd;
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
    public void setVehicle(String vehicle) 
    {
        this.vehicle = vehicle;
    }

    public String getVehicle() 
    {
        return vehicle;
    }
    public void setTotalAmount(BigDecimal totalAmount)
    {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalAmount()
    {
        return totalAmount;
    }
    public void setExplain(String explain) 
    {
        this.explain = explain;
    }

    public String getExplain() 
    {
        return explain;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setImgTag(String imgTag) 
    {
        this.imgTag = imgTag;
    }

    public String getImgTag() 
    {
        return imgTag;
    }
    public void setImg(String img) 
    {
        this.img = img;
    }

    public String getImg() 
    {
        return img;
    }
    public void setTravelId(String travelId)
    {
        this.travelId = travelId;
    }

    public String getTravelId()
    {
        return travelId;
    }
    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }

    public void setTravelType(String travelType)
    {
        this.travelType = travelType;
    }

    public String getTravelType()
    {
        return travelType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userName", getUserName())
            .append("placeBegin", getPlaceBegin())
            .append("placeEnd", getPlaceEnd())
            .append("timeBegin", getTimeBegin())
            .append("timeEnd", getTimeEnd())
            .append("vehicle", getVehicle())
            .append("totalAmount", getTotalAmount())
            .append("explain", getExplain())
            .append("type", getType())
            .append("imgTag", getImgTag())
            .append("img", getImg())
            .append("travelId", getTravelId())
            .append("amount", getAmount())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
