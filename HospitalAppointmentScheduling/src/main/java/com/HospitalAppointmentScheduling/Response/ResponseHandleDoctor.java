package com.HospitalAppointmentScheduling.Response;

import java.util.List;

import org.springframework.stereotype.Component;

import com.HospitalAppointmentScheduling.Entity.DoctorVO;

@Component
public class ResponseHandleDoctor {

	private String sucessMessage;
	private String failureMessage;
	private long id;
	private DoctorVO docVo;
	private List<DoctorVO> list;

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

	public DoctorVO getDocVo() {
		return docVo;
	}

	public void setDocVo(DoctorVO docVo) {
		this.docVo = docVo;
	}

	public List<DoctorVO> getList() {
		return list;
	}

	public void setList(List<DoctorVO> list) {
		this.list = list;
	}

}
