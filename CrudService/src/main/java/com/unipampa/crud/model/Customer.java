package com.unipampa.crud.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Customer extends User {

	@Column(name = "cpf")
	private String cpf;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "address")
	private String address;

	public Customer(String email, String name, String password, String cpf, String phone, String address) {
		super(email, name, password);
		this.cpf = cpf;
		this.phone = phone;
		this.address = address;
	}
	
	public Customer() {
		super();
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
