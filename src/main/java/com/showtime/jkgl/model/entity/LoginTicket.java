package com.showtime.jkgl.model.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "login_ticket")
public class LoginTicket {
    /**
     * 表主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 账户id
     */
    @Column(name = "account_id")
    private Long accountId;

    /**
     * 角色
     */
    private Byte role;

    /**
     * 超时时间
     */
    private Date expired;

    /**
     * 是否有效
     */
    private Byte status;

    /**
     * 票据
     */
    private String ticket;

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
     * 获取账户id
     *
     * @return account_id - 账户id
     */
    public Long getAccountId() {
        return accountId;
    }

    /**
     * 设置账户id
     *
     * @param accountId 账户id
     */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    /**
     * 获取角色
     *
     * @return role - 角色
     */
    public Byte getRole() {
        return role;
    }

    /**
     * 设置角色
     *
     * @param role 角色
     */
    public void setRole(Byte role) {
        this.role = role;
    }

    /**
     * 获取超时时间
     *
     * @return expired - 超时时间
     */
    public Date getExpired() {
        return expired;
    }

    /**
     * 设置超时时间
     *
     * @param expired 超时时间
     */
    public void setExpired(Date expired) {
        this.expired = expired;
    }

    /**
     * 获取是否有效
     *
     * @return status - 是否有效
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置是否有效
     *
     * @param status 是否有效
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取票据
     *
     * @return ticket - 票据
     */
    public String getTicket() {
        return ticket;
    }

    /**
     * 设置票据
     *
     * @param ticket 票据
     */
    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
}