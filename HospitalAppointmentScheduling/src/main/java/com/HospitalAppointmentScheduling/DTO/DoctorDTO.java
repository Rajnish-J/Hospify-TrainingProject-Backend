package com.HospitalAppointmentScheduling.DTO;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.HospitalAppointmentScheduling.Entity.AppointmentsVO;
import com.HospitalAppointmentScheduling.Entity.HospitalVO;
import com.HospitalAppointmentScheduling.Entity.SpecializationVO;

@Component
public class DoctorDTO {
	private Long doctorId;
	private String firstName;
	private String lastName;
	private String doctorPhone;
	private String doctorEmail;
	private String doctorPassword;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private HospitalVO hospital;
	private List<AppointmentsVO> appointments;
	private SpecializationVO specialization;

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

	public HospitalVO getHospital() {
		return hospital;
	}

	public void setHospital(HospitalVO hospital) {
		this.hospital = hospital;
	}

	public List<AppointmentsVO> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<AppointmentsVO> appointments) {
		this.appointments = appointments;
	}

	public SpecializationVO getSpecialization() {
		return specialization;
	}

	public void setSpecialization(SpecializationVO specialization) {
		this.specialization = specialization;
	}

	@Override
	public String toString() {
		return "DoctorDTO [doctorId=" + doctorId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", doctorPhone=" + doctorPhone + ", doctorEmail=" + doctorEmail + ", doctorPassword=" + doctorPassword
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", hospital=" + hospital + ", appointments="
				+ appointments + ", specialization=" + specialization + "]";
	}

}
