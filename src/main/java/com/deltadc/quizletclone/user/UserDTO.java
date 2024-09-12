package com.deltadc.quizletclone.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private Long user_id;

    private String username;

    private String email;

    private Role role;

}
