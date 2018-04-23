package com.showtime.jkgl.model.entity;

import javax.persistence.*;

public class User {
    /**
     * 档案号
     */
    @Id
    @Column(name = "file_no")
    private Integer fileNo;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String gender;

    /**
     * 手机号
     */
    private String tel;

    /**
     * 民族
     */
    private String nation;

    /**
     * 出生日期
     */
    private String birthday;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 证件类别
     */
    @Column(name = "card_type")
    private String cardType;

    /**
     * 证件号
     */
    @Column(name = "cardID")
    private String cardid;

    /**
     * 家庭电话
     */
    @Column(name = "house_tel")
    private String houseTel;

    /**
     * 建档原因
     */
    @Column(name = "file_reason")
    private String fileReason;

    /**
     * 并发症
     */
    private String complication;

    /**
     * 家庭地址
     */
    @Column(name = "house_address")
    private String houseAddress;

    /**
     * 单位地址
     */
    @Column(name = "unit_address")
    private String unitAddress;

    /**
     * 文化程度
     */
    @Column(name = "edu_background")
    private String eduBackground;

    /**
     * 职业
     */
    private String position;

    /**
     * 婚姻状况
     */
    @Column(name = "marital_status")
    private String maritalStatus;

    /**
     * 常住类型
     */
    @Column(name = "usual_type")
    private String usualType;

    /**
     * 籍贾
     */
    private String birthplace;

    /**
     * 档案状态
     */
    @Column(name = "file_status")
    private String fileStatus;

    /**
     * 顾问
     */
    private Integer adviser;

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

    /**
     * 获取账号
     *
     * @return username - 账号
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置账号
     *
     * @param username 账号
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取性别
     *
     * @return gender - 性别
     */
    public String getGender() {
        return gender;
    }

    /**
     * 设置性别
     *
     * @param gender 性别
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * 获取手机号
     *
     * @return tel - 手机号
     */
    public String getTel() {
        return tel;
    }

    /**
     * 设置手机号
     *
     * @param tel 手机号
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * 获取民族
     *
     * @return nation - 民族
     */
    public String getNation() {
        return nation;
    }

    /**
     * 设置民族
     *
     * @param nation 民族
     */
    public void setNation(String nation) {
        this.nation = nation;
    }

    /**
     * 获取出生日期
     *
     * @return birthday - 出生日期
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * 设置出生日期
     *
     * @param birthday 出生日期
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取证件类别
     *
     * @return card_type - 证件类别
     */
    public String getCardType() {
        return cardType;
    }

    /**
     * 设置证件类别
     *
     * @param cardType 证件类别
     */
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    /**
     * 获取证件号
     *
     * @return cardID - 证件号
     */
    public String getCardid() {
        return cardid;
    }

    /**
     * 设置证件号
     *
     * @param cardid 证件号
     */
    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    /**
     * 获取家庭电话
     *
     * @return house_tel - 家庭电话
     */
    public String getHouseTel() {
        return houseTel;
    }

    /**
     * 设置家庭电话
     *
     * @param houseTel 家庭电话
     */
    public void setHouseTel(String houseTel) {
        this.houseTel = houseTel;
    }

    /**
     * 获取建档原因
     *
     * @return file_reason - 建档原因
     */
    public String getFileReason() {
        return fileReason;
    }

    /**
     * 设置建档原因
     *
     * @param fileReason 建档原因
     */
    public void setFileReason(String fileReason) {
        this.fileReason = fileReason;
    }

    /**
     * 获取并发症
     *
     * @return complication - 并发症
     */
    public String getComplication() {
        return complication;
    }

    /**
     * 设置并发症
     *
     * @param complication 并发症
     */
    public void setComplication(String complication) {
        this.complication = complication;
    }

    /**
     * 获取家庭地址
     *
     * @return house_address - 家庭地址
     */
    public String getHouseAddress() {
        return houseAddress;
    }

    /**
     * 设置家庭地址
     *
     * @param houseAddress 家庭地址
     */
    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    /**
     * 获取单位地址
     *
     * @return unit_address - 单位地址
     */
    public String getUnitAddress() {
        return unitAddress;
    }

    /**
     * 设置单位地址
     *
     * @param unitAddress 单位地址
     */
    public void setUnitAddress(String unitAddress) {
        this.unitAddress = unitAddress;
    }

    /**
     * 获取文化程度
     *
     * @return edu_background - 文化程度
     */
    public String getEduBackground() {
        return eduBackground;
    }

    /**
     * 设置文化程度
     *
     * @param eduBackground 文化程度
     */
    public void setEduBackground(String eduBackground) {
        this.eduBackground = eduBackground;
    }

    /**
     * 获取职业
     *
     * @return position - 职业
     */
    public String getPosition() {
        return position;
    }

    /**
     * 设置职业
     *
     * @param position 职业
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * 获取婚姻状况
     *
     * @return marital_status - 婚姻状况
     */
    public String getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * 设置婚姻状况
     *
     * @param maritalStatus 婚姻状况
     */
    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    /**
     * 获取常住类型
     *
     * @return usual_type - 常住类型
     */
    public String getUsualType() {
        return usualType;
    }

    /**
     * 设置常住类型
     *
     * @param usualType 常住类型
     */
    public void setUsualType(String usualType) {
        this.usualType = usualType;
    }

    /**
     * 获取籍贾
     *
     * @return birthplace - 籍贾
     */
    public String getBirthplace() {
        return birthplace;
    }

    /**
     * 设置籍贾
     *
     * @param birthplace 籍贾
     */
    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    /**
     * 获取档案状态
     *
     * @return file_status - 档案状态
     */
    public String getFileStatus() {
        return fileStatus;
    }

    /**
     * 设置档案状态
     *
     * @param fileStatus 档案状态
     */
    public void setFileStatus(String fileStatus) {
        this.fileStatus = fileStatus;
    }

    /**
     * 获取顾问
     *
     * @return adviser - 顾问
     */
    public Integer getAdviser() {
        return adviser;
    }

    /**
     * 设置顾问
     *
     * @param adviser 顾问
     */
    public void setAdviser(Integer adviser) {
        this.adviser = adviser;
    }
}