package com.HospitalAppointmentScheduling.DTO;

public class StateDTO {
	private int stateId;
	private String state;

	// Getters and Setters
	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "StateDTO [stateId=" + stateId + ", state=" + state + "]";
	}
}
