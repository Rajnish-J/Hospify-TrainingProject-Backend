package com.HospitalAppointmentScheduling.BO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.HospitalAppointmentScheduling.DAO.patientRepo;
import com.HospitalAppointmentScheduling.Entity.patientVO;

@Component
public class patientBO {

	@Autowired
	patientRepo patientRepo;

	// Insert method:
	public Long insertPatientDetails(patientVO vo) {
		patientRepo.save(vo);
		return vo.getPatientId();
	}

	// FindByID method:
	public patientVO fetchByID(Long id) {
		Optional<patientVO> patientOpt = patientRepo.findById(id);
		patientVO ret = patientOpt.get();
		return ret;

	}

	// fetchAll method:
	public List<patientVO> fetchAll() {
		return patientRepo.findAll();
	}

	// update method:
	public boolean updatePatientDetails(long id) {
		patientVO vo = patientRepo.findById(id).get();
		vo.setLastName("Jai");
		patientRepo.save(vo);
		return true;
	}
}
