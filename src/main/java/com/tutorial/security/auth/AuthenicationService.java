package com.tutorial.security.auth;

import org.springframework.stereotype.Service;

import com.tutorial.security.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenicationService {
	private final UserRepository userRepository;
	
	public AuthenticationResponse register(RegisterRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		// TODO Auto-generated method stub
		return null;
	}


}
