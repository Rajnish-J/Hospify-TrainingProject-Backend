package com.HospitalAppointmentScheduling.BO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.HospitalAppointmentScheduling.DAO.doctorRepo;

@Component
public class doctorBO {
	@Autowired
	doctorRepo doctorRepo;
}
