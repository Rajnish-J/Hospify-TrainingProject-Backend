package com.HospitalAppointmentScheduling.DTO;

public class AppointmentPatientDoctorAppointmentStatusDTO {
	private AppointmentDTO appointment;
	private PatientDTO patient;
	private DoctorDTO doctor;
	private AppointmentStatusDTO appointmentStatus;

	public AppointmentStatusDTO getAppointmentStatus() {
		return appointmentStatus;
	}

	public void setAppointmentStatus(AppointmentStatusDTO appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}

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
