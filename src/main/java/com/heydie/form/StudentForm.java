package com.heydie.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.CloseEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;

import com.heydie.VO.StudentVO;
import com.heydie.model.Student;
import com.heydie.service.DataService;

@Named
@ViewScoped
public class StudentForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private DataService service;
	
	StudentVO vo;
	
	LazyDataModel<Student> listStd;

	List<Student> studentList; 
	
	private boolean showButton = true;
	
	@PostConstruct
	public void init() {
		vo = new StudentVO();
		studentList = service.getAll();
	}
	
	public StudentVO getVo() {
		return vo;
	}

	public void setVo(StudentVO vo) {
		this.vo = vo;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}
	
	public void onClose(CloseEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Panel Closed",
                "Closed panel id:'" + event.getComponent().getId() + "'");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void onToggle(ToggleEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, event.getComponent().getId() + " toggled",
                "Status:" + event.getVisibility().name());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void saveStudent() {
    	if(vo.getId() == null) {
    		service.saveStudent(vo);
    	} else {
    		service.editStudent(vo.getId(), vo);
    	}
    	studentList = service.getAll();
    	resetForm();
    }
    
    public void resetForm() {
    	vo = new StudentVO();
    }
    
    public void editStudent(Student std) {
    	vo.setId(std.getId());
    	vo.setName(std.getName());
    	vo.setAddress(std.getAddress());
    	
    }
    
    public void deleteStudent(Student std) {
    	service.deleteStudent(std.getId());
    	studentList = service.getAll();
    }
    
    public void searchStudent() {
    	studentList = service.searchStudent(vo.getId(), vo.getName(), vo.getAddress());
    }
	
	
	
}
