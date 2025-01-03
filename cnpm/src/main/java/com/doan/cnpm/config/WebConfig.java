//package com.doan.cnpm.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        // Cho phép tất cả các request từ localhost:3000
//        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:3000")  // Cấp phép localhost:3000
//                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Các phương thức HTTP cho phép
//                .allowedHeaders("*")  // Các header cho phép
//                .allowCredentials(true);  // Cho phép cookies hoặc thông tin xác thực
//    }
//}
