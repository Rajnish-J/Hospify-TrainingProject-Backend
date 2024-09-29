package com.HospitalAppointmentScheduling.BO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.HospitalAppointmentScheduling.DAO.AppointmentStatusRepo;

@Component
public class AppointmentStatusBO {

	@Autowired
	AppointmentStatusRepo appointmentStatus;
}
