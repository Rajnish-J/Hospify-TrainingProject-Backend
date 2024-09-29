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
@Table(name = "city")
@EntityListeners(AuditingEntityListener.class)
public class cityVO {
	@Id
	@Column(name = "city_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cityId;

	@Column(name = "city", nullable = false, unique = true)
	private String city;

	// Getters and Setters method
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

	// ToString method
	@Override
	public String toString() {
		return "cityVO [cityId=" + cityId + ", city=" + city + "]";
	}

}
