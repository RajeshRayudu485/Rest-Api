package org.jsp.crud_rest.repository;

import java.util.List;

import org.jsp.crud_rest.dto.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long>{

	List<Student> findByName(String name);

}
