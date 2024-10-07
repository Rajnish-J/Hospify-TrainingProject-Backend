package com.HospitalAppointmentScheduling.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HospitalAppointmentScheduling.BO.DoctorBO;

@Service
public class DoctorService {
	@Autowired
	DoctorBO doctorBO;
}
