package com.banquemisr.challenge05.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @JsonIgnore
    private Long id;


    private String username;

    @JsonIgnore

    private String email;

    @JsonIgnore
    private String password;


    private List<String> roles = new ArrayList<>();

}