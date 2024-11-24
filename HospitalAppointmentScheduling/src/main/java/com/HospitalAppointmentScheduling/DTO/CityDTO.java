package com.HospitalAppointmentScheduling.DTO;

public class CityDTO {
	private int cityId;
	private String city;

	// Getters and Setters
	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "CityDTO [cityId=" + cityId + ", city=" + city + "]";
	}
}
