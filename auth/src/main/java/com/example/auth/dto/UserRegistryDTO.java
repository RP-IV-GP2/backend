package com.example.auth.dto;

import com.example.auth.model.User;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility= JsonAutoDetect.Visibility.ANY)
public class UserRegistryDTO extends User {

	private Long id;

	private String nome;
	private String email;
	private String senha;

	public UserRegistryDTO() {

	}

	public UserRegistryDTO(String name, String email, String senha) {
		this.nome = name;
		this.email = email;
		this.senha = senha;
	}

	public User toUser() {
		return new User(getUserName(), getPassword(), getEmail());
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}