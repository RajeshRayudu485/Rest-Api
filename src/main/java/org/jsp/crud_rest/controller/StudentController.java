package org.jsp.crud_rest.controller;

import java.util.List;

import org.jsp.crud_rest.dto.Student;
import org.jsp.crud_rest.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

	@Autowired
	StudentRepository repository;

	@PostMapping("/students")
	public ResponseEntity<Student> addStudents(@RequestBody Student student) {
		student.setPercentage((student.getMaths() + student.getEnglish() + student.getScience()) / 3.0);
		if (student.getMaths() < 35 || student.getScience() < 35 || student.getEnglish() < 35) {
			student.setResult("Fail");
		} else if (student.getPercentage() < 60) {
			student.setResult("Second Class");
		} else if (student.getPercentage() < 80) {
			student.setResult("First Class");
		} else {
			student.setResult("Distinction");
		}
		return new ResponseEntity<Student>(repository.save(student), HttpStatus.CREATED);

	}

	@PostMapping("/students/many")
	public ResponseEntity<List<Student>> addAll(@RequestBody List<Student> students) {
		for (Student student : students) {
			student.setPercentage((student.getMaths() + student.getEnglish() + student.getScience()) / 3.0);
			if (student.getMaths() < 35 || student.getScience() < 35 || student.getEnglish() < 35) {
				student.setResult("Fail");
			} else if (student.getPercentage() < 60) {
				student.setResult("Second Class");
			} else if (student.getPercentage() < 80) {
				student.setResult("First Class");
			} else {
				student.setResult("Distinction");
			}
		}
		return new ResponseEntity<List<Student>>(repository.saveAll(students), HttpStatus.CREATED);

	}

	@GetMapping("/students")
	public ResponseEntity<List<Student>> fetchAll() {
		List<Student> student = repository.findAll();
		if (!student.isEmpty()) {
			return new ResponseEntity<List<Student>>(student, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<List<Student>>(student, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/students/id/{id}")
	public ResponseEntity<Student> fetchById(@PathVariable long id) {
		Student student = repository.findById(id).orElse(null);
		return new ResponseEntity<Student>(student, HttpStatus.FOUND);
	}
    @GetMapping("/students/name/{name}")
    public ResponseEntity<List<Student>> findByName(@PathVariable String name){
    	List<Student> student=repository.findByName(name);
    	if (!student.isEmpty()) {
    		return new ResponseEntity<List<Student>>(student,HttpStatus.FOUND);
			
		} else {
			return new ResponseEntity<List<Student>>(student,HttpStatus.NOT_FOUND);
		}
    }
    @GetMapping("/students/mobile/{mobile}")
    public ResponseEntity<Student> fetchByMobile(@PathVariable long mobile){
		return null;
    
    }
}
