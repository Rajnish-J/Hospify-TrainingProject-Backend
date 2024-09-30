package com.HospitalAppointmentScheduling.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.HospitalAppointmentScheduling.BO.countryBO;

@Component
public class countryService {
	@Autowired
	countryBO countryBO;
}
