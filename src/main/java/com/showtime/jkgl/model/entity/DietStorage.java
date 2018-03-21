package com.showtime.jkgl.model.entity;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "diet_storage")
public class DietStorage {
    /**
     * 编码
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 别名
     */
    @Column(name = "nick_name")
    private String nickName;

    /**
     * 拼音
     */
    private String spell;

    /**
     * 热量(大卡)
     */
    private String energy;

    /**
     * 分类
     */
    private String classification;

    /**
     * 含量类别
     */
    @Column(name = "content_type")
    private String contentType;

    /**
     * 脂肪(克)
     */
    private BigDecimal fat;

    /**
     * 纤维素(克)
     */
    private BigDecimal cellulose;

    /**
     * 维生素C(毫克)
     */
    @Column(name = "vitamin_c")
    private BigDecimal vitaminC;

    /**
     * 胡萝卜素(微克)
     */
    private BigDecimal renieratene;

    /**
     * 核黄素(毫克)
     */
    private BigDecimal riboflavin;

    /**
     * 胆固醇(毫克)
     */
    private BigDecimal cholesterol;

    /**
     * 钙(毫克)
     */
    private BigDecimal calcium;

    /**
     * 锌(毫克)
     */
    private BigDecimal zinc;

    /**
     * 锰(毫克)
     */
    private BigDecimal manganese;

    /**
     * 磷(毫克)
     */
    private BigDecimal phosphorus;

    /**
     * 硒(微克)
     */
    private BigDecimal selenium;

    /**
     * 碳水化合物(克)
     */
    private BigDecimal carbohydrate;

    /**
     * 蛋白质(克)
     */
    private BigDecimal protein;

    /**
     * 维生素A(微克)
     */
    @Column(name = "vitamin_a")
    private BigDecimal vitaminA;

    /**
     * 维生素E(毫克)
     */
    @Column(name = "vitamin_e")
    private BigDecimal vitaminE;

    /**
     * 硫胺素(毫克)
     */
    private BigDecimal thiamine;

    /**
     * 烟酸(毫克)
     */
    private BigDecimal niacin;

    /**
     * 镁(毫克)
     */
    private BigDecimal magnesium;

    /**
     * 铁(毫克)
     */
    private BigDecimal iron;

    /**
     * 铜(毫克)
     */
    private BigDecimal copper;

    /**
     * 钾(毫克)
     */
    private BigDecimal potassium;

    /**
     * 钠(毫克)
     */
    private BigDecimal sodium;

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
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取别名
     *
     * @return nick_name - 别名
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 设置别名
     *
     * @param nickName 别名
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 获取拼音
     *
     * @return spell - 拼音
     */
    public String getSpell() {
        return spell;
    }

    /**
     * 设置拼音
     *
     * @param spell 拼音
     */
    public void setSpell(String spell) {
        this.spell = spell;
    }

    /**
     * 获取热量(大卡)
     *
     * @return energy - 热量(大卡)
     */
    public String getEnergy() {
        return energy;
    }

    /**
     * 设置热量(大卡)
     *
     * @param energy 热量(大卡)
     */
    public void setEnergy(String energy) {
        this.energy = energy;
    }

    /**
     * 获取分类
     *
     * @return classification - 分类
     */
    public String getClassification() {
        return classification;
    }

    /**
     * 设置分类
     *
     * @param classification 分类
     */
    public void setClassification(String classification) {
        this.classification = classification;
    }

    /**
     * 获取含量类别
     *
     * @return content_type - 含量类别
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * 设置含量类别
     *
     * @param contentType 含量类别
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * 获取脂肪(克)
     *
     * @return fat - 脂肪(克)
     */
    public BigDecimal getFat() {
        return fat;
    }

    /**
     * 设置脂肪(克)
     *
     * @param fat 脂肪(克)
     */
    public void setFat(BigDecimal fat) {
        this.fat = fat;
    }

    /**
     * 获取纤维素(克)
     *
     * @return cellulose - 纤维素(克)
     */
    public BigDecimal getCellulose() {
        return cellulose;
    }

    /**
     * 设置纤维素(克)
     *
     * @param cellulose 纤维素(克)
     */
    public void setCellulose(BigDecimal cellulose) {
        this.cellulose = cellulose;
    }

    /**
     * 获取维生素C(毫克)
     *
     * @return vitamin_c - 维生素C(毫克)
     */
    public BigDecimal getVitaminC() {
        return vitaminC;
    }

    /**
     * 设置维生素C(毫克)
     *
     * @param vitaminC 维生素C(毫克)
     */
    public void setVitaminC(BigDecimal vitaminC) {
        this.vitaminC = vitaminC;
    }

    /**
     * 获取胡萝卜素(微克)
     *
     * @return renieratene - 胡萝卜素(微克)
     */
    public BigDecimal getRenieratene() {
        return renieratene;
    }

    /**
     * 设置胡萝卜素(微克)
     *
     * @param renieratene 胡萝卜素(微克)
     */
    public void setRenieratene(BigDecimal renieratene) {
        this.renieratene = renieratene;
    }

    /**
     * 获取核黄素(毫克)
     *
     * @return riboflavin - 核黄素(毫克)
     */
    public BigDecimal getRiboflavin() {
        return riboflavin;
    }

    /**
     * 设置核黄素(毫克)
     *
     * @param riboflavin 核黄素(毫克)
     */
    public void setRiboflavin(BigDecimal riboflavin) {
        this.riboflavin = riboflavin;
    }

    /**
     * 获取胆固醇(毫克)
     *
     * @return cholesterol - 胆固醇(毫克)
     */
    public BigDecimal getCholesterol() {
        return cholesterol;
    }

    /**
     * 设置胆固醇(毫克)
     *
     * @param cholesterol 胆固醇(毫克)
     */
    public void setCholesterol(BigDecimal cholesterol) {
        this.cholesterol = cholesterol;
    }

    /**
     * 获取钙(毫克)
     *
     * @return calcium - 钙(毫克)
     */
    public BigDecimal getCalcium() {
        return calcium;
    }

    /**
     * 设置钙(毫克)
     *
     * @param calcium 钙(毫克)
     */
    public void setCalcium(BigDecimal calcium) {
        this.calcium = calcium;
    }

    /**
     * 获取锌(毫克)
     *
     * @return zinc - 锌(毫克)
     */
    public BigDecimal getZinc() {
        return zinc;
    }

    /**
     * 设置锌(毫克)
     *
     * @param zinc 锌(毫克)
     */
    public void setZinc(BigDecimal zinc) {
        this.zinc = zinc;
    }

    /**
     * 获取锰(毫克)
     *
     * @return manganese - 锰(毫克)
     */
    public BigDecimal getManganese() {
        return manganese;
    }

    /**
     * 设置锰(毫克)
     *
     * @param manganese 锰(毫克)
     */
    public void setManganese(BigDecimal manganese) {
        this.manganese = manganese;
    }

    /**
     * 获取磷(毫克)
     *
     * @return phosphorus - 磷(毫克)
     */
    public BigDecimal getPhosphorus() {
        return phosphorus;
    }

    /**
     * 设置磷(毫克)
     *
     * @param phosphorus 磷(毫克)
     */
    public void setPhosphorus(BigDecimal phosphorus) {
        this.phosphorus = phosphorus;
    }

    /**
     * 获取硒(微克)
     *
     * @return selenium - 硒(微克)
     */
    public BigDecimal getSelenium() {
        return selenium;
    }

    /**
     * 设置硒(微克)
     *
     * @param selenium 硒(微克)
     */
    public void setSelenium(BigDecimal selenium) {
        this.selenium = selenium;
    }

    /**
     * 获取碳水化合物(克)
     *
     * @return carbohydrate - 碳水化合物(克)
     */
    public BigDecimal getCarbohydrate() {
        return carbohydrate;
    }

    /**
     * 设置碳水化合物(克)
     *
     * @param carbohydrate 碳水化合物(克)
     */
    public void setCarbohydrate(BigDecimal carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    /**
     * 获取蛋白质(克)
     *
     * @return protein - 蛋白质(克)
     */
    public BigDecimal getProtein() {
        return protein;
    }

    /**
     * 设置蛋白质(克)
     *
     * @param protein 蛋白质(克)
     */
    public void setProtein(BigDecimal protein) {
        this.protein = protein;
    }

    /**
     * 获取维生素A(微克)
     *
     * @return vitamin_a - 维生素A(微克)
     */
    public BigDecimal getVitaminA() {
        return vitaminA;
    }

    /**
     * 设置维生素A(微克)
     *
     * @param vitaminA 维生素A(微克)
     */
    public void setVitaminA(BigDecimal vitaminA) {
        this.vitaminA = vitaminA;
    }

    /**
     * 获取维生素E(毫克)
     *
     * @return vitamin_e - 维生素E(毫克)
     */
    public BigDecimal getVitaminE() {
        return vitaminE;
    }

    /**
     * 设置维生素E(毫克)
     *
     * @param vitaminE 维生素E(毫克)
     */
    public void setVitaminE(BigDecimal vitaminE) {
        this.vitaminE = vitaminE;
    }

    /**
     * 获取硫胺素(毫克)
     *
     * @return thiamine - 硫胺素(毫克)
     */
    public BigDecimal getThiamine() {
        return thiamine;
    }

    /**
     * 设置硫胺素(毫克)
     *
     * @param thiamine 硫胺素(毫克)
     */
    public void setThiamine(BigDecimal thiamine) {
        this.thiamine = thiamine;
    }

    /**
     * 获取烟酸(毫克)
     *
     * @return niacin - 烟酸(毫克)
     */
    public BigDecimal getNiacin() {
        return niacin;
    }

    /**
     * 设置烟酸(毫克)
     *
     * @param niacin 烟酸(毫克)
     */
    public void setNiacin(BigDecimal niacin) {
        this.niacin = niacin;
    }

    /**
     * 获取镁(毫克)
     *
     * @return magnesium - 镁(毫克)
     */
    public BigDecimal getMagnesium() {
        return magnesium;
    }

    /**
     * 设置镁(毫克)
     *
     * @param magnesium 镁(毫克)
     */
    public void setMagnesium(BigDecimal magnesium) {
        this.magnesium = magnesium;
    }

    /**
     * 获取铁(毫克)
     *
     * @return iron - 铁(毫克)
     */
    public BigDecimal getIron() {
        return iron;
    }

    /**
     * 设置铁(毫克)
     *
     * @param iron 铁(毫克)
     */
    public void setIron(BigDecimal iron) {
        this.iron = iron;
    }

    /**
     * 获取铜(毫克)
     *
     * @return copper - 铜(毫克)
     */
    public BigDecimal getCopper() {
        return copper;
    }

    /**
     * 设置铜(毫克)
     *
     * @param copper 铜(毫克)
     */
    public void setCopper(BigDecimal copper) {
        this.copper = copper;
    }

    /**
     * 获取钾(毫克)
     *
     * @return potassium - 钾(毫克)
     */
    public BigDecimal getPotassium() {
        return potassium;
    }

    /**
     * 设置钾(毫克)
     *
     * @param potassium 钾(毫克)
     */
    public void setPotassium(BigDecimal potassium) {
        this.potassium = potassium;
    }

    /**
     * 获取钠(毫克)
     *
     * @return sodium - 钠(毫克)
     */
    public BigDecimal getSodium() {
        return sodium;
    }

    /**
     * 设置钠(毫克)
     *
     * @param sodium 钠(毫克)
     */
    public void setSodium(BigDecimal sodium) {
        this.sodium = sodium;
    }
}