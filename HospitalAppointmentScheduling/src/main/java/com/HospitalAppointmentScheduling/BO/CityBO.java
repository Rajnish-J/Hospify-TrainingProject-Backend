package com.HospitalAppointmentScheduling.BO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.HospitalAppointmentScheduling.DAO.CityRepo;

@Component
public class CityBO {

	@Autowired
	CityRepo cityRepo;
}
