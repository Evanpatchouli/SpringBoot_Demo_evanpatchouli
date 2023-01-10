package com.evanpatchouli.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    int id;
    private String name;
    private String pwd;

    public User(String name, String pwd){
        this.name = name;
        this.pwd = pwd;
    }
}
