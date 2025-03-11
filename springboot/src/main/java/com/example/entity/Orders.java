package com.example.entity;

import java.io.Serializable;

/**
 * 课程订单表
*/
public class Orders implements Serializable {


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /** ID */
    private Integer id;
    private Integer courseId;
    private Double price;


    private String orderId;
    private String time;
    private Integer userId;
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCourseimg() {
        return courseimg;
    }

    public void setCourseimg(String courseimg) {
        this.courseimg = courseimg;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    private String courseimg;
    private String courseName;
}
