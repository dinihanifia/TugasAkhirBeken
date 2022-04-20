package com.bekennft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bekennft.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long>{


	UserModel findByUsername(String username);

}
