package Appointment.VO;

import jakarta.persistence.Id;

public class PatientVO {

	@Id
	private int patientId;

	private String FirstName;
	private String LastName;
	private String dob;
	private String PatientPhone;
	private String PatientEmail;
	private String PatientPassword;

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getPatientPhone() {
		return PatientPhone;
	}

	public void setPatientPhone(String patientPhone) {
		PatientPhone = patientPhone;
	}

	public String getPatientEmail() {
		return PatientEmail;
	}

	public void setPatientEmail(String patientEmail) {
		PatientEmail = patientEmail;
	}

	public String getPatientPassword() {
		return PatientPassword;
	}

	public void setPatientPassword(String patientPassword) {
		PatientPassword = patientPassword;
	}
}
