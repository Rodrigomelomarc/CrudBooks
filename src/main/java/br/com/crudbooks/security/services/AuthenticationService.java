package br.com.crudbooks.security.services;

import br.com.crudbooks.security.dtos.LoginFormDto;
import br.com.crudbooks.user.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var optional = userRepository.findByEmail(username);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new UsernameNotFoundException("Invalid entries");
    }

    public UsernamePasswordAuthenticationToken dtoToUPToken(LoginFormDto dto) {
        return new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
    }

}
