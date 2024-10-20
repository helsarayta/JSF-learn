package com.heydie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.primefaces.event.ToggleSelectEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.heydie.VO.UserVo;

@Named
@ViewScoped
public class UserForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(UserForm.class.getName());

	UserVo vo;

	private String username;
	private String password;
	private String name;
	private String address;
	private Integer age;
	private boolean check;
	private String fileName;
	private boolean showUpload = false;
	private boolean showButton = true;
	private UploadedFile fileUpload;

	private List<String> selectedCities;
	private List<String> cities;

	public void handleFileUpload() {
		if (fileUpload != null) {
			System.out.println("FILE Name =>> " + fileUpload);
			this.fileName = fileUpload.getFileName();
		}
	}

	public void showUploadButton() {
		this.showUpload = true;
		this.showButton = false;
	}

	public void onToggleSelect() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Selected =>> " + selectedCities));
	}

	public void addMessage() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(check ? "Not Ready" : "Ready"));
	}

	public UploadedFile getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(UploadedFile fileUpload) {
		System.out.println("FILE Upload =>> " + fileUpload);
		this.fileUpload = fileUpload;

	}

	public boolean isShowButton() {
		return showButton;
	}

	public void setShowButton(boolean showButton) {
		this.showButton = showButton;
	}

	public boolean isShowUpload() {
		return showUpload;
	}

	public void setShowUpload(boolean showUpload) {
		this.showUpload = showUpload;
	}


	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void displayMessage() {

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Login Success, welcome " + username));
	}

	public List<String> getSelectedCities() {
		return selectedCities;
	}

	public void setSelectedCities(List<String> selectedCities) {
		this.selectedCities = selectedCities;
	}

	public List<String> getCities() {
		return cities;
	}

	public void setCities(List<String> cities) {
		this.cities = cities;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@PostConstruct
	public void init() {
		vo = new UserVo();
		cities = new ArrayList<>();
		cities.add("Miami");
		cities.add("London");
		cities.add("Paris");
		cities.add("Istanbul");
		cities.add("Berlin");
		cities.add("Barcelona");
		cities.add("Rome");
		cities.add("Brasilia");
		cities.add("Amsterdam");

	}

	public String handleInsertForm() {
		log.info("DATA => {}" + vo.toString());

		return "index.xhtml";
	}

	public UserVo getVo() {
		return vo;
	}

	public void setVo(UserVo vo) {
		this.vo = vo;
	}

}
