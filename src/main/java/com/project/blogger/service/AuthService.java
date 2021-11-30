package com.project.blogger.service;

import com.project.blogger.model.security.request.LoginRequest;
import com.project.blogger.model.security.request.SignUpRequest;
import com.project.blogger.model.security.response.JwtResponse;
import com.project.blogger.model.security.response.MessageResponse;

public interface AuthService {

  JwtResponse login(LoginRequest loginRequest);

  MessageResponse register(SignUpRequest signUpRequest);

}
