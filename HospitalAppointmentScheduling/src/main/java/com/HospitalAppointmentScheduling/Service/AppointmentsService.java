package com.HospitalAppointmentScheduling.Service;

import java.time.LocalDate;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HospitalAppointmentScheduling.BO.AppointmentsBO;
import com.HospitalAppointmentScheduling.CustomExceptions.AppointmentBookingDateException;
import com.HospitalAppointmentScheduling.CustomExceptions.AppointmentException;
import com.HospitalAppointmentScheduling.CustomExceptions.DateException;
import com.HospitalAppointmentScheduling.CustomExceptions.DateOfBirthException;
import com.HospitalAppointmentScheduling.CustomExceptions.EmailException;
import com.HospitalAppointmentScheduling.CustomExceptions.IdException;
import com.HospitalAppointmentScheduling.CustomExceptions.PasswordException;
import com.HospitalAppointmentScheduling.CustomExceptions.PatientException;
import com.HospitalAppointmentScheduling.CustomExceptions.PhoneNumberException;
import com.HospitalAppointmentScheduling.CustomExceptions.ReasonException;
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
	public ResponseHandleAppointments insertAppointments(AppointmentsVO vo) throws IdException, EmailException,
			PasswordException, PhoneNumberException, AppointmentException, PatientException, DateOfBirthException {
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
			throws IdException, EmailException, PasswordException, PhoneNumberException, AppointmentException,
			PatientException, DateOfBirthException, AppointmentBookingDateException, ReasonException {
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
	public ResponseHandleAppointments update(AppointmentsVO passedData, Long id) throws IdException {
		log.info("Appointments update method triggered");
		AppointmentsVO vo = apptBO.updateAppointmentDetails(passedData, id);
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

	// delete method:
	public ResponseHandleAppointments deleteAppointment(long id) throws IdException {
		log.info("delete method triggered in the service layer");
		String res = apptBO.deleteAppointment(id);
		if (!res.isEmpty()) {
			String pass = "successfully deleted the appointment details for the appointment ID: " + id;
			log.info(pass);
			apptsRes.setSucessMessage("Deleted successfully");
		} else {
			apptsRes.setFailureMessage("Appointment ID does not exists in the database");
		}
		return apptsRes;

	}

	// fetches all appointments to the respective patient id:
	public ResponseHandleAppointments findAllApptByPatientId(long id) throws IdException {
		log.info("Fetching all the appointments with respect to the patient ID method triggered in the service layer");

		// Create a new instance to avoid stale data
		ResponseHandleAppointments apptsRes = new ResponseHandleAppointments();

		List<AppointmentsVO> list = apptBO.findAllAppointmentsByPatientID(id);
		if (list != null && !list.isEmpty()) {
			String pass = "Successfully fetched the appointment details for the patient ID: " + id;
			log.info(pass);
			apptsRes.setList(list);
			apptsRes.setSucessMessage("All the appointments fetched");
		} else {
			apptsRes.setFailureMessage("No appointments found for the given patient ID");
		}

		return apptsRes;
	}

	// returns the number that the particular date having count
	public ResponseHandleAppointments countOfAppointmentsByDate(LocalDate date) {
		log.info("Appointments on the given date count method triggered");
		long ret = apptBO.countOfAppointmentsByDate(date);
		if (ret > 5) {
			String pass = "Successfully fetched count of the appointments on the date: " + ret;
			log.info(pass);
			apptsRes.setApptsCount(ret);
			apptsRes.setSucessMessage("fetched successfully");
		} else {
			apptsRes.setApptsCount(ret);
			apptsRes.setFailureMessage("lesser count");
		}
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
	public ResponseHandleAppointments acendingDate() throws AppointmentException {
		log.info("Fetching the patient details in ascending order method triggered...");
		List<AppointmentsVO> list = apptBO.ascendingDate();
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