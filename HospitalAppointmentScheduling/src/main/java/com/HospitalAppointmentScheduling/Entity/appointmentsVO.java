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
@Table(name = "appointments")
@EntityListeners(AuditingEntityListener.class)
public class appointmentsVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "appointment_id")
	private Long appointmentID;

	@Column(name = "appointment_date", nullable = false)
	private Date appointmentDate;

	@Column(name = "reason", nullable = false)
	private String reason;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdAt", nullable = false)
	private Date createdAt;

	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updatedAt", nullable = false)
	private Date updatedAt;

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

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
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
