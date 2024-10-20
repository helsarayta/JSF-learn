package com.heydie.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.annotation.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.heydie.model.Student;
import com.heydie.service.DataService;

@Named
@ViewScoped
public class StudentForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private DataService service;
	
	LazyDataModel<Student> listStd;

	List<Student> studentList = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		studentList = service.getAll();
	}


	public List<Student> getStudentList() {
		System.out.println("LIST ST => "+studentList);
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		System.out.println("LIST SET => "+studentList);
		this.studentList = studentList;
	}
	
	
	
}
