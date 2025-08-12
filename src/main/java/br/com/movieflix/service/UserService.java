package br.com.movieflix.service;

import br.com.movieflix.controller.request.UserRequest;
import br.com.movieflix.controller.response.UserResponse;
import br.com.movieflix.entity.User;
import br.com.movieflix.mapper.UserMapper;
import br.com.movieflix.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse salvar(UserRequest userRequest) {
        String password = userRequest.password();
        User user = UserMapper.toUser(userRequest);

        user.setPassword(passwordEncoder.encode(password));
        return UserMapper.toUserResponse(userRepository.save(user));
    }

    @Transactional(readOnly = true)
    public List<UserResponse> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toUserResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public UserResponse findById(UUID id){
        return userRepository.findById(id)
                .map(UserMapper::toUserResponse)
                .orElse(null);
    }

    @Transactional
    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }
}
