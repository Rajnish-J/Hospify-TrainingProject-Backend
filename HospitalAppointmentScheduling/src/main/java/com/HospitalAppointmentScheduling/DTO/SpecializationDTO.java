package com.HospitalAppointmentScheduling.DTO;

public class SpecializationDTO {

	private int specilizationId;
	private String specilizationName;

	public int getSpecilizationId() {
		return specilizationId;
	}

	public void setSpecilizationId(int specilizationId) {
		this.specilizationId = specilizationId;
	}

	public String getSpecilizationName() {
		return specilizationName;
	}

	public void setSpecilizationName(String specilizationName) {
		this.specilizationName = specilizationName;
	}

	@Override
	public String toString() {
		return "SpecializationDTO [specilizationId=" + specilizationId + ", specilizationName=" + specilizationName
				+ "]";
	}

}
