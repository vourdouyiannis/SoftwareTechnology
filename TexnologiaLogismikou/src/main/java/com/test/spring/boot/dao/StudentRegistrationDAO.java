package com.test.spring.boot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.spring.boot.domain.StudentRegistration;


@Repository
public interface StudentRegistrationDAO extends JpaRepository<StudentRegistration, Integer>
{

}
