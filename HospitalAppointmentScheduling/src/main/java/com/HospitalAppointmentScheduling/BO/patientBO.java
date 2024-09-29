package com.HospitalAppointmentScheduling.BO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.HospitalAppointmentScheduling.DAO.patientRepo;

@Component
public class patientBO {

	@Autowired
	patientRepo patientRepo;
}
