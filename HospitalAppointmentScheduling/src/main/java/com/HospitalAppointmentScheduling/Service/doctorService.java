package com.HospitalAppointmentScheduling.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HospitalAppointmentScheduling.BO.doctorBO;

@Service
public class doctorService {
	@Autowired
	doctorBO doctorBO;
}
