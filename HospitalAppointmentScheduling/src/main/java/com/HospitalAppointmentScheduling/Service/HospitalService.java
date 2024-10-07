package com.HospitalAppointmentScheduling.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HospitalAppointmentScheduling.BO.HospitalBO;

@Service
public class HospitalService {
	@Autowired
	HospitalBO hospitalBO;
}
