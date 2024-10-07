package com.HospitalAppointmentScheduling.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HospitalAppointmentScheduling.Entity.DoctorVO;

@Repository
public interface DoctorRepo extends JpaRepository<DoctorVO, Long> {

}
