package com.HospitalAppointmentScheduling.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.HospitalAppointmentScheduling.Entity.DoctorVO;
import com.HospitalAppointmentScheduling.Entity.PatientVO;

public class AppointmentDTO {

	private Long appointmentID;
	private LocalDate appointmentDate;
	private String reason;
	private DoctorVO doctor;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private PatientVO patient;

	public Long getAppointmentID() {
		return appointmentID;
	}

	public void setAppointmentID(Long appointmentID) {
		this.appointmentID = appointmentID;
	}

	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public PatientVO getPatient() {
		return patient;
	}

	public void setPatient(PatientVO patient) {
		this.patient = patient;
	}

	public DoctorVO getDoctor() {
		return doctor;
	}

	public void setDoctor(DoctorVO doctor) {
		this.doctor = doctor;
	}

}
