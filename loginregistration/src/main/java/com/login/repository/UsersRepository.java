package com.login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.login.entity.OurUsers;

@Repository
public interface UsersRepository extends JpaRepository<OurUsers, Integer> {

	
	Optional<OurUsers> findByEmail(String email);
}

