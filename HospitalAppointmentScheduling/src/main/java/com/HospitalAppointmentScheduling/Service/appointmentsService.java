package com.HospitalAppointmentScheduling.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.HospitalAppointmentScheduling.BO.appointmentsBO;

@Component
public class appointmentsService {

	@Autowired
	appointmentsBO appointmentsBO;
}
