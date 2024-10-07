package com.HospitalAppointmentScheduling.BO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.HospitalAppointmentScheduling.DAO.AppointmentsRepo;

@Component
public class AppointmentsBO {

	@Autowired
	AppointmentsRepo appointmentsRepo;
}
