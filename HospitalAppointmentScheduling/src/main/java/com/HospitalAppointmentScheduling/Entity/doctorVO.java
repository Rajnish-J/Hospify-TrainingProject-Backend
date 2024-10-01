package com.HospitalAppointmentScheduling.Entity;

import java.time.LocalDateTime;
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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "doctor")
@EntityListeners(AuditingEntityListener.class)
public class doctorVO {

	@Id
	@Column(name = "doctor_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long doctorId;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "doc_phone", nullable = false, unique = true)
	private String doctorPhone;

	@Column(name = "doc_email", nullable = false, unique = true)
	private String doctorEmail;

	@Column(name = "doc_password", nullable = false, unique = true)
	private String doctorPassword;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdAt")
	private LocalDateTime createdAt;

	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updatedAt")
	private LocalDateTime updatedAt;

	// mapping
	// Many-to-One Relationship with Hospital
	@ManyToOne
	@JoinColumn(name = "hospital_id", nullable = false)
	private hospitalVO hospital;

	// One-to-Many Mapping for Appointments
	@OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<appointmentsVO> appointments;

	// Many-to-One Relationship with Specialization
	@ManyToOne
	@JoinColumn(name = "specialization_id", nullable = false)
	private specializationVO specialization;

	// Getters and Setters methods
	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
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

	public String getDoctorPhone() {
		return doctorPhone;
	}

	public void setDoctorPhone(String doctorPhone) {
		this.doctorPhone = doctorPhone;
	}

	public String getDoctorEmail() {
		return doctorEmail;
	}

	public void setDoctorEmail(String doctorEmail) {
		this.doctorEmail = doctorEmail;
	}

	public String getDoctorPassword() {
		return doctorPassword;
	}

	public void setDoctorPassword(String doctorPassword) {
		this.doctorPassword = doctorPassword;
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

	public hospitalVO getHospital() {
		return hospital;
	}

	public void setHospital(hospitalVO hospital) {
		this.hospital = hospital;
	}

	public List<appointmentsVO> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<appointmentsVO> appointments) {
		this.appointments = appointments;
	}

	public specializationVO getSpecialization() {
		return specialization;
	}

	public void setSpecialization(specializationVO specialization) {
		this.specialization = specialization;
	}

	// ToString:
	@Override
	public String toString() {
		return "doctorVO [doctorId=" + doctorId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", doctorPhone=" + doctorPhone + ", doctorEmail=" + doctorEmail + ", doctorPassword=" + doctorPassword
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

}
