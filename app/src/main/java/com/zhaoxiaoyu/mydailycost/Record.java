package com.zhaoxiaoyu.mydailycost;

import java.util.Date;

/**
 * Created by zhaoxiaoyu on 15/3/19.
 */
public class Record {
    public static final int TYPE_OUTCOME = 0;
    public static final int TYPE_INCOME = 1;

    private int id;
    private double amount;//金额
    private String purpose;//目的
    private String remark;//备注
    private Date date;//账目时间
    private int amountType;//支出:0，收入:1


    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", amount=" + amount +
                ", purpose='" + purpose + '\'' +
                ", remark='" + remark + '\'' +
                ", date=" + date +
                ", amountType=" + amountType +
                '}';
    }

    public int getAmountType() {
        return amountType;
    }

    public void setAmountType(int amountType) {
        this.amountType = amountType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
