package com.showtime.jkgl.controller.adviser;

import com.showtime.jkgl.constant.LoginTicketFieldConstant;
import com.showtime.jkgl.mapper.DietStorageMapper;
import com.showtime.jkgl.mapper.HealthSchemeMapper;
import com.showtime.jkgl.mapper.SessionLogMapper;
import com.showtime.jkgl.mapper.UserMapper;
import com.showtime.jkgl.model.base.HostHolder;
import com.showtime.jkgl.model.base.ViewObject;
import com.showtime.jkgl.model.entity.*;
import com.showtime.jkgl.service.AdviserService;
import com.showtime.jkgl.utils.JkglUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@Slf4j
@RequestMapping(value = "/adviser")
public class AdviserController {

    @Autowired
    private AdviserService adviserService;

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SessionLogMapper sessionLogMapper;

    @Autowired
    private HealthSchemeMapper healthSchemeMapper;

    @Autowired
    private DietStorageMapper dietStorageMapper;

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

    @GetMapping(value = {"/selectUser/{mode}"})
    public String selectUser(@PathVariable("mode")String mode, Model model) {
        Adviser adviser = hostHolder.getAdviser();

        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("adviser", adviser.getId().intValue());
        List<User> users = userMapper.selectByExample(example);

        model.addAttribute("users", users);
        model.addAttribute("mode", mode);

        return "adviser/selectUser";
    }

    @GetMapping(value = {"/session/{fileNo}"})
    public String session(@PathVariable("fileNo") Integer fileNo, Model model) {
        Adviser adviser = hostHolder.getAdviser();
        User user = userMapper.selectByPrimaryKey(fileNo);

        Example example = new Example(SessionLog.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("adviserId", adviser.getId());
        criteria.andEqualTo("userFileNo", Long.valueOf(fileNo));
        List<SessionLog> sessionLogs = sessionLogMapper.selectByExample(example);

        model.addAttribute("sessionLogs", sessionLogs);
        model.addAttribute("user", user);

        return "adviser/session";
    }

    @GetMapping(value = {"/healthScheme/{fileNo}"})
    public String healthScheme(@PathVariable("fileNo") Integer fileNo, Model model) {
        List<ViewObject> vos = new ArrayList<>();
        Adviser adviser = hostHolder.getAdviser();
        User user = userMapper.selectByPrimaryKey(fileNo);

        Example example = new Example(HealthScheme.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("adviserId", adviser.getId());
        criteria.andEqualTo("userFileNo", Long.valueOf(fileNo));
        List<HealthScheme> healthSchemes = healthSchemeMapper.selectByExample(example);

        for(HealthScheme healthScheme : healthSchemes){
            ViewObject viewObject = new ViewObject();
            viewObject.set("healthScheme", healthScheme);
            String[] temp = healthScheme.getDietStorageIds().split(",");
            List<String> dietStorageIds =  Arrays.asList(temp);
            List<DietStorage> dietStorages = dietStorageMapper.selectByIds(dietStorageIds);
//            viewObject.set("");
        }

        model.addAttribute("healthSchemes", healthSchemes);
        model.addAttribute("user", user);

        return "adviser/healthScheme";
    }

    @GetMapping(value = {"/healthScheme/{fileNo}/add"})
    public String addHealthScheme(@PathVariable("fileNo") Integer fileNo, Model model) {

        model.addAttribute("fileNo", fileNo);

        return "adviser/addHealthScheme";
    }

    @PostMapping(value = {"/send/{fileNo}"})
    public String send(@PathVariable("fileNo") Integer fileNo, @Param("content") String content, Model model) {
        Adviser adviser = hostHolder.getAdviser();

        User user = userMapper.selectByPrimaryKey(fileNo);
        if(user == null){
            model.addAttribute("error", "该用户不存在");
            return "/adviser/session";
        }
        SessionLog sessionLog = new SessionLog();
        sessionLog.setAdviserId(adviser.getId());
        sessionLog.setContent(content);
        sessionLog.setTime(new Date());
        sessionLog.setUserFileNo(Long.valueOf(user.getFileNo()));
        sessionLog.setSender(Long.valueOf(adviser.getId()));
        sessionLogMapper.insert(sessionLog);

        Example example = new Example(SessionLog.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userFileNo", user.getFileNo());
        criteria.andEqualTo("adviserId", adviser.getId());
        List<SessionLog> sessionLogs = sessionLogMapper.selectByExample(example);
        model.addAttribute("sessionLogs", sessionLogs);

        return "redirect:/adviser/session/{fileNo}";
    }
}
