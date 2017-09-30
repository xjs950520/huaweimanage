package com.huawei.bean;

public class SportCondition {

    private int id;
    private int userId;
    private String addDate;
    private String url;
    private int num;
    private String finish_status;
    private int status;

    public SportCondition() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFinish_status() {
        return finish_status;
    }

    public void setFinish_status(String finish_status) {
        this.finish_status = finish_status;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
