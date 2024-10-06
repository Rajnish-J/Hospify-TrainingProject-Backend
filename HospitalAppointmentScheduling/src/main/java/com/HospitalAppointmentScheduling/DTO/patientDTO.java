package com.HospitalAppointmentScheduling.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.HospitalAppointmentScheduling.Entity.appointmentsVO;
import com.fasterxml.jackson.annotation.JsonFormat;

public class patientDTO {
	private Long patientId;
	private String firstName;
	private String lastName;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dob;
	private String PatientPhone;
	private String PatientEmail;
	private String PatientPassword;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private List<appointmentsVO> appointments;

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getPatientPhone() {
		return PatientPhone;
	}

	public void setPatientPhone(String patientPhone) {
		PatientPhone = patientPhone;
	}

	public String getPatientEmail() {
		return PatientEmail;
	}

	public void setPatientEmail(String patientEmail) {
		PatientEmail = patientEmail;
	}

	public String getPatientPassword() {
		return PatientPassword;
	}

	public void setPatientPassword(String patientPassword) {
		PatientPassword = patientPassword;
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

	public List<appointmentsVO> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<appointmentsVO> appointments) {
		this.appointments = appointments;
	}

	@Override
	public String toString() {
		return "patientDTO [patientId=" + patientId + ", firstName=" + firstName + ", lastName=" + lastName + ", dob="
				+ dob + ", PatientPhone=" + PatientPhone + ", PatientEmail=" + PatientEmail + ", PatientPassword="
				+ PatientPassword + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", appointments="
				+ appointments + "]";
	}

}
