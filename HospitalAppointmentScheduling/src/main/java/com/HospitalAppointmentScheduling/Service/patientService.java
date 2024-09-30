package com.HospitalAppointmentScheduling.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.HospitalAppointmentScheduling.BO.patientBO;
import com.HospitalAppointmentScheduling.CustomExceptions.patientException;
import com.HospitalAppointmentScheduling.Entity.patientVO;

@Component
public class patientService {
	@Autowired
	patientBO patientBO;

	// insert method
	public long insertPatientDetails(patientVO vo) throws patientException {
		Long flag = patientBO.insertPatientDetails(vo);
		if (flag > 0) {
			System.out.println("Patient added succesfully");
		} else {
			throw new patientException("Error in inserting patient");
		}
		return flag;
	}

	// find by Id method:
	public patientVO fetchById(long id) throws patientException {
		patientVO ret = patientBO.fetchByID(id);
		if (ret == null) {
			throw new patientException("There are no patient in the given ID");
		}
		return ret;
	}

	// fetch all method:
	public List<patientVO> fetchAll() {
		return patientBO.fetchAll();
	}

	// update method
	public String updatePatientDetails(long id) throws patientException {
		String str = "";
		boolean flag = patientBO.updatePatientDetails(id);
		if (flag) {
			str = "patient details updated";
		} else {
			throw new patientException("error in updating patient details");
		}

		return str;
	}
}
