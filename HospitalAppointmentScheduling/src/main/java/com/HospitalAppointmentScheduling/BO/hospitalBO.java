package com.HospitalAppointmentScheduling.BO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.HospitalAppointmentScheduling.DAO.hospitalRepo;

@Component
public class hospitalBO {

	@Autowired
	hospitalRepo hospitalRepo;
}
