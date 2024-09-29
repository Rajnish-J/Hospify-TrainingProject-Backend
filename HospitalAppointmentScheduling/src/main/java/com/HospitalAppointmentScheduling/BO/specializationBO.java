package com.HospitalAppointmentScheduling.BO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.HospitalAppointmentScheduling.DAO.specializationRepo;

@Component
public class specializationBO {

	@Autowired
	specializationRepo specializationRepo;
}
