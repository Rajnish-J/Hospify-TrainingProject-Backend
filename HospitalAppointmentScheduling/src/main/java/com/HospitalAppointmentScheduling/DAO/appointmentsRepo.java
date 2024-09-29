package com.HospitalAppointmentScheduling.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HospitalAppointmentScheduling.Entity.appointmentsVO;

public interface appointmentsRepo extends JpaRepository<appointmentsVO, Long> {

}
