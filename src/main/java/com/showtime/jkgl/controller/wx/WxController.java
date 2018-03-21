package com.showtime.jkgl.controller.wx;

import com.showtime.jkgl.model.view.Setting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value = "/wx")
public class WxController {

    @Value("${telephone}")
    private String telephone;

    @GetMapping(value = {"/setting/get_set"})
    public Setting get_set() {
        Setting setting = new Setting();
        setting.setTelephone(telephone);
        return setting;

    }
}
