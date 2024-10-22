package com.heydie.service;

import java.util.List;
import java.util.Map;

import org.primefaces.model.FilterMeta;

import com.heydie.VO.StudentVO;
import com.heydie.model.Student;

public interface DataService {
	
	public List<Student> getAll();
	Student saveStudent(StudentVO vo);
	Student editStudent(Long id, StudentVO vo);
	void deleteStudent(Long id);
	List<Student> searchStudent(Long id, String name, String address);
	List<Student> listCriteria(int first, int pageSize, Map<String, FilterMeta> filterBy);
	List<Student> useJPQL(int first, int pageSize, Map<String, FilterMeta> filterBy);
	Long countData();
	int countDataUseCriteriaBuilder();
	int countDataJPQL();
	

}
