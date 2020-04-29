package br.com.crudbooks.user.dtos;

import br.com.crudbooks.user.models.User;
import lombok.Data;

@Data
public class UserDto {

	private Long id;
	private String nickname;
	private String email;

}
