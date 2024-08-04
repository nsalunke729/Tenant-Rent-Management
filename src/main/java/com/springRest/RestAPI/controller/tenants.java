package com.springRest.RestAPI.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
public class tenants {
    
    List<String> tenant;
    
    public tenants(){
        tenant = Arrays.asList("abc", "def", "Xyz");
    }
    @GetMapping("/tenants")
    //test url http://127.0.0.1:8080/tenants
    public List<String> getTenants(){
        //TODO: process GET request
        //Logger.notify("in get method", "http://127.0.0.1);    
        return tenant;
    }

    @PostMapping("tenants/{name}")
    //test url http://127.0.0.1:8080/tenants/
public String postMethodName(@RequestBody List<String> entity) {
    // Process POST request to add new tenants
    if (entity != null && !entity.isEmpty()) {
        for (String s : entity) {
            tenant.add(s);
        }
        return "Tenants added successfully";
    } else {
        return "Invalid input data";
    }
}
    

    @PutMapping("/tenants/{name}")
    public String putMethodName(@PathVariable String nameString, @RequestBody String entity) {
        //TODO: process PUT request
        tenant.add(nameString);
        return "Tenant added: " + nameString;
    }

    @DeleteMapping("tenants/{name}")
    public String deleteMethodName(@PathVariable String nameString) {
        //TODO: process DELETE request
        tenant.remove(nameString);
        return "Tenant deleted: " + nameString;
    }
    
}
