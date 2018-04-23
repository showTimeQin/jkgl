package com.showtime.jkgl.model.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "health_scheme")
public class HealthScheme {
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
    private String userFileNo;

    /**
     * 膳食编号[1,2,3]
     */
    @Column(name = "diet_storage_ids")
    private String dietStorageIds;

    /**
     * 数量[1杯,1碗]
     */
    private String quantitys;

    /**
     * 日期
     */
    private Date date;

    /**
     * 1:早餐 2:午餐 3:晚餐
     */
    @Column(name = "eat_time")
    private Byte eatTime;

    /**
     * 顾问主键
     */
    @Column(name = "adviser_id")
    private String adviserId;

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
    public String getUserFileNo() {
        return userFileNo;
    }

    /**
     * 设置用户档案号
     *
     * @param userFileNo 用户档案号
     */
    public void setUserFileNo(String userFileNo) {
        this.userFileNo = userFileNo;
    }

    /**
     * 获取膳食编号[1,2,3]
     *
     * @return diet_storage_ids - 膳食编号[1,2,3]
     */
    public String getDietStorageIds() {
        return dietStorageIds;
    }

    /**
     * 设置膳食编号[1,2,3]
     *
     * @param dietStorageIds 膳食编号[1,2,3]
     */
    public void setDietStorageIds(String dietStorageIds) {
        this.dietStorageIds = dietStorageIds;
    }

    /**
     * 获取数量[1杯,1碗]
     *
     * @return quantitys - 数量[1杯,1碗]
     */
    public String getQuantitys() {
        return quantitys;
    }

    /**
     * 设置数量[1杯,1碗]
     *
     * @param quantitys 数量[1杯,1碗]
     */
    public void setQuantitys(String quantitys) {
        this.quantitys = quantitys;
    }

    /**
     * 获取日期
     *
     * @return date - 日期
     */
    public Date getDate() {
        return date;
    }

    /**
     * 设置日期
     *
     * @param date 日期
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * 获取1:早餐 2:午餐 3:晚餐
     *
     * @return eat_time - 1:早餐 2:午餐 3:晚餐
     */
    public Byte getEatTime() {
        return eatTime;
    }

    /**
     * 设置1:早餐 2:午餐 3:晚餐
     *
     * @param eatTime 1:早餐 2:午餐 3:晚餐
     */
    public void setEatTime(Byte eatTime) {
        this.eatTime = eatTime;
    }

    /**
     * 获取顾问主键
     *
     * @return adviser_id - 顾问主键
     */
    public String getAdviserId() {
        return adviserId;
    }

    /**
     * 设置顾问主键
     *
     * @param adviserId 顾问主键
     */
    public void setAdviserId(String adviserId) {
        this.adviserId = adviserId;
    }
}