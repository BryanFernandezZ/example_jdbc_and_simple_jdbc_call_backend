package com.example.jdbctemplate.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientRequestDto {
    private String name;
    private String lastName;
}
