package br.com.crudbooks.user.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.crudbooks.user.dtos.UserDto;
import br.com.crudbooks.user.dtos.UserFormDto;
import br.com.crudbooks.user.services.UserService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/users")
@AllArgsConstructor
public class UserController {

	private final UserService userService;
	
	@PostMapping
	public ResponseEntity<UserDto> store(@RequestBody UserFormDto formDto) {
		var userDto = userService.createUser(formDto);
		return ResponseEntity.ok().body(userDto);
	}

}
