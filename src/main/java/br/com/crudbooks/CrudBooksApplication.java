package br.com.crudbooks;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class CrudBooksApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudBooksApplication.class, args);
	}
}
