package com.management.studentbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.studentbackend.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
