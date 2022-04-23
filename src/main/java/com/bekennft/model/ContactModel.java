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
@Table(name="contact")

public class ContactModel {
	@Id
	@Column(length=20)
	private String Email;
	private String Name;
	private String PhoneNumber;
	private String Messages;
}
