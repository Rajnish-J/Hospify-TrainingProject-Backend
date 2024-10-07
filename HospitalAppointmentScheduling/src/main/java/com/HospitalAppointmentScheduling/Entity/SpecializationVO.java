package com.HospitalAppointmentScheduling.Entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "specialization")
@EntityListeners(AuditingEntityListener.class)
public class SpecializationVO {
	@Id
	@Column(name = "specialization_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer specializationId;

	@Column(name = "specialization", nullable = false, unique = true)
	private String specialtyName;

	// Getters and Setters methods
	public Integer getSpecializationId() {
		return specializationId;
	}

	public void setSpecializationId(Integer specializationId) {
		this.specializationId = specializationId;
	}

	public String getSpecialtyName() {
		return specialtyName;
	}

	public void setSpecialtyName(String specialtyName) {
		this.specialtyName = specialtyName;
	}

	// ToString method
	@Override
	public String toString() {
		return "specializationVO [specializationId=" + specializationId + ", specialtyName=" + specialtyName + "]";
	}

}
