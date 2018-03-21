package com.showtime.jkgl.configuration;


import com.showtime.jkgl.interceptor.AdminRequiredInterceptor;
import com.showtime.jkgl.interceptor.PassportInterceptor;
import com.showtime.jkgl.interceptor.StudentRequiredInterceptor;
import com.showtime.jkgl.interceptor.TeacherRequiredInterceptor;
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
    StudentRequiredInterceptor studentRequiredInterceptor;

    @Autowired
    TeacherRequiredInterceptor teacherRequiredInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(passportInterceptor);
        registry.addInterceptor(adminRequiredInterceptor).
                addPathPatterns("/admin/*").excludePathPatterns("/admin/login");
        registry.addInterceptor(studentRequiredInterceptor).
                addPathPatterns("/student/*");
        registry.addInterceptor(teacherRequiredInterceptor).
                addPathPatterns("/teacher/*");
        super.addInterceptors(registry);
    }
}
