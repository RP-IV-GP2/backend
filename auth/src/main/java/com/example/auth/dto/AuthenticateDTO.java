package com.example.auth.dto;

import com.example.auth.model.User;

public class AuthenticateDTO {
	private String tipo;
	private String email;
	private String nome;
	private String token;

	public AuthenticateDTO(String email, String nome, String token, String tipo) {

		this.email = email;
		this.nome = nome;
		this.token = token;
		this.tipo = tipo;
	}

	public AuthenticateDTO() {
	}

	public static AuthenticateDTO toDTO(User user, String tipo) {
		return new AuthenticateDTO(user.getEmail(), user.getUserName(), user.getToken(), tipo);
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getToken() {
		return token;
	}

	public String getTipo() {
		return tipo;
	}
}