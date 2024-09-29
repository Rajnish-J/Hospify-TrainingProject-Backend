package com.HospitalAppointmentScheduling.BO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.HospitalAppointmentScheduling.DAO.cityRepo;

@Component
public class cityBO {

	@Autowired
	cityRepo cityRepo;
}
