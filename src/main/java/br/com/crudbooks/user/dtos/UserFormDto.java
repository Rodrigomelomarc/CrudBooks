package br.com.crudbooks.user.dtos;

import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class UserFormDto {

	@Length(min = 3)
	private String nickname;
	@Email
	private String email;
	@Length(min = 6)
	private String password;
	
}
