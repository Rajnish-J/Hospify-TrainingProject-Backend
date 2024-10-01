package com.HospitalAppointmentScheduling.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HospitalAppointmentScheduling.BO.patientBO;
import com.HospitalAppointmentScheduling.CustomExceptions.patientException;
import com.HospitalAppointmentScheduling.DTO.patientDTO;
import com.HospitalAppointmentScheduling.Entity.patientVO;
import com.HospitalAppointmentScheduling.Response.ResponseHandle;

@Service
public class patientService {
	@Autowired
	private patientBO patientBO;

	@Autowired
	private patientDTO patientDTO;

	@Autowired
	private ResponseHandle response;

	// insert method
	public ResponseHandle insertPatientDetails(patientVO vo) throws patientException {
		Long flag = patientBO.insertPatientDetails(vo);
		if (flag > 0) {
			response.setSucessmessage("patient Details added successfully");
			response.setId(flag);
		} else {
			throw new patientException("Error in inserting patient");
		}
		return response;
	}

	// find by Id method:
	public ResponseHandle fetchById(long id) throws patientException {
		patientVO ret = patientBO.fetchByID(id);
		if (ret == null) {
			throw new patientException("There are no patient in the given ID");
		}
		response.setSucessmessage("patient Details fetched successfully");
		response.setPatient(ret);
		return response;
	}

	// fetch all method:
	public ResponseHandle fetchAll() {
		response.setListpatient(patientBO.fetchAll());
		return response;
	}

	// update method
	public ResponseHandle updatePatientDetails(long id) throws patientException {
		boolean flag = patientBO.updatePatientDetails(id);
		if (flag) {
			response.setSucessmessage("updated the patient details successfully");
		} else {
			throw new patientException("error in updating patient details");
		}

		return response;
	}
}
