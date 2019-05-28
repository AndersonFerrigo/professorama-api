package com.clearsys.professorama.api.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.clearsys.professorama.api.enums.PerfilEnum;

@Entity
@Table(name = "aluno") 
public class Aluno implements Serializable{
	
	/**
	 * Entidade responsável por representar um Aluno no sistema
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String nome;
	private String serie;
	private String usuario;
	private String senha;
	private PerfilEnum perfil;
	
	public Aluno() {}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
		
	@Column(name="nome", nullable = false)
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name="serie", nullable = false)
	public String getSerie() {
		return serie;
	}
	
	public void setSerie(String serie) {
		this.serie = serie;
	}
	
	@Column(name="usuario", nullable = false)
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	
	@Enumerated(EnumType.STRING)
	@Column(name = "perfil", nullable = false)
	public PerfilEnum getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilEnum perfil) {
		this.perfil = perfil;
	}

	@Column(name="senha", nullable = false)
	public String getSenha() {
		return senha;
	}
	
	
	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "Aluno [id=" + id + ", nome=" + nome + ", serie=" + serie + ", usuario=" + usuario + ", senha=" + senha
				+ "]";
	}
	
	
}
