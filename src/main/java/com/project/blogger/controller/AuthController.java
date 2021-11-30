package com.project.blogger.controller;

import com.project.blogger.model.security.request.LoginRequest;
import com.project.blogger.model.security.request.SignUpRequest;
import com.project.blogger.model.security.response.JwtResponse;
import com.project.blogger.model.security.response.MessageResponse;
import com.project.blogger.service.AuthService;
import com.project.blogger.validation.UserValidator;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:8081")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

  private final AuthService authService;

  private final UserValidator userValidator;

  @PostMapping("/login")
  public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody final LoginRequest loginRequest) {
    return ResponseEntity.ok(authService.login(loginRequest));
  }

  @PostMapping("/register")
  public ResponseEntity<MessageResponse> register(@Valid @RequestBody final SignUpRequest signUpRequest) {
    if (Boolean.FALSE.equals(userValidator.validateUsername(signUpRequest))) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Username already in use!"));
    }

    if (Boolean.FALSE.equals(userValidator.validateEmail(signUpRequest))) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Email already in use!"));
    }

    return ResponseEntity.ok(authService.register(signUpRequest));
  }

}
