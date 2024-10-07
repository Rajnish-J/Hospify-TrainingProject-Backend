package com.HospitalAppointmentScheduling.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HospitalAppointmentScheduling.Entity.AppointmentsVO;

@Repository
public interface AppointmentsRepo extends JpaRepository<AppointmentsVO, Long> {

}
