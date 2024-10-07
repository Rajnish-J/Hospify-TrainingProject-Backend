package com.HospitalAppointmentScheduling.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PatientDTO {
	private Long patientId;
	private String firstName;
	private String lastName;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dob;
	private String patientPhone;
	private String patientEmail;
	private String patientPassword;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private List<AppointmentDTO> appointments;

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
		return patientPhone;
	}

	public void setPatientPhone(String patientPhone) {
		this.patientPhone = patientPhone;
	}

	public String getPatientEmail() {
		return patientEmail;
	}

	public void setPatientEmail(String patientEmail) {
		this.patientEmail = patientEmail;
	}

	public String getPatientPassword() {
		return patientPassword;
	}

	public void setPatientPassword(String patientPassword) {
		this.patientPassword = patientPassword;
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

	public List<AppointmentDTO> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<AppointmentDTO> appointments) {
		this.appointments = appointments;
	}

	@Override
	public String toString() {
		return "patientDTO [patientId=" + patientId + ", firstName=" + firstName + ", lastName=" + lastName + ", dob="
				+ dob + ", patientPhone=" + patientPhone + ", patientEmail=" + patientEmail + ", patientPassword="
				+ patientPassword + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", appointments="
				+ appointments + "]";
	}

}
