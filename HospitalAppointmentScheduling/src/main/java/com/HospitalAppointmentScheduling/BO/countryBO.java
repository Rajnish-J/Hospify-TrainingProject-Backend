package com.HospitalAppointmentScheduling.BO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.HospitalAppointmentScheduling.DAO.countryRepo;

@Component
public class countryBO {

	@Autowired
	countryRepo countryRepo;
}
