package com.HospitalAppointmentScheduling.BO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.HospitalAppointmentScheduling.DAO.stateRepo;

@Component
public class stateBO {

	@Autowired
	stateRepo stateRepo;
}
