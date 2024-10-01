package com.HospitalAppointmentScheduling.DTO;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.HospitalAppointmentScheduling.Entity.appointmentsVO;
import com.HospitalAppointmentScheduling.Entity.hospitalVO;
import com.HospitalAppointmentScheduling.Entity.specializationVO;

@Component
public class doctorDTO {
	private Long doctorId;

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

	private String firstName;
	private String lastName;
	private String doctorPhone;
	private String doctorEmail;
	private String doctorPassword;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private hospitalVO hospital;
	private List<appointmentsVO> appointments;
	private specializationVO specialization;
}
