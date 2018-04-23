package com.showtime.jkgl.model.entity;

import javax.persistence.*;

@Table(name = "energy_measurement")
public class EnergyMeasurement {
    /**
     * 编码
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 膳食库编码
     */
    @Column(name = "food_storage_id")
    private Integer foodStorageId;

    /**
     * 度量单位
     */
    private String measurement;

    /**
     * 热量(可食部分热量)
     */
    private String energy;

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
     * 获取膳食库编码
     *
     * @return food_storage_id - 膳食库编码
     */
    public Integer getFoodStorageId() {
        return foodStorageId;
    }

    /**
     * 设置膳食库编码
     *
     * @param foodStorageId 膳食库编码
     */
    public void setFoodStorageId(Integer foodStorageId) {
        this.foodStorageId = foodStorageId;
    }

    /**
     * 获取度量单位
     *
     * @return measurement - 度量单位
     */
    public String getMeasurement() {
        return measurement;
    }

    /**
     * 设置度量单位
     *
     * @param measurement 度量单位
     */
    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    /**
     * 获取热量(可食部分热量)
     *
     * @return energy - 热量(可食部分热量)
     */
    public String getEnergy() {
        return energy;
    }

    /**
     * 设置热量(可食部分热量)
     *
     * @param energy 热量(可食部分热量)
     */
    public void setEnergy(String energy) {
        this.energy = energy;
    }

    public EnergyMeasurement() {
    }

    public EnergyMeasurement(EnergyMeasurement e) {
        this.foodStorageId = e.foodStorageId;
        this.measurement = e.measurement;
        this.energy = e.energy;
    }
}