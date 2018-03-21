package com.showtime.jkgl.controller;

import com.showtime.jkgl.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class IndexController {
    @Autowired
    private AdminService adminService;

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

    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {
        model.addAttribute("groups", group);
        return "index";
    }

}
