package com.HospitalAppointmentScheduling.BO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.HospitalAppointmentScheduling.DAO.appointmentsRepo;

@Component
public class appointmentsBO {

	@Autowired
	appointmentsRepo appointmentsRepo;
}
