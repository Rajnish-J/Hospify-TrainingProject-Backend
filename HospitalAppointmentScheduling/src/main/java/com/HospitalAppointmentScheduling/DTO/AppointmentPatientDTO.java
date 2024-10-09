package com.HospitalAppointmentScheduling.DTO;

public class AppointmentPatientDTO {
	private AppointmentDTO appointment;
	private PatientDTO patient;

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
