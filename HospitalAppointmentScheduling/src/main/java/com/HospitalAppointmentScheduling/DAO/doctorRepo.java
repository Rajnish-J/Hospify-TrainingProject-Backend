package com.HospitalAppointmentScheduling.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HospitalAppointmentScheduling.Entity.doctorVO;

@Repository
public interface doctorRepo extends JpaRepository<doctorVO, Long> {

}
