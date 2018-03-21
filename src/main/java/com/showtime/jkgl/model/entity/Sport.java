package com.showtime.jkgl.model.entity;

import javax.persistence.*;

public class Sport {
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
    private String sportName;

    /**
     * 运动类别
     */
    @Column(name = "sports_category")
    private Integer sportsCategory;

    /**
     * 运动风险
     */
    @Column(name = "sport_risk")
    private Integer sportRisk;

    /**
     * 运动时间
     */
    @Column(name = "sport_time")
    private Integer sportTime;

    /**
     * 运动频率
     */
    @Column(name = "sport_frequency")
    private String sportFrequency;

    /**
     * 拉伸部位
     */
    @Column(name = "tensile_site")
    private String tensileSite;

    /**
     * 动作方法
     */
    @Column(name = "action_method")
    private String actionMethod;

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
    public String getSportName() {
        return sportName;
    }

    /**
     * 设置项目名
     *
     * @param sportName 项目名
     */
    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    /**
     * 获取运动类别
     *
     * @return sports_category - 运动类别
     */
    public Integer getSportsCategory() {
        return sportsCategory;
    }

    /**
     * 设置运动类别
     *
     * @param sportsCategory 运动类别
     */
    public void setSportsCategory(Integer sportsCategory) {
        this.sportsCategory = sportsCategory;
    }

    /**
     * 获取运动风险
     *
     * @return sport_risk - 运动风险
     */
    public Integer getSportRisk() {
        return sportRisk;
    }

    /**
     * 设置运动风险
     *
     * @param sportRisk 运动风险
     */
    public void setSportRisk(Integer sportRisk) {
        this.sportRisk = sportRisk;
    }

    /**
     * 获取运动时间
     *
     * @return sport_time - 运动时间
     */
    public Integer getSportTime() {
        return sportTime;
    }

    /**
     * 设置运动时间
     *
     * @param sportTime 运动时间
     */
    public void setSportTime(Integer sportTime) {
        this.sportTime = sportTime;
    }

    /**
     * 获取运动频率
     *
     * @return sport_frequency - 运动频率
     */
    public String getSportFrequency() {
        return sportFrequency;
    }

    /**
     * 设置运动频率
     *
     * @param sportFrequency 运动频率
     */
    public void setSportFrequency(String sportFrequency) {
        this.sportFrequency = sportFrequency;
    }

    /**
     * 获取拉伸部位
     *
     * @return tensile_site - 拉伸部位
     */
    public String getTensileSite() {
        return tensileSite;
    }

    /**
     * 设置拉伸部位
     *
     * @param tensileSite 拉伸部位
     */
    public void setTensileSite(String tensileSite) {
        this.tensileSite = tensileSite;
    }

    /**
     * 获取动作方法
     *
     * @return action_method - 动作方法
     */
    public String getActionMethod() {
        return actionMethod;
    }

    /**
     * 设置动作方法
     *
     * @param actionMethod 动作方法
     */
    public void setActionMethod(String actionMethod) {
        this.actionMethod = actionMethod;
    }
}