package com.project.blogger.validation;

import com.project.blogger.model.security.request.SignUpRequest;
import com.project.blogger.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValidator {

  private final UserRepository userRepository;

  public boolean validateUsername(final SignUpRequest signUpRequest) {
    return !userRepository.existsByUsername(signUpRequest.getUsername());
  }

  public boolean validateEmail(final SignUpRequest signUpRequest) {
    return !userRepository.existsByEmail(signUpRequest.getEmail());
  }

}
