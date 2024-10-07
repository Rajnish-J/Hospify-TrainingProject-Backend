package com.HospitalAppointmentScheduling.Entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "country")
@EntityListeners(AuditingEntityListener.class)
public class CountryVO {

	@Id
	@Column(name = "country_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int countryId;

	@Column(name = "Country", nullable = false, unique = true)
	private String country;

	// Getters and Setters methods
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

	// ToString method
	@Override
	public String toString() {
		return "countryVO [countryId=" + countryId + ", country=" + country + "]";
	}

}
