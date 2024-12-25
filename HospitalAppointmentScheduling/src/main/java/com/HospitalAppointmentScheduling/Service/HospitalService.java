package com.HospitalAppointmentScheduling.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HospitalAppointmentScheduling.BO.HospitalBO;
import com.HospitalAppointmentScheduling.Entity.HospitalVO;
import com.HospitalAppointmentScheduling.Response.ResponseHandleHospital;

@Service
public class HospitalService {
	@Autowired
	private HospitalBO hospitalBO;

	@Autowired
	private ResponseHandleHospital resHos;

//	Logger log = Logger.getLogger(HospitalService.class);

	// hospital admin authentication
	public ResponseHandleHospital hospitalAuthentication(String email, String password) {
		HospitalVO vo = hospitalBO.hospitalAuthentication(email, password);
		if (vo != null) {
			resHos.setSucessMessage("Hospital Admin ID available");
			resHos.setHospital(vo);
//			resHos.setFailureMessage(null);
		} else {
//			resHos.setSucessMessage(null);
			resHos.setFailureMessage("failed to fetch");
		}
		return resHos;
	}
}
