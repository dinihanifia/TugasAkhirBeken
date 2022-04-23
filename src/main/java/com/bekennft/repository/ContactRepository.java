package com.bekennft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bekennft.model.ContactModel;

public interface ContactRepository extends JpaRepository<ContactModel, String>{

}
