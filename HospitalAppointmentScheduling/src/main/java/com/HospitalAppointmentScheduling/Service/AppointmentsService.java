package com.HospitalAppointmentScheduling.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.HospitalAppointmentScheduling.BO.AppointmentsBO;

@Component
public class AppointmentsService {

	@Autowired
	AppointmentsBO appointmentsBO;
}
