package com.showtime.jkgl.controller;



import com.showtime.jkgl.constant.LoginTicketFieldConstant;
import com.showtime.jkgl.constant.ParamRoleConstant;
import com.showtime.jkgl.service.AdminService;
import com.showtime.jkgl.service.AdviserService;
import com.showtime.jkgl.utils.JkglUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
public class LoginController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AdviserService adviserService;

    @PostMapping(value = {"/login"})
    public String login(@Param("username") String username,
                        @Param("password") String password,
                        @Param("role") String role,
                        Model model, HttpServletResponse response) {
        log.info("username: {}, password: {}, role: {}", username, password, role);
        Map<String, Object> map = new HashMap<>();
        if(ParamRoleConstant.ADMIN.equals(role)){
            map = adminService.login(username, password);
        }else if(ParamRoleConstant.ADVISER.equals(role)){
            map = adviserService.login(username, password);
        }

        if (JkglUtil.addTicketToCookie(response, map)){
            return "redirect:index";
        }
        model.addAllAttributes(map);
        return "login";
    }

    @GetMapping(value = {"/login"})
    public String index() {
        return "login";
    }

}
