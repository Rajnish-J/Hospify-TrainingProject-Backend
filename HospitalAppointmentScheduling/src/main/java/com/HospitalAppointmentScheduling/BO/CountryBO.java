package com.HospitalAppointmentScheduling.BO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.HospitalAppointmentScheduling.DAO.CountryRepo;

@Component
public class CountryBO {

	@Autowired
	CountryRepo countryRepo;
}
