package com.management.studentbackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.management.studentbackend.entities.Student;
import com.management.studentbackend.models.Response;
import com.management.studentbackend.services.StudentService;

@RestController
@RequestMapping("/students")
@CrossOrigin("*")
public class StudentController {

	@Autowired
	StudentService studentService;

	@GetMapping
	public ResponseEntity<List<Student>> getStudents() {
		return ResponseEntity.ok(studentService.getStudents());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudent(@PathVariable int id) {
		return ResponseEntity.ok(studentService.getStudent(id));
	}

	@PostMapping
	public ResponseEntity<Response> addStudent(@RequestBody Student student) {
		return ResponseEntity.ok(studentService.saveStudent(student, "Student has been added"));
	}

	@PutMapping
	public ResponseEntity<Response> updateStudent(@RequestBody Student student) {
		return ResponseEntity.ok(studentService.saveStudent(student, "Student has been updated"));
	}
	
	@PutMapping("/payment")
	public ResponseEntity<Response> updatePayment(@RequestBody Student student ,@RequestParam int payble){
		return ResponseEntity.ok(studentService.updatePayment(student,payble));
	}

	@DeleteMapping
	public ResponseEntity<Response> deleteStudent(@RequestBody Student student) {
		return ResponseEntity.ok(studentService.deleteStudent(student));
	}

}
