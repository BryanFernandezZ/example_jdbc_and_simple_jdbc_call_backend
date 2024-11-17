package com.example.jdbctemplate.model;

import lombok.Builder;

import java.util.Map;

@Builder
public class Client {
    private Integer id;
    private String name;
    private String lastName;
}
