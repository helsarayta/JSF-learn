package com.heydie.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.heydie.model.Student;

@Repository
public interface DataRepo extends JpaRepository<Student, Long>{
	

}
