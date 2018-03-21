package com.showtime.jkgl.controller.adviser;

import com.showtime.jkgl.constant.LoginTicketFieldConstant;
import com.showtime.jkgl.model.base.HostHolder;
import com.showtime.jkgl.service.AdviserService;
import com.showtime.jkgl.utils.JkglUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping(value = "/adviser")
public class AdviserController {

    @Autowired
    private AdviserService adviserService;

    @Autowired
    private HostHolder hostHolder;

    @PostMapping(value = {"/register"})
    public String register(@Param("username")String username,
                           @Param("password")String password,
                           Model model,HttpServletResponse response) {
        Map<String, Object> map = adviserService.register(username, password);

        if (JkglUtil.addTicketToCookie(response, map)){
            return "redirect:/index";
        }
        for(Map.Entry entry:map.entrySet()){
            log.info("key:{}, value:{}",entry.getKey(), entry.getValue());
        }
        model.addAllAttributes(map);
        return "adviser/register";
    }

    @GetMapping(value = {"/logout"})
    public String logout() {
        adviserService.logout(hostHolder.getTicket());
        return "redirect:/index";
    }

    @GetMapping(value = {"/register"})
    public String registerPage() {
        return "/adviser/register";
    }

    @GetMapping(value = {"/message"})
    public String message() {
        return "adviser/message";
    }

    @ResponseBody
    @PostMapping(value = {"/confirmPassword"})
    public String confirmPassword(@RequestParam(name = "password") String password,
                                  @RequestParam(name = "confirm_password") String confirm_password) {

        if(!password.equals(confirm_password)){
            return "X, 密码不一致";
        }
        return "√";
    }

    @PostMapping(value = {"/update"})
    public String update(@RequestParam(name = "introduce") String introduce,
                         @RequestParam(name = "password") String password,
                         @RequestParam(name = "confirm_password") String confirm_password,
                         Model model) {

        if(!password.equals(confirm_password)){
            model.addAttribute("msgpwd", "X, 密码不一致");
            return "admin/message";
        }

        adviserService.updatePasswordAndIntroduce(password, introduce);
        return "redirect:/index";
    }
}
