package com.HospitalAppointmentScheduling.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HospitalAppointmentScheduling.BO.DoctorBO;
import com.HospitalAppointmentScheduling.DAO.DoctorDetailsProjection;
import com.HospitalAppointmentScheduling.Response.ResponseHandleAppointments;

@Service
public class DoctorService {
	@Autowired
	DoctorBO doctorBO;

	@Autowired
	ResponseHandleAppointments apptsRes;

	public ResponseHandleAppointments fetch() {
		List<DoctorDetailsProjection> list = doctorBO.fetch();
		if (list.size() > 0) {
			apptsRes.setSucessMessage("All doctor's fetched with specilization");
			apptsRes.setListSpecialiaztion(list);

		} else {
			apptsRes.setFailureMessage("error in fetching");
		}

		return apptsRes;
	}
}
