package com.HospitalAppointmentScheduling.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "appointments")
@EntityListeners(AuditingEntityListener.class)
@NamedQueries({
		@NamedQuery(name = "AppointmentsVO.findAllByPatientIdOrderByDate", query = "SELECT a FROM AppointmentsVO a ORDER BY a.appointmentDate ASC") })

public class AppointmentsVO {

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

	// Mapping to doctor
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "doctor_id", nullable = false)
	private DoctorVO doctor;

	// mapping to patient
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "patient_id", nullable = false)
	private PatientVO patient;

	// mapping to appointment status
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "status_id", nullable = false)
	private AppointmentStatusVO status;

	// Getters and Setters method
	public Long getAppointmentID() {
		return appointmentID;
	}

	public void setAppointmentID(Long appointmentID) {
		this.appointmentID = appointmentID;
	}

	public DoctorVO getDoctor() {
		return doctor;
	}

	public void setDoctor(DoctorVO doctor) {
		this.doctor = doctor;
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

	public AppointmentStatusVO getStatus() {
		return status;
	}

	public void setStatus(AppointmentStatusVO status) {
		this.status = status;
	}

	// ToString method:
	@Override
	public String toString() {
		return "AppointmentsVO [appointmentID=" + appointmentID + ", appointmentDate=" + appointmentDate + ", reason="
				+ reason + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", doctor=" + doctor + ", patient="
				+ patient + ", status=" + status + "]";
	}

}
