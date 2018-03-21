package com.showtime.jkgl.controller;

import com.github.pagehelper.Page;
import com.showtime.jkgl.model.entity.DietStorage;
import com.showtime.jkgl.model.entity.EnergyMeasurement;
import com.showtime.jkgl.service.DietStorageService;
import com.showtime.jkgl.service.EnergyMeasurementService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@Slf4j
public class FoodController {

    @Autowired
    private DietStorageService dietStorageService;

    @Autowired
    private EnergyMeasurementService energyMeasurementService;

    private final static String[] group = {
            "谷薯芋、杂豆、主食",
            "蛋类、肉类及制品",
            "奶类及制品",
            "蔬果和菌藻",
            "坚果、大豆及制品",
            "饮料",
            "食用油、油脂及制品",
            "调味品",
            "零食、点心、冷饮",
            "其它",
            "广东菜",
            "东北菜",
            "其他西餐",
            "北京菜",
            "四川菜",
            "宁夏菜",
            "家常菜",
            "少数民族菜",
            "山东菜",
            "山西菜",
            "广州菜",
            "河南菜",
            "浙江菜",
            "清真菜",
            "福建菜",
            "私家菜"
    };

    private final static String[] names = {
            "name",
            "nickName",
            "spell",
            "energy",
            "contentType"
    };

    @GetMapping(value = {"/food/group/{index}"})
    public String group(@PathVariable("index") Integer index, Model model, @RequestParam(name = "page", defaultValue = "1") int page) {
        Page<DietStorage> dietStorages = dietStorageService.getDietStorageByClassification(group[index], page, 10);

        model.addAttribute("groups", group);
        model.addAttribute("group", group[index]);
        model.addAttribute("groupIndex", index);
        model.addAttribute("dietStorages", dietStorages.getResult());
        model.addAttribute("pageNum", dietStorages.getPageNum());
        model.addAttribute("pageSize", dietStorages.getPages());
        return "group";
    }

    @GetMapping(value = {"/shiwu/{spell}"})
    public String shiwu(@PathVariable("spell") String spell, Model model) {
        DietStorage dietStorage = dietStorageService.getDietStorageBySpell(spell);
        List<EnergyMeasurement> energyMeasurements = energyMeasurementService.getByFoodStorageId(dietStorage.getId());

        for(int i=0; i<group.length; ++i){
            if(group[i].equals(dietStorage.getClassification())){
                model.addAttribute("groupIndex", i);
                break;
            }
        }
        model.addAttribute("dietStorage", dietStorage);
        model.addAttribute("energyMeasurements", energyMeasurements);
        return "shiwu";
    }

    @PostMapping(value = {"/shiwu/update"})
    @ResponseBody
    public String update(@RequestParam(name = "name") String name,
                         @RequestParam(name = "spell") String spell,
                         @RequestParam(name = "value", required = false) String value) {
        DietStorage dietStorage = dietStorageService.getDietStorageBySpell(spell);

        int falg = 0;
        BigDecimal bvalue = null;

        for(String n : names){
            if(n.equals(name)){
                falg = 1;
                break;
            }
        }
        try {
            if(falg == 0){
                log.info("value : -{}-", value);
                bvalue = new BigDecimal(value.trim());
                dietStorageService.update(name, dietStorage, bvalue, BigDecimal.class);
            }else {
                dietStorageService.update(name, dietStorage, value, String.class);
            }
        }catch (Exception e){
            log.error(e.getMessage(), e);
            return "插入失败";
        }

        return "success";
    }
}
