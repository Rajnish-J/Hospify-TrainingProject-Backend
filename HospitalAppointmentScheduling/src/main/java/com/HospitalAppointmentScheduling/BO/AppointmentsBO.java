package com.HospitalAppointmentScheduling.BO;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.HospitalAppointmentScheduling.CustomExceptions.DateException;
import com.HospitalAppointmentScheduling.CustomExceptions.IdException;
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
	public AppointmentsVO insertAppointments(AppointmentsVO vo) throws IdException {
		appointmentsRepo.save(vo);
		return vo;
	}

	public AppointmentsVO insertAppointmentsWithPatientID(AppointmentsVO vo) throws IdException {
		if (vo.getPatient().getPatientId() != null && validateID(vo.getPatient().getPatientId())) {
			PatientVO pvo = pRepo.findById(vo.getPatient().getPatientId()).get();
			vo.setPatient(pvo);
			appointmentsRepo.save(vo);
		}
		return vo;
	}

	// fetchById method:
	public AppointmentsVO fetchByID(Long id) throws IdException {
		if (validateID(id)) {
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
		if (validateID(id)) {
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

	// validations

	// checks the ID Checking
	public boolean validateID(Long id) throws IdException {
		List<Long> pID = pRepo.fetchPatientId();
		System.out.println(pID);

		boolean contains = false;
		for (Long obj : pID) {
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
