package com.tutorial.security.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tutorial.security.User.User;

public interface UserRepository extends JpaRepository<User,Integer>{

	Optional<User> findByEmail(String email);
}
