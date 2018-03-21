package com.showtime.jkgl.model.entity;

import javax.persistence.*;

@Table(name = "sport_item")
public class SportItem {
    /**
     * 编码
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 项目名
     */
    @Column(name = "sport_name")
    private Integer sportName;

    /**
     * 档案号
     */
    @Column(name = "file_no")
    private String fileNo;

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
     * 获取项目名
     *
     * @return sport_name - 项目名
     */
    public Integer getSportName() {
        return sportName;
    }

    /**
     * 设置项目名
     *
     * @param sportName 项目名
     */
    public void setSportName(Integer sportName) {
        this.sportName = sportName;
    }

    /**
     * 获取档案号
     *
     * @return file_no - 档案号
     */
    public String getFileNo() {
        return fileNo;
    }

    /**
     * 设置档案号
     *
     * @param fileNo 档案号
     */
    public void setFileNo(String fileNo) {
        this.fileNo = fileNo;
    }
}