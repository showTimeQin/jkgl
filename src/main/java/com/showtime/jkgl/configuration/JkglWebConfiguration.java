package com.showtime.jkgl.configuration;


import com.showtime.jkgl.interceptor.AdminRequiredInterceptor;
import com.showtime.jkgl.interceptor.PassportInterceptor;
import com.showtime.jkgl.interceptor.UserRequiredInterceptor;
import com.showtime.jkgl.interceptor.AdviserRequiredInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by nowcoder on 2016/7/3.
 */
@Component
public class JkglWebConfiguration extends WebMvcConfigurerAdapter {
    @Autowired
    PassportInterceptor passportInterceptor;

    @Autowired
    AdminRequiredInterceptor adminRequiredInterceptor;

    @Autowired
    UserRequiredInterceptor userRequiredInterceptor;

    @Autowired
    AdviserRequiredInterceptor adviserRequiredInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(passportInterceptor);
        registry.addInterceptor(adminRequiredInterceptor).
                addPathPatterns("/admin/*").excludePathPatterns("/admin/login").excludePathPatterns("/admin/register");
        registry.addInterceptor(userRequiredInterceptor).
                addPathPatterns("/user/*").excludePathPatterns("/user/login").excludePathPatterns("/user/register");;
        registry.addInterceptor(adviserRequiredInterceptor).
                addPathPatterns("/adviser/*").excludePathPatterns("/adviser/register");
        super.addInterceptors(registry);
    }
}
