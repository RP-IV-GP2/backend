package com.example.acquisition.dto;


import com.example.acquisition.model.Acquisition;
import org.modelmapper.ModelMapper;

import java.util.Date;

public class AcquisitionDTO {
	
	private Long id;
	private Date data;
	private Integer amount;
	private Double value;
	private Long idProperty;
	private Long idUser;

	public AcquisitionDTO() {
	}

	public AcquisitionDTO(Long id, Date data, Integer amount, Double value, Long idProperty, Long idUser) {
		this.id = id;
		this.data = data;
		this.value = value;
		this.amount = amount;
		this.idProperty = idProperty;
		this.idUser = idUser;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Long getIdProperty() {
		return idProperty;
	}

	public void setIdProperty(Long idProperty) {
		this.idProperty = idProperty;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public static AcquisitionDTO createAcquisition(Acquisition acquisition){
		return new ModelMapper().map(acquisition, AcquisitionDTO.class);
	}
}
