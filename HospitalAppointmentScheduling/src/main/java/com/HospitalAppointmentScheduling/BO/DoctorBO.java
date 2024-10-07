package com.HospitalAppointmentScheduling.BO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.HospitalAppointmentScheduling.DAO.DoctorRepo;

@Component
public class DoctorBO {
	@Autowired
	DoctorRepo doctorRepo;
}
