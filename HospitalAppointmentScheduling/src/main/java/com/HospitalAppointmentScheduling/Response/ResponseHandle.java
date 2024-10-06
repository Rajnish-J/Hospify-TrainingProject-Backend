package com.HospitalAppointmentScheduling.Response;

import java.util.List;

import org.springframework.stereotype.Component;

import com.HospitalAppointmentScheduling.DAO.PatientProjection;
import com.HospitalAppointmentScheduling.Entity.patientVO;

@Component
public class ResponseHandle {

	private String Sucessmessage;
	private String failuremessage;
	private long id;
	private patientVO patient;
	private List<patientVO> Listpatient;
	private String phone;
	private PatientProjection pro;

	public PatientProjection getPro() {
		return pro;
	}

	public void setPro(PatientProjection pro) {
		this.pro = pro;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSucessmessage() {
		return Sucessmessage;
	}

	public void setSucessmessage(String sucessmessage) {
		Sucessmessage = sucessmessage;
	}

	public String getFailuremessage() {
		return failuremessage;
	}

	public void setFailuremessage(String failuremessage) {
		this.failuremessage = failuremessage;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public patientVO getPatient() {
		return patient;
	}

	public void setPatient(patientVO patient) {
		this.patient = patient;
	}

	public List<patientVO> getListpatient() {
		return Listpatient;
	}

	public void setListpatient(List<patientVO> listpatient) {
		Listpatient = listpatient;
	}
}
