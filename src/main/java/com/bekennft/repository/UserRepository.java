package com.bekennft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bekennft.model.UserCustomModel;
import com.bekennft.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long>{
	
	/*
	 * @Query(
	 * value="SELECT email, full_name, username FROM `user` WHERE username like ?1")
	 * List<UserCustomModel> getDataByEmailAndFullnameAndUsername(String email,
	 * String fullName, String username);
	 */

	UserModel findByUsername(String username);

}
