package com.showtime.jkgl.model.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "diet_record")
public class DietRecord {
    /**
     * 编码
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 饮食时间
     */
    private String time;

    /**
     * 餐次
     */
    private Date times;

    /**
     * 饮食详细信息
     */
    private String detail;

    /**
     * 档案号
     */
    @Column(name = "file_no")
    private Integer fileNo;

    /**
     * 获取编码
     *
     * @return id - 编码
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置编码
     *
     * @param id 编码
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取饮食时间
     *
     * @return time - 饮食时间
     */
    public String getTime() {
        return time;
    }

    /**
     * 设置饮食时间
     *
     * @param time 饮食时间
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * 获取餐次
     *
     * @return times - 餐次
     */
    public Date getTimes() {
        return times;
    }

    /**
     * 设置餐次
     *
     * @param times 餐次
     */
    public void setTimes(Date times) {
        this.times = times;
    }

    /**
     * 获取饮食详细信息
     *
     * @return detail - 饮食详细信息
     */
    public String getDetail() {
        return detail;
    }

    /**
     * 设置饮食详细信息
     *
     * @param detail 饮食详细信息
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * 获取档案号
     *
     * @return file_no - 档案号
     */
    public Integer getFileNo() {
        return fileNo;
    }

    /**
     * 设置档案号
     *
     * @param fileNo 档案号
     */
    public void setFileNo(Integer fileNo) {
        this.fileNo = fileNo;
    }
}