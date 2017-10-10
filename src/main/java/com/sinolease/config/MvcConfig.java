package com.sinolease.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by AlbertLy on 2016/11/15.
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/home").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/FTPManagement").setViewName("FTPManagement");
        registry.addViewController("/provisionManagement").setViewName("provisionManagement");
        registry.addViewController("/rateManagement").setViewName("rateManagement");
        registry.addViewController("/userManagement").setViewName("userManagement");
        registry.addViewController("/titleManagement").setViewName("titleManagement");
        registry.addViewController("/departmentManagement").setViewName("departmentManagement");
        registry.addViewController("/changePassword").setViewName("changePassword");
        registry.addViewController("/KPI").setViewName("KPI");
        registry.addViewController("/newKPI").setViewName("newKPI");
        registry.addViewController("/KPIResult").setViewName("KPIResult");
        registry.addViewController("/AMLCustomer").setViewName("AMLCustomer");
        registry.addViewController("/AMLScore").setViewName("AMLScore");
        registry.addViewController("/paymentReport").setViewName("paymentReport");
}
}
