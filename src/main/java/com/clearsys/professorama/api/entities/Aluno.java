package com.clearsys.professorama.api.entities;

public class Aluno {
	private long id;
	private int serie;
	private String nome;
	private String usuario;
	private String senha;
	private String nivelEscolar;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getSerie() {
		return serie;
	}
	public void setSerie(int serie) {
		this.serie = serie;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNivelEscolar() {
		return nivelEscolar;
	}
	public void setNivelEscolar(String nivelEscolar) {
		this.nivelEscolar = nivelEscolar;
	}
	@Override
	public String toString() {
		return "Aluno [id=" + id + ", serie=" + serie + ", nome=" + nome + ", usuario=" + usuario + ", senha=" + senha
				+ ", nivelEscolar=" + nivelEscolar + "]";
	}
	

	
	
}
