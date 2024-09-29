package com.HospitalAppointmentScheduling.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HospitalAppointmentScheduling.Entity.doctorVO;

public interface doctorRepo extends JpaRepository<doctorVO, Long> {

}
