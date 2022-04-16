package com.bekennft.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="user")
public class UserModel {
	
	@Id
	@Column(length=25)
	private String username;
	private String fullName;
	private String email;
	private String password;

}
