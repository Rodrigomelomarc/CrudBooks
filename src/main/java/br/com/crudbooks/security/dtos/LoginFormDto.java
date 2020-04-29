package br.com.crudbooks.security.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginFormDto {

    private String email;
    private String password;

}
