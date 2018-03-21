package com.showtime.jkgl.service;



import com.showtime.jkgl.constant.TicketRoleConstant;
import com.showtime.jkgl.enums.ResultEnum;
import com.showtime.jkgl.exception.JkglException;
import com.showtime.jkgl.mapper.AdminMapper;
import com.showtime.jkgl.mapper.LoginTicketMapper;
import com.showtime.jkgl.model.base.HostHolder;
import com.showtime.jkgl.model.entity.Admin;
import com.showtime.jkgl.model.entity.LoginTicket;
import com.showtime.jkgl.utils.JkglUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;



@Service
@Slf4j
public class AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private LoginTicketMapper loginTicketMapper;

    @Autowired
    private HostHolder hostHolder;


    public Map<String, Object> register(String username, String password) {
        Map<String, Object> map = JkglUtil.checkUsernameAndPassword(username,password);

        if(map.size() != 0){
            return map;
        }

        Admin a1 = new Admin();
        a1.setAccount(username);
        Admin admin = adminMapper.selectOne(a1);

        if (admin != null) {
            map.put("msgname", "用户名已经被注册");
            return map;
        }

        // 密码强度
        admin = new Admin();
        admin.setAccount(username);
        admin.setSalt(UUID.randomUUID().toString().substring(0, 5));
        admin.setPassword(JkglUtil.MD5(password+admin.getSalt()));
        adminMapper.insert(admin);
        // 登陆
        String ticket = addLoginTicket(admin.getId());
        map.put("ticket", ticket);
        return map;
    }


    public Map<String, Object> login(String username, String password) {
        Map<String, Object> map = JkglUtil.checkUsernameAndPassword(username,password);

        if(map.size() != 0){
            return map;
        }

        Admin b1 = new Admin();
        b1.setAccount(username);
        Admin admin = adminMapper.selectOne(b1);

        if (admin == null) {
            /** 如果管理员第一次登录，则为其注册 */
            if(adminMapper.selectCount(new Admin()) == 0){
                return this.register(username, password);
            }
            map.put("msgname", "用户名不存在");
            return map;
        }

        if (!JkglUtil.MD5(password+admin.getSalt()).equals(admin.getPassword())) {
            map.put("msgpwd", "密码不正确");
            return map;
        }

        map.put("userId", admin.getId());

        String ticket = addLoginTicket(admin.getId());
        map.put("ticket", ticket);
        return map;
    }

    private String addLoginTicket(Long accountId) {
        LoginTicket ticket = JkglUtil.InitLoginTicket(accountId);
        ticket.setRole(TicketRoleConstant.ADMIN);
        loginTicketMapper.insert(ticket);
        return ticket.getTicket();
    }

    public Admin getAdmin() {
        return adminMapper.selectByPrimaryKey(1L);
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
        Admin admin = hostHolder.getAdmin();
        admin.setPassword(JkglUtil.MD5(password+admin.getSalt()));
        adminMapper.updateByPrimaryKey(admin);
    }

    public void updatePasswordAndEmail(String password, String email) {
        if (StringUtils.isBlank(password)) {
            throw new JkglException(ResultEnum.EMPTY_PASSWORD);
        }
        Admin admin = hostHolder.getAdmin();
        admin.setPassword(JkglUtil.MD5(password+admin.getSalt()));
        if("".equals(email)){
            admin.setEmail(email);
        }
        adminMapper.updateByPrimaryKey(admin);
    }
}
