package com.HospitalAppointmentScheduling.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HospitalAppointmentScheduling.Entity.AppointmentStatusVO;

public interface AppointmentStatusRepo extends JpaRepository<AppointmentStatusVO, Integer> {

}
