package com.HospitalAppointmentScheduling.Service;

import java.time.LocalDate;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HospitalAppointmentScheduling.BO.AppointmentsBO;
import com.HospitalAppointmentScheduling.CustomExceptions.DateException;
import com.HospitalAppointmentScheduling.CustomExceptions.IdException;
import com.HospitalAppointmentScheduling.Entity.AppointmentsVO;
import com.HospitalAppointmentScheduling.Response.ResponseHandleAppointments;

import jakarta.transaction.Transactional;

@Service
public class AppointmentsService {

	Logger log = Logger.getLogger(AppointmentsService.class);

	@Autowired
	AppointmentsBO apptBO;

	@Autowired
	ResponseHandleAppointments apptsRes;

	// Insert method:
	@Transactional
	public ResponseHandleAppointments insertAppointments(AppointmentsVO vo) throws IdException {
		log.info("Appointments insert method triggered");
		AppointmentsVO flag = apptBO.insertAppointments(vo);
		if (flag != null) {
			apptsRes.setSucessMessage("Appointments added successfully");
			apptsRes.setAppoVo(vo);
		} else {
			apptsRes.setFailureMessage("Error in inserting appointment details");
		}
		return apptsRes;
	}

	@Transactional
	public ResponseHandleAppointments insertAppointmentsWithPatientID(AppointmentsVO vo) throws IdException {
		log.info("Appointments insert method triggered");
		AppointmentsVO flag = apptBO.insertAppointmentsWithPatientID(vo);
		if (flag != null) {
			apptsRes.setSucessMessage("Appointments added successfully");
			apptsRes.setAppoVo(vo);
		} else {
			apptsRes.setFailureMessage("Error in inserting appointment details");
		}
		return apptsRes;
	}

	// fetchByID method:
	@Transactional
	public ResponseHandleAppointments fetchByID(Long id) throws IdException {
		log.info("Fetch by ID method triggered...");
		AppointmentsVO vo = apptBO.fetchByID(id);
		if (vo != null) {
			apptsRes.setSucessMessage("Appointments fetched successfully");
			apptsRes.setAppoVo(vo);
		} else {
			apptsRes.setFailureMessage("Error in fetching appointment details");
		}
		return apptsRes;
	}

	// fetchAll method:
	@Transactional
	public ResponseHandleAppointments fetchAll() {
		log.info("fetch all method triggered...");
		List<AppointmentsVO> list = apptBO.fetchAll();
		if (list.size() > 0) {
			apptsRes.setSucessMessage("all the appointments fetched successfully");
			apptsRes.setList(list);
		} else {
			apptsRes.setFailureMessage("Error in fetching appointment records");
		}
		return apptsRes;
	}

	// update method:
	@Transactional
	public ResponseHandleAppointments update(Long id) throws IdException {
		AppointmentsVO vo = apptBO.updateAppointmentDetails(id);
		if (vo != null) {
			apptsRes.setSucessMessage("Appointment updated succuessfully");
			apptsRes.setAppoVo(vo);
		} else {
			apptsRes.setFailureMessage("Error in updating appointment details");
		}
		return apptsRes;
	}

	// fetching appt details between two dates
	@Transactional
	public ResponseHandleAppointments fetchApptBetweenTwoDates(LocalDate sd, LocalDate ld) throws DateException {
		List<AppointmentsVO> list = apptBO.fetchApptBetweenTwoDates(sd, ld);
		if (list.size() > 0) {
			apptsRes.setSucessMessage("all the appointments fetched");
			apptsRes.setList(list);
		} else {
			apptsRes.setFailureMessage("Error in fetching");
		}
		return apptsRes;
	}
}
