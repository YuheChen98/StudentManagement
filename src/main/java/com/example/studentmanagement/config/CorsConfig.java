package com.example.studentmanagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
//public class CorsConfig implements WebMvcConfigurer {
//    public void addCorsMappings(CorsRegistry registry){
//        registry.addMapping("/**")//允许跨域访问的路径
//                .allowedOrigins("*")//允许跨域访问的域
//                .allowedMethods("POST","GET","PUT","DELETE","OPTIONS")
//                .maxAge(168000)//预检间隔时间
//                .allowedHeaders("*")//允许头部设置
//                .allowCredentials(true);//是否发送cookie
//    }
//}
