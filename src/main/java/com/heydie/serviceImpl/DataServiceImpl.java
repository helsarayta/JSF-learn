package com.heydie.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;
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
	
	
	public List<Student> listCriteria(int first, int pageSize, Map<String, FilterMeta> filterBy) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	    CriteriaQuery<Student> cq = cb.createQuery(Student.class);
	    Root<Student> student = cq.from(Student.class);

	    // Add filtering for address (Jakarta or Semarang)
	    cq.where(cb.upper(student.get("address")).in("Jakarta".toUpperCase(), "Semarang".toUpperCase()));

	    // Optionally, add other dynamic filters from filterBy
	    if (filterBy != null && !filterBy.isEmpty()) {
	        for (Map.Entry<String, FilterMeta> entry : filterBy.entrySet()) {
	            String field = entry.getKey();
	            Object filterValue = entry.getValue().getFilterValue();

	            if (filterValue != null) {
	                cq.where(cb.equal(student.get(field), filterValue));
	            }
	        }
	    }

	    // Execute query with pagination
	    return entityManager.createQuery(cq)
	                        .setFirstResult(first)
	                        .setMaxResults(pageSize)
	                        .getResultList();
		
	}
	
	public List<Student> useJPQL(int first, int pageSize, Map<String, FilterMeta> filterBy) {
	    // Base JPQL query to filter students by address
	    StringBuilder jpql = new StringBuilder("SELECT s FROM Student s WHERE UPPER(s.address) IN (:cities)");

	    // Add dynamic filters from filterBy
	    if (filterBy != null && !filterBy.isEmpty()) {
	        for (Map.Entry<String, FilterMeta> entry : filterBy.entrySet()) {
	            String field = entry.getKey();
	            jpql.append(" AND s.").append(field).append(" = :").append(field);  // Add filters dynamically
	        }
	    }

	    // Create the query from the JPQL
	    Query query = entityManager.createQuery(jpql.toString());

	    // Set parameter for cities (Jakarta, Semarang)
	    query.setParameter("cities", List.of("Jakarta".toUpperCase(), "Semarang".toUpperCase()));

	    // Set dynamic filter values
	    if (filterBy != null && !filterBy.isEmpty()) {
	        for (Map.Entry<String, FilterMeta> entry : filterBy.entrySet()) {
	            String field = entry.getKey();
	            Object filterValue = entry.getValue().getFilterValue();
	            query.setParameter(field, filterValue);  // Set the parameter for dynamic filters
	        }
	    }

	    // Handle pagination
	    query.setFirstResult(first);
	    query.setMaxResults(pageSize);

	    // Execute the query and return the result list
	    return query.getResultList();
	}
	
	@Override
	public Long countData() {
		Session s = entityManager.unwrap(Session.class);
		Criteria c = s.createCriteria(Student.class);
		
		c.setProjection(Projections.rowCount());
		
		return (Long)c.uniqueResult();
	}
	
	
	public int countDataUseCriteriaBuilder() {
	    // Get CriteriaBuilder from EntityManager
	    CriteriaBuilder cb = entityManager.getCriteriaBuilder();

	    // Create CriteriaQuery for Long (since row count returns a number)
	    CriteriaQuery<Long> cq = cb.createQuery(Long.class);

	    // Define the root (the entity type you're counting)
	    Root<Student> student = cq.from(Student.class);

	    // Set projection (count rows)
	    cq.select(cb.count(student));

	    // Execute the query and return the result as an integer
	    return entityManager.createQuery(cq).getSingleResult().intValue();
	}
	
	public int countDataJPQL() {
	    // Define JPQL count query
	    String jpql = "SELECT COUNT(s) FROM Student s";
	    
	    // Create the query
	    Long count = entityManager.createQuery(jpql, Long.class).getSingleResult();
	    
	    // Return the result as an integer
	    return count.intValue();
	}

}
