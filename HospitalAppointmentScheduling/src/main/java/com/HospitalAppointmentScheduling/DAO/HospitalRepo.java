package com.HospitalAppointmentScheduling.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.HospitalAppointmentScheduling.Entity.HospitalVO;

@Repository
public interface HospitalRepo extends JpaRepository<HospitalVO, Long> {

	@Query("SELECT h FROM HospitalVO h WHERE h.hospitalEmail = :email AND h.hospitalPhone = :phone")
	HospitalVO authenticateHospital(@Param("email") String email, @Param("phone") String phone);

}
