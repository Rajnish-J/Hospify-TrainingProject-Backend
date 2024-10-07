package com.HospitalAppointmentScheduling.BO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.HospitalAppointmentScheduling.DAO.HospitalRepo;

@Component
public class HospitalBO {

	@Autowired
	HospitalRepo hospitalRepo;
}
