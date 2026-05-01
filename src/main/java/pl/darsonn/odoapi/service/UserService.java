package pl.darsonn.odoapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.darsonn.odoapi.exception.ResourceAlreadyExistsException;
import pl.darsonn.odoapi.model.dto.UserRequest;
import pl.darsonn.odoapi.model.dto.UserResponse;
import pl.darsonn.odoapi.model.entity.User;
import pl.darsonn.odoapi.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserResponse createUser(UserRequest request) {
        if(userRepository.existsByUsername(request.username())) {
            throw new ResourceAlreadyExistsException("User with username '" + request.username() + "' already exists.");
        }

        User user = new User();
        user.setUsername(request.username());
        User savedUser = userRepository.save(user);

        return mapToResponse(savedUser);
    }

    private UserResponse mapToResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername()
        );
    }
}
