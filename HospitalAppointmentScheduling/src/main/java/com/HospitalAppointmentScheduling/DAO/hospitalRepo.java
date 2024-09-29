package com.HospitalAppointmentScheduling.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HospitalAppointmentScheduling.Entity.hospitalVO;

public interface hospitalRepo extends JpaRepository<hospitalVO, Long> {

}
