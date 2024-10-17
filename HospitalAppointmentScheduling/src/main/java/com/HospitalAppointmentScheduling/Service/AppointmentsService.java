package com.HospitalAppointmentScheduling.Service;

import java.time.LocalDate;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HospitalAppointmentScheduling.BO.AppointmentsBO;
import com.HospitalAppointmentScheduling.CustomExceptions.AppointmentException;
import com.HospitalAppointmentScheduling.CustomExceptions.DateException;
import com.HospitalAppointmentScheduling.CustomExceptions.EmailException;
import com.HospitalAppointmentScheduling.CustomExceptions.IdException;
import com.HospitalAppointmentScheduling.CustomExceptions.PasswordException;
import com.HospitalAppointmentScheduling.CustomExceptions.PhoneNumberException;
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
	public ResponseHandleAppointments insertAppointments(AppointmentsVO vo)
			throws IdException, EmailException, PasswordException, PhoneNumberException, AppointmentException {
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
	public ResponseHandleAppointments insertAppointmentsWithPatientID(AppointmentsVO vo)
			throws IdException, EmailException, PasswordException, PhoneNumberException, AppointmentException {
		log.info("Appointments insert method triggered");
		AppointmentsVO flag = apptBO.insertAppointmentsWithPatientID(vo);
		log.info("insertAppointments method - Appointment insertion logic executed");
		if (flag != null) {
			log.info("Appointment insertion successful");
			apptsRes.setSucessMessage("Appointments added successfully");
			apptsRes.setAppoVo(vo);
		} else {
			apptsRes.setFailureMessage("Error in inserting appointment details");
		}
		log.info("insertAppointments method - END");
		return apptsRes;
	}

	// fetchByID method:
	@Transactional
	public ResponseHandleAppointments fetchByID(Long id) throws IdException {
		log.info("Fetch by ID method triggered...");
		AppointmentsVO vo = apptBO.fetchByID(id);
		log.info("insertAppointmentsWithPatientID method - Appointment insertion logic executed");
		if (vo != null) {
			log.info("Appointment insertion with patient ID successful");
			apptsRes.setSucessMessage("Appointments fetched successfully");
			apptsRes.setAppoVo(vo);
		} else {
			apptsRes.setFailureMessage("Error in fetching appointment details");
		}
		log.info("insertAppointmentsWithPatientID method - END");
		return apptsRes;
	}

	// fetchAll method:
	@Transactional
	public ResponseHandleAppointments fetchAll() {
		log.info("Appointment fetch by ID successful");
		log.info("fetch all method triggered...");
		List<AppointmentsVO> list = apptBO.fetchAll();
		if (list.size() > 0) {
			apptsRes.setSucessMessage("all the appointments fetched successfully");
			apptsRes.setList(list);
		} else {
			apptsRes.setFailureMessage("Error in fetching appointment records");
		}
		log.info("fetchAll method - END");
		return apptsRes;
	}

	// update method:
	@Transactional
	public ResponseHandleAppointments update(Long id) throws IdException {
		log.info("Appointments update method triggered");
		AppointmentsVO vo = apptBO.updateAppointmentDetails(id);
		log.info("update method - Updating appointment details executed");
		if (vo != null) {
			log.info("Appointment update successful");
			apptsRes.setSucessMessage("Appointment updated succuessfully");
			apptsRes.setAppoVo(vo);
		} else {
			apptsRes.setFailureMessage("Error in updating appointment details");
		}
		log.info("update method - END");
		return apptsRes;
	}

	// fetching appt details between two dates
	@Transactional
	public ResponseHandleAppointments fetchApptBetweenTwoDates(LocalDate sd, LocalDate ld) throws DateException {
		log.info("Appointments details by between two dates method triggered");
		List<AppointmentsVO> list = apptBO.fetchApptBetweenTwoDates(sd, ld);
		log.info("fetchApptBetweenTwoDates method - Fetching appointments between dates executed");
		if (list.size() > 0) {
			log.info("Fetching appointments between dates successful");
			apptsRes.setSucessMessage("all the appointments fetched");
			apptsRes.setList(list);
		} else {
			apptsRes.setFailureMessage("Error in fetching");
		}
		log.info("fetchApptBetweenTwoDates method - END");
		return apptsRes;
	}

	// ascending order:
	public ResponseHandleAppointments acending() throws AppointmentException {
		log.info("Fetching the patient details in ascending order method triggered...");
		List<AppointmentsVO> list = apptBO.ascending();
		log.info("acending method - Fetching patient details in ascending order executed");
		if (list.size() > 0) {
			log.info("Fetching patient details in ascending order successful");
			apptsRes.setList(list);
			apptsRes.setSucessMessage("fetching the paitent details in ascending order is successfully executed");
		} else {
			apptsRes.setFailureMessage("Error in fetching...");
		}
		log.info("acending method - END");
		return apptsRes;
	}
}