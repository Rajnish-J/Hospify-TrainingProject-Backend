package com.HospitalAppointmentScheduling.DAO;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.HospitalAppointmentScheduling.Entity.PatientVO;

@Repository
public interface PatientRepo extends JpaRepository<PatientVO, Long> {

	// patient authentication for the login page in the console
	@Query("SELECT p FROM PatientVO p WHERE p.patientEmail = :email AND p.patientPassword = :password")
	PatientVO patientAuthentication(@Param("email") String email, @Param("password") String password);

	@Query("SELECT COUNT(p) > 0 FROM PatientVO p WHERE p.patientEmail = :email")
	boolean existsByEmail(@Param("email") String email);

	@Query("SELECT COUNT(p) > 0 FROM PatientVO p WHERE p.patientPhone = :phone")
	boolean existsByPhone(@Param("phone") String phone);
//	================================================================================ //

	// fetch by phone number
	@Query("SELECT p FROM PatientVO p WHERE p.patientPhone = :phone")
	PatientVO findByPhoneNumber(@Param("phone") String phone);

	// fetching patient who all are having appointments today:
	@Query("SELECT p FROM PatientVO p JOIN p.appointments a WHERE a.appointmentDate = :today")
	List<PatientVO> findPatientsWithAppointmentsDay(@Param("today") LocalDate today);

	// fetching the last name and first name of the patient with patient ID using
	// projection interfaceL
	@Query("SELECT p.patientId AS patientId, p.firstName AS firstName, p.lastName AS lastName "
			+ "FROM PatientVO p WHERE p.patientId = :patientId")
	PatientProjection findNameOfPatientById(@Param("patientId") Long patientId);

	// fetching all the patients details who all are having DOB between given two
	// numbers:
	@Query(name = "AppointmentsVO.findByDOBRange")
	List<PatientVO> fetchBetweenDOBpat(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

	// fetching all the patient details in ascending order:
	@Query(name = "PatientVO.findAllOrderedByAttendance")
	List<PatientVO> fetchAscending();

	// fetching all patients id for validation:
	@Query("SELECT p.patientId AS patientId FROM PatientVO p ")
	List<Long> fetchPatientId();

	// fetching all patients phone number for validation:
	@Query("SELECT p.patientPhone AS patientPhone FROM PatientVO p ")
	List<String> fetchPatientPhoneNumber();

	// fetching the common date of births in the database
	@Query("SELECT p.dob FROM PatientVO p GROUP BY p.dob ORDER BY COUNT(p) DESC")
	List<LocalDate> findMostCommonDOB();

	// Finds the ID of the patient who has the highest number of appointments.
	@Query("SELECT p FROM PatientVO p JOIN p.appointments a GROUP BY p.patientId ORDER BY COUNT(a) DESC")
	List<PatientVO> findPatientWithMostAppointments();

	// Retrieves the total count of patients in the Patients table.
	@Query("SELECT COUNT(p) FROM PatientVO p")
	Long findTotalPatientsCount();

}
