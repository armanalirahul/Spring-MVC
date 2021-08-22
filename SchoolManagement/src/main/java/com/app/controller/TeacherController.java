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

import com.app.dao.TeacherDao;
import com.app.model.Teacher;

@RestController
public class TeacherController {

	@Autowired
	private TeacherDao teacherDao;

	@PostMapping("/addTeacher")
	public String insert(@RequestBody() Teacher teacher) {

		String t = teacherDao.insert(teacher);
		if (t != null) {
			return "Teacher successfully added...";
		} else {
			return "failed operation";
		}
	}

	@GetMapping("/showTeachers")
	public List<Teacher> getAllDetails() {
		List<Teacher> all = teacherDao.getAllDetails();
		return all;
	}

	@GetMapping("/teacher/{id}")
	public ResponseEntity<Teacher> searchDataById(@PathVariable() int id) {
		Teacher teacher = teacherDao.searchDetailById(id);
		if (teacher != null) {
			ResponseEntity<Teacher> response = new ResponseEntity<Teacher>(teacher, HttpStatus.FOUND);
			return response;
		} else {
			ResponseEntity<Teacher> response = new ResponseEntity<Teacher>(teacher, HttpStatus.NOT_FOUND);
			return response;
		}
	}

	@PutMapping("/updateTeacher")
	public String update(@RequestBody() Teacher teacher) {
		int update = teacherDao.updateData(teacher);
		if (update > 0) {
			return "Successfully updated...";
		} else {
			return "operation failed";
		}
	}

	@DeleteMapping("/deleteTeacher/{id}")
	public String delete(@PathVariable() int id) {
		int delete = teacherDao.deleteData(id);
		if (delete > 0) {
			return "Successfully deleted...";
		} else {
			return "operation failed";
		}
	}

}
