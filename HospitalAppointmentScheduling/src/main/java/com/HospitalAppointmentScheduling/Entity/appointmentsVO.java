package com.HospitalAppointmentScheduling.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
@Table(name = "appointments")
@EntityListeners(AuditingEntityListener.class)
public class appointmentsVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "appointment_id")
	private Long appointmentID;

	@Column(name = "appointment_date", nullable = false)
	private LocalDate appointmentDate;

	@Column(name = "reason", nullable = false)
	private String reason;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdAt", nullable = false)
	private LocalDateTime createdAt;

	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updatedAt", nullable = false)
	private LocalDateTime updatedAt;

	// Mapping
	@ManyToOne
	@JoinColumn(name = "doctor_id", nullable = false)
	private doctorVO doctor;

	@ManyToOne
	@JoinColumn(name = "patient_id", nullable = false)
	private patientVO patient;

	@ManyToOne
	@JoinColumn(name = "status_id", nullable = false)
	private AppointmentStatusVO status;

	// Getters and Setters method
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

	// ToString method:
	@Override
	public String toString() {
		return "appointmentsVO [appointmentID=" + appointmentID + ", appointmentDate=" + appointmentDate + ", reason="
				+ reason + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

}
