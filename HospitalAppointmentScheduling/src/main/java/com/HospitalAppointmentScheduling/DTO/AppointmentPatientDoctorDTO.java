package com.HospitalAppointmentScheduling.DTO;

public class AppointmentPatientDoctorDTO {
	private AppointmentDTO appointment;
	private PatientDTO patient;
	private DoctorDTO doctor;

	public DoctorDTO getDoctor() {
		return doctor;
	}

	public void setDoctor(DoctorDTO doctor) {
		this.doctor = doctor;
	}

	// Getters and setters
	public AppointmentDTO getAppointment() {
		return appointment;
	}

	public void setAppointment(AppointmentDTO appointment) {
		this.appointment = appointment;
	}

	public PatientDTO getPatient() {
		return patient;
	}

	public void setPatient(PatientDTO patient) {
		this.patient = patient;
	}
}
