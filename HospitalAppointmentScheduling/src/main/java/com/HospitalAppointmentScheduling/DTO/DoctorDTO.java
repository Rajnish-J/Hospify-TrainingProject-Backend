package com.HospitalAppointmentScheduling.DTO;

public class DoctorDTO {
	private Long doctorId;
	private String firstName;
	private String lastName;
	private String doctorPhone;
	private String doctorEmail;
	private String doctorPassword;
	private Long hospitalId;
	private int specilizationId;

	// Getters and Setters
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

	public Long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getDoctorPassword() {
		return doctorPassword;
	}

	public void setDoctorPassword(String doctorPassword) {
		this.doctorPassword = doctorPassword;
	}

	public int getSpecilizationId() {
		return specilizationId;
	}

	public void setSpecilizationId(int specilizationId) {
		this.specilizationId = specilizationId;
	}

	@Override
	public String toString() {
		return "DoctorDTO [doctorId=" + doctorId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", doctorPhone=" + doctorPhone + ", doctorEmail=" + doctorEmail + ", doctorPassword=" + doctorPassword
				+ ", hospitalId=" + hospitalId + ", specilizationId=" + specilizationId + "]";
	}

}
