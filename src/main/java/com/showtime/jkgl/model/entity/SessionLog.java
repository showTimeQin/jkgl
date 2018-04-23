package com.showtime.jkgl.model.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "session_log")
public class SessionLog {
    /**
     * 表主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户档案号
     */
    @Column(name = "user_file_no")
    private Long userFileNo;

    /**
     * 发送者
     */
    private Long sender;

    /**
     * 内容
     */
    private String content;

    /**
     * 时间
     */
    private Date time;

    /**
     * 顾问主键
     */
    @Column(name = "adviser_id")
    private Long adviserId;

    /**
     * 获取表主键
     *
     * @return id - 表主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置表主键
     *
     * @param id 表主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户档案号
     *
     * @return user_file_no - 用户档案号
     */
    public Long getUserFileNo() {
        return userFileNo;
    }

    /**
     * 设置用户档案号
     *
     * @param userFileNo 用户档案号
     */
    public void setUserFileNo(Long userFileNo) {
        this.userFileNo = userFileNo;
    }

    /**
     * 获取发送者
     *
     * @return sender - 发送者
     */
    public Long getSender() {
        return sender;
    }

    /**
     * 设置发送者
     *
     * @param sender 发送者
     */
    public void setSender(Long sender) {
        this.sender = sender;
    }

    /**
     * 获取内容
     *
     * @return content - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取时间
     *
     * @return time - 时间
     */
    public Date getTime() {
        return time;
    }

    /**
     * 设置时间
     *
     * @param time 时间
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * 获取顾问主键
     *
     * @return adviser_id - 顾问主键
     */
    public Long getAdviserId() {
        return adviserId;
    }

    /**
     * 设置顾问主键
     *
     * @param adviserId 顾问主键
     */
    public void setAdviserId(Long adviserId) {
        this.adviserId = adviserId;
    }
}