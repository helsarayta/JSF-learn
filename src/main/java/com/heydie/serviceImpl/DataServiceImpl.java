package com.heydie.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heydie.VO.StudentVO;
import com.heydie.model.Student;
import com.heydie.repo.DataRepo;
import com.heydie.service.DataService;

@Service
@Transactional
public class DataServiceImpl implements DataService{
	
	@Autowired
	private DataRepo repo;

	@Override
	public List<Student> getAll() {
		List<Student> listStudents = repo.findAll();
		return listStudents;
	}

	@Override
	public Student saveStudent(StudentVO vo) {
		Student st = new Student();
		st.setName(vo.getName());
		st.setAddress(vo.getAddress());
		
		repo.save(st);
		return st;
	}

	@Override
	public Student editStudent(Long id, StudentVO vo) {
		Student std = repo.findById(id).get();
		
		std.setAddress(vo.getAddress());
		std.setName(vo.getName());
		
		repo.save(std);
		
		return std;
	}
	
	
	
	




}
