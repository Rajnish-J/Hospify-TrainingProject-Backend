package com.HospitalAppointmentScheduling.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.HospitalAppointmentScheduling.Entity.AppointmentsVO;

@Repository
public interface AppointmentsRepo extends JpaRepository<AppointmentsVO, Long> {
	// fetching all patients id for validation:
	@Query("SELECT a.appointmentID AS appointmentID FROM AppointmentsVO a ")
	List<Long> fetchAppointmentIds();
}