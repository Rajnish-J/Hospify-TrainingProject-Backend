package com.HospitalAppointmentScheduling.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.HospitalAppointmentScheduling.BO.doctorBO;

@Component
public class doctorService {
	@Autowired
	doctorBO doctorBO;
}
