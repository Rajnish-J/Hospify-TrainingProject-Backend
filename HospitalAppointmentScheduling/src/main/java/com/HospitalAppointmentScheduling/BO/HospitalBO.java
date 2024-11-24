package com.HospitalAppointmentScheduling.BO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.HospitalAppointmentScheduling.DAO.HospitalRepo;
import com.HospitalAppointmentScheduling.Entity.HospitalVO;

@Component
public class HospitalBO {

	@Autowired
	private HospitalRepo hosRepo;

	// hospital admin authentication
	public HospitalVO hospitalAuthentication(String email, String password) {
		HospitalVO vo = hosRepo.authenticateHospital(email, password);
		return vo;
	}
}
