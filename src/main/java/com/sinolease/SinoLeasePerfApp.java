package com.sinolease;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.github.pagehelper.PageHelper;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@EnableCaching
@EnableTransactionManagement
@MapperScan(basePackages = "com.sinolease.dao")
public class SinoLeasePerfApp {

    public static void main(String[] args) {
        SpringApplication.run(SinoLeasePerfApp.class, args);
    }


    
    @Bean  
    public PageHelper pageHelper() {   
        PageHelper pageHelper = new PageHelper();  
        Properties p = new Properties();  
        p.setProperty("offsetAsPageNum", "true");  
        p.setProperty("rowBoundsWithCount", "true");  
        p.setProperty("reasonable", "true");  
        pageHelper.setProperties(p);  
        return pageHelper;  
    } 
    
    @Bean
    public Converter<String, Date> addNewConvert() {
        return new Converter<String, Date>() {
            @Override
            public Date convert(String source) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = sdf.parse((String) source);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return date;
            }
        };
    }
}
