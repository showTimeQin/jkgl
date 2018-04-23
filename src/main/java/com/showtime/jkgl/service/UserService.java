package com.showtime.jkgl.service;

import com.showtime.jkgl.constant.TicketRoleConstant;
import com.showtime.jkgl.mapper.LoginTicketMapper;
import com.showtime.jkgl.mapper.UserMapper;
import com.showtime.jkgl.model.entity.Adviser;
import com.showtime.jkgl.model.entity.LoginTicket;
import com.showtime.jkgl.model.entity.User;
import com.showtime.jkgl.utils.JkglUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LoginTicketMapper loginTicketMapper;

    public Map<String, Object> register(String username, String password) {
        Map<String, Object> map = JkglUtil.checkUsernameAndPassword(username,password);

        if(map.size() != 0){
            return map;
        }

        User u1 = new User();
        u1.setUsername(username);
        User user = userMapper.selectOne(u1);

        if (user != null) {
            map.put("msgname", "用户名已经被注册");
            return map;
        }

        // 密码强度
        user = new User();
        user.setUsername(username);
        user.setName(username);
        user.setPassword(JkglUtil.MD5(password));
        userMapper.insertSelective(user);
        log.info("userId : {}", user.getFileNo());
        // 登陆
        return login(username, password);
    }
    public Map<String, Object> login(String username, String password) {

        Map<String, Object> map = JkglUtil.checkUsernameAndPassword(username,password);

        if(map.size() != 0){
            return map;
        }

        User u1 = new User();
        u1.setUsername(username);
        User user = userMapper.selectOne(u1);

        if (user == null) {
            map.put("msgname", "用户名不存在");
            return map;
        }

        if (!JkglUtil.MD5(password).equals(user.getPassword())) {
            map.put("msgpwd", "密码不正确");
            return map;
        }

        map.put("userId", user.getFileNo());

        String ticket = addLoginTicket(Long.valueOf(user.getFileNo()));
        map.put("ticket", ticket);
        return map;
    }

    public void logout(String ticket) {
        Example example = JkglUtil.logout(ticket);

        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setStatus((byte)0);
        loginTicketMapper.updateByExampleSelective(loginTicket, example);
    }

    private String addLoginTicket(Long accountId) {
        LoginTicket ticket = JkglUtil.InitLoginTicket(accountId);
        ticket.setRole(TicketRoleConstant.USER);
        loginTicketMapper.insert(ticket);
        return ticket.getTicket();
    }
}
