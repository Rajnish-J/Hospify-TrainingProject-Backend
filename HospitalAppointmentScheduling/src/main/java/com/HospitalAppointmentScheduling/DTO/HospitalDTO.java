package com.HospitalAppointmentScheduling.DTO;

import java.time.LocalDateTime;
import java.util.List;

public class HospitalDTO {
	private Long hospitalId;
	private String hospitalName;
	private String hospitalPhone;
	private String hospitalEmail;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private CityDTO city;
	private StateDTO state;
	private CountryDTO country;
	private List<DoctorDTO> doctors;

	public Long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getHospitalPhone() {
		return hospitalPhone;
	}

	public void setHospitalPhone(String hospitalPhone) {
		this.hospitalPhone = hospitalPhone;
	}

	public String getHospitalEmail() {
		return hospitalEmail;
	}

	public void setHospitalEmail(String hospitalEmail) {
		this.hospitalEmail = hospitalEmail;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public CityDTO getCity() {
		return city;
	}

	public void setCity(CityDTO city) {
		this.city = city;
	}

	public StateDTO getState() {
		return state;
	}

	public void setState(StateDTO state) {
		this.state = state;
	}

	public CountryDTO getCountry() {
		return country;
	}

	public void setCountry(CountryDTO country) {
		this.country = country;
	}

	public List<DoctorDTO> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<DoctorDTO> doctors) {
		this.doctors = doctors;
	}

	@Override
	public String toString() {
		return "HospitalDTO [hospitalId=" + hospitalId + ", hospitalName=" + hospitalName + ", hospitalPhone="
				+ hospitalPhone + ", hospitalEmail=" + hospitalEmail + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + ", city=" + city + ", state=" + state + ", country=" + country + ", doctors=" + doctors
				+ "]";
	}

}
