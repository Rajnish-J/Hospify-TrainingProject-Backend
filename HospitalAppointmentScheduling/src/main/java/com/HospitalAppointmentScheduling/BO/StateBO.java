package com.HospitalAppointmentScheduling.BO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.HospitalAppointmentScheduling.DAO.StateRepo;

@Component
public class StateBO {

	@Autowired
	StateRepo stateRepo;
}
