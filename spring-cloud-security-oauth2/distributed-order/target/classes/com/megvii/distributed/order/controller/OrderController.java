package com.megvii.distributed.order.controller;

import com.megvii.distributed.order.model.UserDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @GetMapping(value = "/r1")
    @PreAuthorize("hasAnyAuthority('p1')")
    public String r1(){
        UserDto user=(UserDto)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getFullname() + "访问资源1";
    }

    @GetMapping(value = "/r2")
    @PreAuthorize("hasAnyAuthority('p2')")
    public String r2(){
        UserDto user=(UserDto)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername() + "访问资源2";
    }
}
