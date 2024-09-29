package com.HospitalAppointmentScheduling.Entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "Patients")
@EntityListeners(AuditingEntityListener.class)
public class patientVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "patient_id")
	private Long patientId;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "dob", nullable = false)
	private Date dob;

	@Column(name = "patient_phone", nullable = false, unique = true)
	private String PatientPhone;

	@Column(name = "patient_email", nullable = false, unique = true)
	private String PatientEmail;

	@Column(name = "patient_password", nullable = false, unique = true)
	private String PatientPassword;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdAt", nullable = false)
	private LocalDateTime createdAt;

	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updatedAt", nullable = false)
	private LocalDateTime updatedAt;

	// Mapping for appointments
	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<appointmentsVO> appointments;

	// Getters and Setters methods
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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
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

	// ToString method
	@Override
	public String toString() {
		return "patientVO [patientId=" + patientId + ", firstName=" + firstName + ", lastName=" + lastName + ", dob="
				+ dob + ", PatientPhone=" + PatientPhone + ", PatientEmail=" + PatientEmail + ", PatientPassword="
				+ PatientPassword + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

}
