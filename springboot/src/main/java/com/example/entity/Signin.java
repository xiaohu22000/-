package com.example.entity;

import java.io.Serializable;

/**
 * 签到表
*/
public class Signin implements Serializable {


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    /** ID */
    private Integer id;
    private Integer userId;
    private String time;
    private String day;


}
