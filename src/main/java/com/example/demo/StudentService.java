package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
	@Autowired
	private StudentRepo repo;
	
	public void save(Student student) {
		repo.save(student);
	}
	public Student get(String id) {
		return repo.findById(id).orElse(null);
	}
}
