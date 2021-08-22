package com.app.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Teacher;

@RestController
public class TeacherDao {


	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public String insert(Teacher teacher) {
		sessionFactory.getCurrentSession().save(teacher);
		return "Added Successfull...";
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<Teacher> getAllDetails() {
		Query<Teacher> query = sessionFactory.getCurrentSession().createQuery("from Teacher");
		List<Teacher> list = query.getResultList();
		return list;
	}

	@Transactional
	public Teacher searchDetailById(int id) {
		Teacher teacher = sessionFactory.getCurrentSession().get(Teacher.class, id);
		return teacher;
	}

	@Transactional
	public int updateData(Teacher teacher) {
		int i = 0;
		sessionFactory.getCurrentSession().update(teacher);
		i = 1;
		return i;
	}

	@Transactional
	public int deleteData(int id) {
		int i = 0;
		Teacher teacher = searchDetailById(id);
		if (teacher != null) {
			sessionFactory.getCurrentSession().delete(teacher);
			i = 1;
			return i;
		} else {
			return i;
		}

	}
	
}
