package br.com.crudbooks.user.services;

import br.com.crudbooks.utils.exceptions.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.crudbooks.user.dtos.UserDto;
import br.com.crudbooks.user.dtos.UserFormDto;
import br.com.crudbooks.user.models.User;
import br.com.crudbooks.user.repositories.UserRepository;
import lombok.AllArgsConstructor;

import java.nio.file.attribute.UserPrincipalNotFoundException;

@Service
@AllArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder encoder;
	private final ModelMapper mapper;
	
	public UserDto createUser(UserFormDto formDto) {
		formDto.setPassword(encoder.encode(formDto.getPassword()));
		var user = dtoToEntity(formDto);
		user = userRepository.save(user);
		return entityToDto(user);
	}

	public User getCurrentUser() throws UserNotFoundException {
		var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		var username = ((UserDetails)principal).getUsername();
		return userRepository.findByEmail(username).orElseThrow(() ->  new UserNotFoundException("Usuário não encontrado"));
	}

	private User dtoToEntity(UserFormDto dto) {
		return mapper.map(dto, User.class);
	}

	private UserDto entityToDto(User user) {
		return mapper.map(user, UserDto.class);
	}
}
