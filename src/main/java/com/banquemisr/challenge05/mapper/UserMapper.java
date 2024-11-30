package com.banquemisr.challenge05.mapper;


import com.banquemisr.challenge05.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.security.core.GrantedAuthority;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {


    public UserDto map(UserDetails src) {
        UserDto dest = new UserDto();
        dest.setUsername(src.getUsername());
        List<String> roles = src.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        dest.setRoles(roles);

        return dest;
    }
}

