package com.HospitalAppointmentScheduling.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.HospitalAppointmentScheduling.Entity.patientVO;

public class appointmentDTO {

	private Long appointmentID;
	private LocalDate appointmentDate;
	private String reason;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private patientVO patient;

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

	public patientVO getPatient() {
		return patient;
	}

	public void setPatient(patientVO patient) {
		this.patient = patient;
	}

}
