package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.StudentDao;
import com.app.model.Student;

@RestController//Representational State Transfer
public class StudentController {

	@Autowired
	private StudentDao studentDao;

	@PostMapping("/addStudent")
	public ResponseEntity<HttpStatus> insert(@RequestBody() Student student) {

		return (studentDao.insert(student) != null) ? new ResponseEntity<HttpStatus>(HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

	@GetMapping("/showStudents")
	public List<Student> getAllDetails() {
		List<Student> all = studentDao.getAllDetails();
		return all;
	}

	@GetMapping("/student/{id}")
	public ResponseEntity<Student> searchDataById(@PathVariable() int id) {
		Student student = studentDao.searchDetailById(id);
		if (student != null) {
			ResponseEntity<Student> response = new ResponseEntity<Student>(student, HttpStatus.FOUND);
			return response;
		} else {
			ResponseEntity<Student> response = new ResponseEntity<Student>(student, HttpStatus.NOT_FOUND);
			return response;
		}
	}

	@PutMapping("/updateStudent")
	public ResponseEntity<HttpStatus> update(@RequestBody() Student student) {
		int update = studentDao.updateData(student);
		if (update > 0) {
			ResponseEntity<HttpStatus> response = new ResponseEntity<HttpStatus>(HttpStatus.OK);
			return response;
		} else {
			ResponseEntity<HttpStatus> response = new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
			return response;
		}
	}

	@DeleteMapping("/deleteStudent/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable() int id) {
		int delete = studentDao.deleteData(id);
		if (delete > 0) {
			ResponseEntity<HttpStatus> response = new ResponseEntity<HttpStatus>(HttpStatus.OK);
			return response;
		} else {
			ResponseEntity<HttpStatus> response = new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
			return response;
		}
	}
}
