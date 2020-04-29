package br.com.crudbooks.security.services;

import br.com.crudbooks.user.services.UserService;
import br.com.crudbooks.utils.exceptions.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PermissionService {

    private final UserService userService;

    public void verifyBookUser(Long userId) throws UserNotFoundException {
        var user = userService.getCurrentUser();
        if (user.getId() != userId) {
            throw  new AccessDeniedException("You dont't have permission in this book");
        }
    }

}
