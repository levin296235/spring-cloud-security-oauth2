package com.megvii.distributed.order.model;

import lombok.Data;

/**
 * @author Administrator
 * @version 1.0
 **/
@Data
public class UserDto {
    private String id;
    private String username;
    private String password;
    private String fullname;
    private String mobile;
    private String status;
}
