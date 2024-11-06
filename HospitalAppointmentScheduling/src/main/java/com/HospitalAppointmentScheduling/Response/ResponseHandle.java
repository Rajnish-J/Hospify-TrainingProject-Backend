package com.HospitalAppointmentScheduling.Response;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import com.HospitalAppointmentScheduling.DAO.PatientProjection;
import com.HospitalAppointmentScheduling.Entity.PatientVO;

@Component
public class ResponseHandle {

	private String sucessMessage;
	private String failureMessage;
	private long id;
	private PatientVO patient;
	private List<PatientVO> listPatient;
	private String phone;
	private PatientProjection pro;
	private LocalDate date;
	private List<LocalDate> listOfDates;

	public List<LocalDate> getListOfDates() {
		return listOfDates;
	}

	public void setListOfDates(List<LocalDate> listOfDates) {
		this.listOfDates = listOfDates;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getSucessMessage() {
		return sucessMessage;
	}

	public void setSucessMessage(String sucessMessage) {
		this.sucessMessage = sucessMessage;
	}

	public String getFailureMessage() {
		return failureMessage;
	}

	public void setFailureMessage(String failureMessage) {
		this.failureMessage = failureMessage;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public PatientVO getPatient() {
		return patient;
	}

	public void setPatient(PatientVO patient) {
		this.patient = patient;
	}

	public List<PatientVO> getListPatient() {
		return listPatient;
	}

	public void setListPatient(List<PatientVO> listPatient) {
		this.listPatient = listPatient;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public PatientProjection getPro() {
		return pro;
	}

	public void setPro(PatientProjection pro) {
		this.pro = pro;
	}

	@Override
	public String toString() {
		return "ResponseHandle [SucessMessage=" + sucessMessage + ", failureMessage=" + failureMessage + ", id=" + id
				+ ", patient=" + patient + ", listPatient=" + listPatient + ", phone=" + phone + ", pro=" + pro + "]";
	}

}
