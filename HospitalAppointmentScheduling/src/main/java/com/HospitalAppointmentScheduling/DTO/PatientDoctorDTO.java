package com.HospitalAppointmentScheduling.DTO;

public class PatientDoctorDTO {
	private PatientDTO patient;
	private DoctorDTO doctor;

	public PatientDTO getPatient() {
		return patient;
	}

	public void setPatient(PatientDTO patient) {
		this.patient = patient;
	}

	public DoctorDTO getDoctor() {
		return doctor;
	}

	public void setDoctor(DoctorDTO doctor) {
		this.doctor = doctor;
	}

}
