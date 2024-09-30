package com.HospitalAppointmentScheduling.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HospitalAppointmentScheduling.BO.hospitalBO;

@Service
public class hospitalService {
	@Autowired
	hospitalBO hospitalBO;
}
