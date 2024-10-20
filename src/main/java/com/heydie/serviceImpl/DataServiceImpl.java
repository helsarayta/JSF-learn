package com.heydie.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.primefaces.model.FilterMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
	
	




}
