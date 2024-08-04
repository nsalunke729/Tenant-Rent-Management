package com.springRest.tenantRent;

import org.apache.tomcat.util.digester.SystemPropertySource;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class TenantRentApplication{

    @Value("${environment.name}") 
    private static String environmentName;

    @Value("${spring.datasource.url}") 
    private static String dburl;

    public static void main(String[] args) {
        SpringApplication.run(TenantRentApplication.class, args);
        System.out.println("Spring Boot Rest API application started!");
        
        System.out.println("Active Environment: " + environmentName);
        System.out.println("Database URL: " + dburl);

    }

}
