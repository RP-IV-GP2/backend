package com.example.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")

public class User {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_name", unique = true)
	private String userName;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	private String token;

	public User() {

	}

	public User(Long id, String userName, String email, String password, String token) {
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.token = token;
	}

	public User(String nome, String email, String senha) {
		this.userName = nome;
		this.email = email;
		this.password = senha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
