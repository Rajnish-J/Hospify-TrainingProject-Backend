package com.HospitalAppointmentScheduling.DTO;

public class CountryDTO {
	private int countryId;
	private String country;

	// Getters and Setters
	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "CountryDTO [countryId=" + countryId + ", country=" + country + "]";
	}
}
