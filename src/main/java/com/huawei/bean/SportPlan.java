package com.huawei.bean;

public class SportPlan {
    private int id;
    private int number;//假字段
    private int userId;
    private int sportSortId;
    private String sportSortName;//假字段
    private String url;//假字段，所播放运动项目的地址
    private String planDate;
    private String watchTime;
    private int finishStatus;
    private int status;

    public SportPlan() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSportSortId() {
        return sportSortId;
    }

    public void setSportSortId(int sportSortId) {
        this.sportSortId = sportSortId;
    }

    public String getPlanDate() {
        return planDate;
    }

    public void setPlanDate(String planDate) {
        this.planDate = planDate;
    }

    public String getWatchTime() {
        return watchTime;
    }

    public void setWatchTime(String watchTime) {
        this.watchTime = watchTime;
    }

    public int getFinishStatus() {
        return finishStatus;
    }

    public void setFinishStatus(int finishStatus) {
        this.finishStatus = finishStatus;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getSportSortName() {
        return sportSortName;
    }

    public void setSportSortName(String sportSortName) {
        this.sportSortName = sportSortName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
