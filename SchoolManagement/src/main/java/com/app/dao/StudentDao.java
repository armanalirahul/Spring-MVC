package com.app.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Student;

@RestController
public class StudentDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public String insert(Student student) {
		sessionFactory.getCurrentSession().save(student);
		return "Added Successfull...";
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<Student> getAllDetails() {
		Query<Student> query = sessionFactory.getCurrentSession().createQuery("from Student");
		List<Student> list = query.getResultList();
		return list;
	}

	@Transactional
	public Student searchDetailById(int id) {
		Student student = sessionFactory.getCurrentSession().get(Student.class, id);
		return student;
	}

	@Transactional
	public int updateData(Student student) {
		int i = 0;
		sessionFactory.getCurrentSession().update(student);
		i = 1;
		return i;
	}

	@Transactional
	public int deleteData(int id) {
		int i = 0;
		Student student = searchDetailById(id);
		if (student != null) {
			sessionFactory.getCurrentSession().delete(student);
			i = 1;
			return i;
		} else {
			return i;
		}

	}

}
