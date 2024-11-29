package com.HospitalAppointmentScheduling.DAO;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.HospitalAppointmentScheduling.Entity.AppointmentsVO;

@Repository
public interface AppointmentsRepo extends JpaRepository<AppointmentsVO, Long> {
	// fetching all patients id for validation:
	@Query("SELECT a.appointmentID AS appointmentID FROM AppointmentsVO a ")
	List<Long> fetchAppointmentIds();

	// Find Appoinment in the Given Date Range
	@Query("SELECT a FROM AppointmentsVO a WHERE a.appointmentDate BETWEEN :startDate AND :endDate")
	List<AppointmentsVO> findAllByAppointmentDateRange(@Param("startDate") LocalDate startDate,
			@Param("endDate") LocalDate endDate);

	// Appointments by order date
	@Query(name = "AppointmentsVO.findAllByPatientIdOrderByDate")
	List<AppointmentsVO> fetchApptsAscendingDate();

	// ---------------------------------------------------------------------------------
	//
	// used API's

	// fetch all appointments with respect to the patient ID:
	@Query("SELECT a FROM AppointmentsVO a WHERE a.patient.patientId = :patientId")
	List<AppointmentsVO> findAllApptByPatientId(@Param("patientId") long patientId);

	// delete the appointment by the given ID:
	@Modifying
	@Transactional
	@Query("DELETE FROM AppointmentsVO a WHERE a.appointmentID = :appointmentId")
	void deleteByAppointmentId(@Param("appointmentId") long appointmentId);

	// returns the long (number of appointments for the particular date)
	@Query("SELECT COUNT(a) FROM AppointmentsVO a WHERE a.appointmentDate = :appointmentDate")
	long countAppointmentsByDate(@Param("appointmentDate") LocalDate appointmentDate);

}