package com.HospitalAppointmentScheduling.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.HospitalAppointmentScheduling.Entity.AppointmentStatusVO;
import com.HospitalAppointmentScheduling.Entity.doctorVO;
import com.HospitalAppointmentScheduling.Entity.patientVO;

@Component
public class appointmentDTO {

	private Long appointmentID;
	private LocalDate appointmentDate;
	private String reason;
	private LocalDateTime createdAt;

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

	public doctorVO getDoctor() {
		return doctor;
	}

	public void setDoctor(doctorVO doctor) {
		this.doctor = doctor;
	}

	public patientVO getPatient() {
		return patient;
	}

	public void setPatient(patientVO patient) {
		this.patient = patient;
	}

	public AppointmentStatusVO getStatus() {
		return status;
	}

	public void setStatus(AppointmentStatusVO status) {
		this.status = status;
	}

	private LocalDateTime updatedAt;
	private doctorVO doctor;
	private patientVO patient;
	private AppointmentStatusVO status;
}
