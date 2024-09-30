package com.HospitalAppointmentScheduling.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.HospitalAppointmentScheduling.BO.AppointmentStatusBO;

@Component
public class AppointmentStatusService {
	@Autowired
	AppointmentStatusBO appointmentStatusBO;
}
