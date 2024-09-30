package com.HospitalAppointmentScheduling.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.HospitalAppointmentScheduling.BO.hospitalBO;

@Component
public class hospitalService {
	@Autowired
	hospitalBO hospitalBO;
}
