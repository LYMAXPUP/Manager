package com.model;

import com.alibaba.excel.annotation.ExcelProperty;

public class Asset {
    private String accountName;
    private String voucher;
    private String accountTime;
    private int financeState;
    private int assetId;

    @ExcelProperty(index = 0)
    private String assetName;

    @ExcelProperty(index = 5)
    private String type;

    @ExcelProperty(index = 1)
    private String department;

    @ExcelProperty(index = 2)
    private String model;

    @ExcelProperty(index = 3)
    private double price;

    @ExcelProperty(index = 4)
    private String buyTime;
    private String keeperName;
    private String userName;
    private int assetState;
    private String text;

    public Asset() {
    }

    public Asset(String accountName, String voucher, String accountTime, int financeState, int assetId, String assetName, String type, String department, String model, double price, String buyTime, String keeperName, String userName, int assetState, String text) {
        this.accountName = accountName;
        this.voucher = voucher;
        this.accountTime = accountTime;
        this.financeState = financeState;
        this.assetId = assetId;
        this.assetName = assetName;
        this.type = type;
        this.department = department;
        this.model = model;
        this.price = price;
        this.buyTime = buyTime;
        this.keeperName = keeperName;
        this.userName = userName;
        this.assetState = assetState;
        this.text = text;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

    public String getAccountTime() {
        return accountTime;
    }

    public void setAccountTime(String accountTime) {
        this.accountTime = accountTime;
    }

    public int getFinanceState() {
        return financeState;
    }

    public void setFinanceState(int financeState) {
        this.financeState = financeState;
    }

    public int getAssetId() {
        return assetId;
    }

    public void setAssetId(int assetId) {
        this.assetId = assetId;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(String buyTime) {
        this.buyTime = buyTime;
    }

    public String getKeeperName() {
        return keeperName;
    }

    public void setKeeperName(String keeperName) {
        this.keeperName = keeperName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAssetState() {
        return assetState;
    }

    public void setAssetState(int assetState) {
        this.assetState = assetState;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "accountName='" + accountName + '\'' +
                ", voucher='" + voucher + '\'' +
                ", accountTime='" + accountTime + '\'' +
                ", financeState=" + financeState +
                ", assetId=" + assetId +
                ", assetName='" + assetName + '\'' +
                ", type='" + type + '\'' +
                ", department='" + department + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", buyTime='" + buyTime + '\'' +
                ", keeperName='" + keeperName + '\'' +
                ", userName='" + userName + '\'' +
                ", assetState=" + assetState +
                ", text='" + text + '\'' +
                '}';
    }
}
