package com.springboot.securityjwt.dto.SignDto;

import lombok.*;

@Data
@Getter
@Setter
public class SignUpDto {
    private String uid;
    private String password;
    private String name;
    private String number;
    private String address;
}