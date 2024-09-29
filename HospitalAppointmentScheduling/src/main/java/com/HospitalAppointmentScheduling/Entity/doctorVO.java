package com.HospitalAppointmentScheduling.Entity;

import java.sql.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	private Long doctor_id;

	@Column(name = "first_name", nullable = false)
	private String first_name;

	@Column(name = "last_name", nullable = false)
	private String last_name;

	@Column(name = "doc_phone", nullable = false, unique = true)
	private String doctor_phone;

	@Column(name = "doc_email", nullable = false, unique = true)
	private String doctor_email;

	@Column(name = "doc_password", nullable = false, unique = true)
	private String doctor_password;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdAt")
	private Date createdAt;

	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updatedAt")
	private Date updatedAt;

	// mapping
	// Many-to-One Relationship with Hospital
	@ManyToOne
	@JoinColumn(name = "hospital_id", nullable = false)
	private hospitalVO hospital;

	// Many-to-One Relationship with Specialization
	@ManyToOne
	@JoinColumn(name = "specialization_id", nullable = false)
	private specializationVO specialization;

	// Getters and Setters methods
	public Long getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(Long doctor_id) {
		this.doctor_id = doctor_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getDoctor_phone() {
		return doctor_phone;
	}

	public void setDoctor_phone(String doctor_phone) {
		this.doctor_phone = doctor_phone;
	}

	public String getDoctor_email() {
		return doctor_email;
	}

	public void setDoctor_email(String doctor_email) {
		this.doctor_email = doctor_email;
	}

	public String getDoctor_password() {
		return doctor_password;
	}

	public void setDoctor_password(String doctor_password) {
		this.doctor_password = doctor_password;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public hospitalVO getHospital() {
		return hospital;
	}

	public void setHospital(hospitalVO hospital) {
		this.hospital = hospital;
	}

	public specializationVO getSpecialization() {
		return specialization;
	}

	public void setSpecialization(specializationVO specialization) {
		this.specialization = specialization;
	}

	// ToString Method
	@Override
	public String toString() {
		return "doctorVO [doctor_id=" + doctor_id + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", doctor_phone=" + doctor_phone + ", doctor_email=" + doctor_email + ", doctor_password="
				+ doctor_password + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", hospital=" + hospital
				+ ", specialization=" + specialization + "]";
	}

}
