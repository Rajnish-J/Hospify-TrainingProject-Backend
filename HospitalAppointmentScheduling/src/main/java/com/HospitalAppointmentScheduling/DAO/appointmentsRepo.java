package com.HospitalAppointmentScheduling.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HospitalAppointmentScheduling.Entity.appointmentsVO;

@Repository
public interface appointmentsRepo extends JpaRepository<appointmentsVO, Long> {

}
