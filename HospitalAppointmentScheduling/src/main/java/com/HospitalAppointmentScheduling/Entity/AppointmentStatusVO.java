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
@Table(name = "appointmentstatus")
@EntityListeners(AuditingEntityListener.class)
public class AppointmentStatusVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "status_id")
	private int statusId;

	@Column(name = "StatusName", nullable = false)
	private String statusName;

	// Getters and Setters method
	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	// ToString method
	@Override
	public String toString() {
		return "AppointmentStatusVO [statusId=" + statusId + ", statusName=" + statusName + "]";
	}

}
