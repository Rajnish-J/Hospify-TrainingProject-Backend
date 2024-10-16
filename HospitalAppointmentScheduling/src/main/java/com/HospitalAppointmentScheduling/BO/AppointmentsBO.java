package com.HospitalAppointmentScheduling.BO;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.HospitalAppointmentScheduling.CustomExceptions.AppointmentException;
import com.HospitalAppointmentScheduling.CustomExceptions.DateException;
import com.HospitalAppointmentScheduling.CustomExceptions.EmailException;
import com.HospitalAppointmentScheduling.CustomExceptions.IdException;
import com.HospitalAppointmentScheduling.CustomExceptions.PasswordException;
import com.HospitalAppointmentScheduling.CustomExceptions.PhoneNumberException;
import com.HospitalAppointmentScheduling.DAO.AppointmentsRepo;
import com.HospitalAppointmentScheduling.DAO.PatientRepo;
import com.HospitalAppointmentScheduling.Entity.AppointmentsVO;
import com.HospitalAppointmentScheduling.Entity.PatientVO;

@Component
public class AppointmentsBO {

	@Autowired
	AppointmentsRepo appointmentsRepo;

	@Autowired
	PatientRepo pRepo;

	// Insert method:
	public AppointmentsVO insertAppointments(AppointmentsVO vo)
			throws IdException, EmailException, PasswordException, PhoneNumberException, AppointmentException {
		if (validateEmail(vo.getPatient().getPatientEmail()) && validatePassword(vo.getPatient().getPatientPassword())
				&& validatePhoneNumber(vo.getPatient().getPatientPhone())) {
			appointmentsRepo.save(vo);
			return vo;
		} else {
			throw new AppointmentException("ERROR: in creating appointments");
		}

	}

	public AppointmentsVO insertAppointmentsWithPatientID(AppointmentsVO vo)
			throws IdException, EmailException, PasswordException, PhoneNumberException, AppointmentException {
		if (vo.getPatient().getPatientId() != null && validatePatID(vo.getPatient().getPatientId())) {
			PatientVO pvo = pRepo.findById(vo.getPatient().getPatientId()).get();
			vo.setPatient(pvo);
			if (validateEmail(vo.getPatient().getPatientEmail())
					&& validatePassword(vo.getPatient().getPatientPassword())
					&& validatePhoneNumber(vo.getPatient().getPatientPhone())) {
				appointmentsRepo.save(vo);
			}
			return vo;
		} else {
			throw new AppointmentException("ERROR: in creating appointments");
		}
	}

	// fetchById method:
	public AppointmentsVO fetchByID(Long id) throws IdException {
		if (validateApptID(id)) {
			AppointmentsVO ret = appointmentsRepo.findById(id).get();
			return ret;
		}
		return null;

	}

	// fetchAll method:
	public List<AppointmentsVO> fetchAll() {
		return appointmentsRepo.findAll();
	}

	public AppointmentsVO updateAppointmentDetails(Long id) throws IdException {
		if (validateApptID(id)) {
			AppointmentsVO vo = appointmentsRepo.findById(id).get();
			vo.setReason("Cured");
			vo = appointmentsRepo.save(vo);
			return vo;
		}
		return null;

	}

	// Appointment by between two days:
	public List<AppointmentsVO> fetchApptBetweenTwoDates(LocalDate sd, LocalDate ld) throws DateException {
		List<AppointmentsVO> list = appointmentsRepo.findAllByAppointmentDateRange(sd, ld);
		if (sd.isAfter(ld)) {
			throw new DateException("start date could be before the end date");
		}
		return list;
	}

	// ascending order:
	public List<AppointmentsVO> ascending() throws AppointmentException {
		List<AppointmentsVO> list = appointmentsRepo.fetchApptsAscending();
		if (!(list.size() > 0)) {
			throw new AppointmentException("ERROR: There is no Records in the DataBase");
		}
		return list;
	}

	// validations

	// validation methods:
	public boolean validatePhoneNumber(String phoneNumber) throws PhoneNumberException {
		if (phoneNumber == null || phoneNumber.length() != 10) {
			throw new PhoneNumberException("ERROR: The phone number lenght is atleast 10");
		}
		for (char c : phoneNumber.toCharArray()) {
			if (!Character.isDigit(c)) {
				throw new PhoneNumberException("ERROR: phone number does not have any alphabets");
			}
		}
		return true;
	}

	// validation for email
	public boolean validateEmail(String email) throws EmailException {
		if (email == null || email.isEmpty()) {
			throw new EmailException("ERROR: Email field could not be empty");
		}
		int atCount = 0;
		for (char c : email.toCharArray()) {
			if (c == '@') {
				atCount++;
				if (atCount == 1) {
					break;
				}
			}
		}
		if (atCount == 0) {
			throw new EmailException("ERROR: Email should contain atleast one " + "@" + " charactre in it");
		}
		return atCount == 1;
	}

	// validations for password
	public boolean validatePassword(String password) throws PasswordException {

		if (password == null) {
			throw new PasswordException("ERROR: password field could not be empty");
		} else if (password.length() < 8 || password.length() > 12) {
			throw new PasswordException("ERROR: password length in between 8 to 12");
		}

		boolean hasUppercase = false;
		boolean hasLowercase = false;
		boolean hasDigit = false;
		boolean hasSpecial = false;

		for (char c : password.toCharArray()) {
			if (Character.isUpperCase(c)) {
				hasUppercase = true;
			} else if (Character.isLowerCase(c)) {
				hasLowercase = true;
			} else if (Character.isDigit(c)) {
				hasDigit = true;
			} else {
				hasSpecial = true;
			}
		}
		if (!(hasUppercase)) {
			throw new PasswordException("ERROR: password could have atleast one Uppercase letter");
		}
		if (!(hasLowercase)) {
			throw new PasswordException("ERROR: password could have atleast one Lowercase letter");
		}
		if (!(hasDigit)) {
			throw new PasswordException("ERROR: password could have atleast one Digit");
		}
		if (!(hasSpecial)) {
			throw new PasswordException("ERROR: password could have atleast one Special character");
		}

		return hasUppercase && hasLowercase && hasDigit && hasSpecial;
	}

	// checks the Patient ID Checking
	public boolean validatePatID(Long id) throws IdException {
		List<Long> pID = pRepo.fetchPatientId();

		boolean contains = false;
		for (Long obj : pID) {
			if (obj == id) {
				contains = true;
				break;
			}
		}
		if (!contains) {
			throw new IdException("ERROR: Patient ID not exist in the database");
		}
		if (id == null) {
			throw new IdException("ERROR: Patient Id field could not be null");
		} else if (id <= 0) {
			throw new IdException("ERROR: Patient ID could not be negative or zero");
		}

		return id != null && id > 0;
	}

	// checks the Patient ID Checking
	public boolean validateApptID(Long id) throws IdException {
		List<Long> aID = appointmentsRepo.fetchAppointmentIds();

		boolean contains = false;
		for (Long obj : aID) {
			if (obj == id) {
				contains = true;
				break;
			}
		}
		if (!contains) {
			throw new IdException("ERROR: appointment ID not exist in the database");
		}
		if (id == null) {
			throw new IdException("ERROR: appointment Id field could not be null");
		} else if (id <= 0) {
			throw new IdException("ERROR: appointment ID could not be negative or zero");
		}

		return id != null && id > 0;
	}

}
