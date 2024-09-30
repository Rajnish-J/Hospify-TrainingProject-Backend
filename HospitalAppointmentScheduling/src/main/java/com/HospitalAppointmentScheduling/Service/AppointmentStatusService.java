package com.HospitalAppointmentScheduling.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HospitalAppointmentScheduling.BO.AppointmentStatusBO;

@Service
public class AppointmentStatusService {
	@Autowired
	AppointmentStatusBO appointmentStatusBO;
}
