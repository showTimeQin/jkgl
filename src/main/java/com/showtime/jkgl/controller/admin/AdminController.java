package com.showtime.jkgl.controller.admin;

import com.showtime.jkgl.model.base.HostHolder;
import com.showtime.jkgl.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Slf4j
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private HostHolder hostHolder;

    @GetMapping(value = {"/logout"})
    public String logout() {
        adminService.logout(hostHolder.getTicket());
        return "redirect:/index";
    }

    @GetMapping(value = {"/message"})
    public String message() {
        return "admin/message";
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
    public String update(@RequestParam(name = "email") String email,
                         @RequestParam(name = "password") String password,
                         @RequestParam(name = "confirm_password") String confirm_password,
                         Model model) {

        if(!password.equals(confirm_password)){
            model.addAttribute("msgpwd", "X, 密码不一致");
            return "admin/message";
        }

        adminService.updatePasswordAndEmail(password, email);
        return "redirect:/index";
    }
}
