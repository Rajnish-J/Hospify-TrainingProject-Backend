package com.HospitalAppointmentScheduling.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HospitalAppointmentScheduling.BO.CityBO;

@Service
public class CityService {

	@Autowired
	CityBO cityBO;
}
