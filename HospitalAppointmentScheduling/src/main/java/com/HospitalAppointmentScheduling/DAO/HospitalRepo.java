package com.HospitalAppointmentScheduling.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HospitalAppointmentScheduling.Entity.HospitalVO;

@Repository
public interface HospitalRepo extends JpaRepository<HospitalVO, Long> {

}
