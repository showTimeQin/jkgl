package com.showtime.jkgl.controller.user;

import com.showtime.jkgl.constant.ParamRoleConstant;
import com.showtime.jkgl.mapper.AdviserMapper;
import com.showtime.jkgl.mapper.SessionLogMapper;
import com.showtime.jkgl.mapper.UserMapper;
import com.showtime.jkgl.model.base.HostHolder;
import com.showtime.jkgl.model.entity.Adviser;
import com.showtime.jkgl.model.entity.SessionLog;
import com.showtime.jkgl.model.entity.User;
import com.showtime.jkgl.service.AdviserService;
import com.showtime.jkgl.service.UserService;
import com.showtime.jkgl.utils.JkglUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@Slf4j
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private AdviserMapper adviserMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private SessionLogMapper sessionLogMapper;

    @GetMapping(value = {"/index"})
    public String index() {
        return "/user/index";
    }

    @GetMapping(value = {"/healthScheme"})
    public String healthScheme() {
        return "/user/health_scheme";
    }

    @GetMapping(value = {"/sport"})
    public String sport() {
        return "/user/sport";
    }

    @GetMapping(value = {"/login"})
    public String loginPage() {
        return "/user/login";
    }

    @PostMapping(value = {"/login"})
    public String login(@Param("username") String username,
                        @Param("password") String password,
                        Model model, HttpServletResponse response) {
        log.info("username: {}, password: {}", username, password);
        Map<String, Object> map = new HashMap<>();
        map = userService.login(username, password);

        if (JkglUtil.addTicketToCookie(response, map)){
            return "redirect:/user/index";
        }
        model.addAllAttributes(map);
        return "/user/login";
    }

    @GetMapping(value = {"/logout"})
    public String logout() {
        userService.logout(hostHolder.getTicket());
        return "redirect:/user/login";
    }

    @PostMapping(value = {"/register"})
    public String register(@Param("username")String username,
                           @Param("password")String password,
                           Model model,HttpServletResponse response) {
        Map<String, Object> map = userService.register(username, password);

        if (JkglUtil.addTicketToCookie(response, map)){
            return "redirect:/index";
        }
        for(Map.Entry entry:map.entrySet()){
            log.info("key:{}, value:{}",entry.getKey(), entry.getValue());
        }
        model.addAllAttributes(map);
        return "/user/index";
    }

    @GetMapping(value = {"/register"})
    public String registerPage() {
        return "/user/register";
    }

    @GetMapping(value = {"/message"})
    public String message(Model model) {
        model.addAttribute("user", hostHolder.getUser());
        return "/user/message";
    }

    @GetMapping(value = {"/messageUpdate"})
    public String messageUpdatePage(Model model) {
        model.addAttribute("user", hostHolder.getUser());
        return "/user/messageUpdate";
    }

    @PostMapping(value = {"/messageUpdate"})
    public String messageUpdate(@Param("name")Integer id,
                                @Param("name")String name,
                                @Param("gender")String gender,
                                @Param("tel")String tel,
                                @Param("nation")String nation,
                                @Param("birthday")String birthday,
                                @Param("email")String email,
                                @Param("cardType")String cardType,
                                @Param("cardid")String cardid,
                                @Param("houseTel")String houseTel,
                                @Param("fileReason")String fileReason,
                                @Param("complication")String complication,
                                @Param("houseAddress")String houseAddress,
                                @Param("unitAddress")String unitAddress,
                                @Param("eduBackground")String eduBackground,
                                @Param("position")String position,
                                @Param("maritalStatus")String maritalStatus,
                                @Param("usualType")String usualType,
                                @Param("birthplace")String birthplace,
                                @Param("fileStatus")String fileStatus,
                                Model model) {
        User user = new User();
        user.setFileNo(id);
        user.setName(name);
        user.setGender(gender);
        user.setTel(tel);
        user.setNation(nation);
        user.setBirthday(birthday);
        user.setEmail(email);
        user.setCardType(cardType);
        user.setCardid(cardid);
        user.setHouseTel(houseTel);
        user.setFileReason(fileReason);
        user.setComplication(complication);
        user.setHouseAddress(houseAddress);
        user.setUnitAddress(unitAddress);
        user.setEduBackground(eduBackground);
        user.setPosition(position);
        user.setMaritalStatus(maritalStatus);
        user.setUsualType(usualType);
        user.setBirthplace(birthplace);
        user.setFileStatus(fileStatus);
        userMapper.updateByPrimaryKeySelective(user);
        model.addAttribute("user", hostHolder.getUser());
        return "redirect:/user/message";
    }

    @GetMapping(value = {"/session"})
    public String session(Model model) {
        User user = hostHolder.getUser();
        Integer adviserId = hostHolder.getUser().getAdviser();
        if (adviserId == null){
            model.addAttribute("error", "请先选择一个顾问");
            return "/user/session";
        }

        Adviser adviser = adviserMapper.selectByPrimaryKey(Long.valueOf(adviserId));
        if(adviser == null){
            model.addAttribute("error", "该顾问不存在");
        }

        Example example = new Example(SessionLog.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userFileNo", user.getFileNo());
        criteria.andEqualTo("adviserId", adviserId);
        List<SessionLog> sessionLogs = sessionLogMapper.selectByExample(example);
        model.addAttribute("sessionLogs", sessionLogs);

        return "/user/session";
    }

    @PostMapping(value = {"/selectAdviser"})
    public String selectAdviser(@Param("account") String account, Model model) {
        User user = hostHolder.getUser();
        if(user.getAdviser() != null){
            model.addAttribute("error", "你已经选择了顾问");
            return "/user/session";
        }

        Adviser a1 = new Adviser();
        a1.setAccount(account);
        Adviser adviser = adviserMapper.selectOne(a1);
        log.info("adviser : {}", adviser);
        if(adviser == null || adviser.getId() == null){
            model.addAttribute("error", "该顾问不存在");
            return "/user/session";
        }

        user.setAdviser(adviser.getId().intValue());
        userMapper.updateByPrimaryKeySelective(user);
        model.addAttribute("success", "选择成功");

        return "/user/session";
    }

    @PostMapping(value = {"/send"})
    public String send(@Param("content") String content, Model model) {
        User user = hostHolder.getUser();
        Long adviserId = Long.valueOf(hostHolder.getUser().getAdviser());
        if (adviserId == null){
            model.addAttribute("error", "请先选择一个顾问");
            return "/user/session";
        }

        Adviser adviser = adviserMapper.selectByPrimaryKey(adviserId);
        if(adviser == null){
            model.addAttribute("error", "该顾问不存在");
        }
        log.info("123");
        SessionLog sessionLog = new SessionLog();
        sessionLog.setAdviserId(adviserId);
        sessionLog.setContent(content);
        sessionLog.setTime(new Date());
        sessionLog.setUserFileNo(Long.valueOf(user.getFileNo()));
        sessionLog.setSender(Long.valueOf(user.getFileNo()));
        sessionLogMapper.insert(sessionLog);

        Example example = new Example(SessionLog.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userFileNo", user.getFileNo());
        criteria.andEqualTo("adviserId", adviserId);
        List<SessionLog> sessionLogs = sessionLogMapper.selectByExample(example);
        model.addAttribute("sessionLogs", sessionLogs);

        return "/user/session";
    }
}
