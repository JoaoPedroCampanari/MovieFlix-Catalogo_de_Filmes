package br.com.movieflix.controller;

import br.com.movieflix.config.TokenComponent;
import br.com.movieflix.controller.request.LoginRequest;
import br.com.movieflix.controller.request.UserRequest;
import br.com.movieflix.controller.response.LoginResponse;
import br.com.movieflix.controller.response.UserResponse;
import br.com.movieflix.entity.User;
import br.com.movieflix.exception.UsernameOrPasswordInvalidException;
import br.com.movieflix.mapper.UserMapper;
import br.com.movieflix.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/movieflix/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenComponent tokenComponent;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> salvar(@RequestBody UserRequest userRequest){
        User salvado = userService.salvar(UserMapper.toUser(userRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toUserResponse(salvado));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        try {
            UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password());
            Authentication authentication = authenticationManager.authenticate(userAndPass);

            User user = (User) authentication.getPrincipal();
            String token = tokenComponent.generateToken(user);

            return ResponseEntity.ok(new LoginResponse(user.getEmail(), token));
        }catch (BadCredentialsException e){
            throw new UsernameOrPasswordInvalidException("Usuario ou senha inválidos. ");
        }
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll(){
        List<UserResponse> userResponses = userService.findAll()
                .stream()
                .map(UserMapper::toUserResponse)
                .toList();
        return ResponseEntity.ok(userResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable(name = "id") UUID id){
        return userService.findById(id)
                .map(user -> ResponseEntity.ok(UserMapper.toUserResponse(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(name = "id") UUID id){
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
