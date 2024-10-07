package com.HospitalAppointmentScheduling.BO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.HospitalAppointmentScheduling.DAO.SpecializationRepo;

@Component
public class SpecializationBO {

	@Autowired
	SpecializationRepo specializationRepo;
}
