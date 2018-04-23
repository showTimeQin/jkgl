package com.showtime.jkgl.interceptor;




import com.showtime.jkgl.constant.AttributeNameConstant;
import com.showtime.jkgl.constant.TicketRoleConstant;
import com.showtime.jkgl.enums.ResultEnum;
import com.showtime.jkgl.exception.JkglException;
import com.showtime.jkgl.mapper.AdminMapper;
import com.showtime.jkgl.mapper.AdviserMapper;
import com.showtime.jkgl.mapper.LoginTicketMapper;
import com.showtime.jkgl.mapper.UserMapper;
import com.showtime.jkgl.model.base.HostHolder;
import com.showtime.jkgl.model.entity.Admin;
import com.showtime.jkgl.model.entity.Adviser;
import com.showtime.jkgl.model.entity.LoginTicket;
import com.showtime.jkgl.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by nowcoder on 2016/7/3.
 */
@Component
@Slf4j
public class PassportInterceptor implements HandlerInterceptor {

    @Autowired
    private LoginTicketMapper loginTicketMapper;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AdviserMapper adviserMapper;

    @Autowired
    private HostHolder hostHolder;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String ticket = null;
        if (httpServletRequest.getCookies() != null) {
            for (Cookie cookie : httpServletRequest.getCookies()) {
                if (cookie.getName().equals("ticket")) {
                    ticket = cookie.getValue();
                    hostHolder.setTicket(ticket);
                    break;
                }
            }
        }

        log.info("ticket is : {}", ticket);
        if (ticket != null) {

            LoginTicket t1 = new LoginTicket();
            t1.setTicket(ticket);
            LoginTicket loginTicket = loginTicketMapper.selectOne(t1);

            if (loginTicket == null || loginTicket.getExpired().before(new Date()) || loginTicket.getStatus() == 0) {
                return true;
            }

            if(TicketRoleConstant.ADMIN.equals(loginTicket.getRole())){
                Admin admin = adminMapper.selectByPrimaryKey(loginTicket.getAccountId());
                hostHolder.setAdmin(admin);
            }else if(TicketRoleConstant.USER.equals(loginTicket.getRole())){
                User user = userMapper.selectByPrimaryKey((loginTicket.getAccountId()).intValue());
                hostHolder.setUser(user);
            }else if(TicketRoleConstant.ADVISER.equals(loginTicket.getRole())){
                Adviser adviser = adviserMapper.selectByPrimaryKey(loginTicket.getAccountId());
                hostHolder.setAdviser(adviser);
            }else {
                throw new JkglException(ResultEnum.UNKNOW_ROLE);
            }

        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            if(hostHolder.getAdmin() != null){
                modelAndView.addObject(AttributeNameConstant.ADMIN, hostHolder.getAdmin());
            }

            if(hostHolder.getUser() != null){
                modelAndView.addObject(AttributeNameConstant.USER, hostHolder.getUser());
            }

            if(hostHolder.getAdviser() != null){
                modelAndView.addObject(AttributeNameConstant.ADVISER, hostHolder.getAdviser());
            }

        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        hostHolder.clear();
    }
}
