package com.clearsys.professorama.api.security.dto;

import javax.validation.constraints.NotEmpty;

public class JwtAuthenticationDto {

	private String usuario;
	private String senha;

	public JwtAuthenticationDto() {
	}

	@NotEmpty(message = " Usuário não pode ser vazio ")
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@NotEmpty(message = " Senha não pode ser vazio ")
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "JwtAuthenticationDto [usuario=" + usuario + ", senha=" + senha + "]";
	}

}
