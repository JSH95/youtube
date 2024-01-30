package com.weavus.weatube.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UserDto {
    private String id;
    private String password;
    private String userName;

}