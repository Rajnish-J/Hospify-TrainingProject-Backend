package com.HospitalAppointmentScheduling.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HospitalAppointmentScheduling.BO.CountryBO;

@Service
public class CountryService {
	@Autowired
	CountryBO countryBO;
}
