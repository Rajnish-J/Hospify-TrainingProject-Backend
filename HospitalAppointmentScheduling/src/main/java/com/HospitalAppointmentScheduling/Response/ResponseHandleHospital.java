package com.HospitalAppointmentScheduling.Response;

import org.springframework.stereotype.Component;

import com.HospitalAppointmentScheduling.Entity.HospitalVO;

@Component
public class ResponseHandleHospital {

	private String sucessMessage;
	private String failureMessage;
	private long id;
	private HospitalVO hospital;

	public String getSucessMessage() {
		return sucessMessage;
	}

	public void setSucessMessage(String sucessMessage) {
		this.sucessMessage = sucessMessage;
	}

	public String getFailureMessage() {
		return failureMessage;
	}

	public void setFailureMessage(String failureMessage) {
		this.failureMessage = failureMessage;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public HospitalVO getHospital() {
		return hospital;
	}

	public void setHospital(HospitalVO hospital) {
		this.hospital = hospital;
	}

	@Override
	public String toString() {
		return "HospitalControler [sucessMessage=" + sucessMessage + ", failureMessage=" + failureMessage + ", id=" + id
				+ ", hospital=" + hospital + "]";
	}
}
