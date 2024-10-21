package com.heydie.serviceImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
	
	@PersistenceContext
	EntityManager entityManager;
	
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
	
	@Override
	public void deleteStudent(Long id) {
		repo.deleteById(id);
	}

	@Override
	public List<Student> searchStudent(Long id, String name, String address) {
		 
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT s FROM Student s WHERE 1 = 1");
		
		if(id != null) {
			sb.append(" AND id like :id");
		} 
		
		if (name != null || !name.isEmpty()) {
			sb.append(" AND name like :name");
		}
		
		if (address != null || !address.isEmpty()) {
			sb.append(" AND address like :address");
		}
		
		TypedQuery<Student> query = entityManager.createQuery(sb.toString(), Student.class);
		
		if(id != null) {
			query.setParameter("id", "%"+id+"%");
		}
		
		if (name != null || !name.isEmpty()) {
			query.setParameter("name", "%"+name+"%");
		} 
		
		if (address != null || !address.isEmpty()) {
			query.setParameter("address", "%"+address+"%");
		}
		
		return query.getResultList();
	
	}
	
	
	
	




}
