package com.HospitalAppointmentScheduling.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.HospitalAppointmentScheduling.BO.patientBO;

@Component
public class patientService {
	@Autowired
	patientBO patientBO;
}
