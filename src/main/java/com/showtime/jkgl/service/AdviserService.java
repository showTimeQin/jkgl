package com.showtime.jkgl.service;


import com.showtime.jkgl.constant.TicketRoleConstant;
import com.showtime.jkgl.enums.ResultEnum;
import com.showtime.jkgl.exception.JkglException;
import com.showtime.jkgl.mapper.AdviserMapper;
import com.showtime.jkgl.mapper.LoginTicketMapper;
import com.showtime.jkgl.model.base.HostHolder;
import com.showtime.jkgl.model.entity.Adviser;
import com.showtime.jkgl.model.entity.LoginTicket;
import com.showtime.jkgl.utils.JkglUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class AdviserService {
    @Autowired
    private AdviserMapper adviserMapper;

    @Autowired
    private LoginTicketMapper loginTicketMapper;


    @Autowired
    private HostHolder hostHolder;

    public Map<String, Object> register(String username, String password) {
        Map<String, Object> map = JkglUtil.checkUsernameAndPassword(username,password);

        if(map.size() != 0){
            return map;
        }

        Adviser a1 = new Adviser();
        a1.setAccount(username);
        Adviser adviser = adviserMapper.selectOne(a1);

        if (adviser != null) {
            map.put("msgname", "用户名已经被注册");
            return map;
        }

        // 密码强度
        adviser = new Adviser();
        adviser.setAccount(username);
        adviser.setSalt(UUID.randomUUID().toString().substring(0, 5));
        adviser.setPassword(JkglUtil.MD5(password+adviser.getSalt()));
        adviserMapper.insert(adviser);
        // 登陆
        String ticket = addLoginTicket(adviser.getId());
        map.put("ticket", ticket);
        return map;
    }


    public Map<String, Object> login(String username, String password) {

        Map<String, Object> map = JkglUtil.checkUsernameAndPassword(username,password);

        if(map.size() != 0){
            return map;
        }

        Adviser a1 = new Adviser();
        a1.setAccount(username);
        Adviser adviser = adviserMapper.selectOne(a1);

        if (adviser == null) {
            map.put("msgname", "用户名不存在");
            return map;
        }

        if (!JkglUtil.MD5(password+adviser.getSalt()).equals(adviser.getPassword())) {
            map.put("msgpwd", "密码不正确");
            return map;
        }

        map.put("userId", adviser.getId());

        String ticket = addLoginTicket(adviser.getId());
        map.put("ticket", ticket);
        return map;
    }

    private String addLoginTicket(Long accountId) {
        LoginTicket ticket = JkglUtil.InitLoginTicket(accountId);
        ticket.setRole(TicketRoleConstant.ADVISER);
        loginTicketMapper.insert(ticket);
        return ticket.getTicket();
    }



    public Adviser getAdviser(Long id) {
        return adviserMapper.selectByPrimaryKey(id);
    }

    public void logout(String ticket) {
        Example example = JkglUtil.logout(ticket);

        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setStatus((byte)0);
        loginTicketMapper.updateByExampleSelective(loginTicket, example);
    }

    public void updatePassword(String password) {
        if (StringUtils.isBlank(password)) {
            throw new JkglException(ResultEnum.EMPTY_PASSWORD);
        }
        Adviser teacher = hostHolder.getAdviser();
        teacher.setPassword(JkglUtil.MD5(password+teacher.getSalt()));
        adviserMapper.updateByPrimaryKey(teacher);
    }

    public void updatePasswordAndIntroduce(String password, String introduce) {
        if (StringUtils.isBlank(password)) {
            throw new JkglException(ResultEnum.EMPTY_PASSWORD);
        }
        Adviser teacher = hostHolder.getAdviser();
        teacher.setPassword(JkglUtil.MD5(password+teacher.getSalt()));
        if("".equals(introduce)){
            teacher.setIntroduce(introduce);
        }
        adviserMapper.updateByPrimaryKey(teacher);
    }
}
