package com.clearsys.professorama.api.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class AlunoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String nome;
	private String serie;
	private String ra;
	private String usuario;
	private String senha;

	public AlunoDto() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Length(min = 1, max = 200, message = "Nome deve conter entre 1 e 200 caracteres.")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Length(min = 1, max = 20, message = "Nome deve conter entre 1 e 20 caracteres.")
	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	@Length(min = 1, max = 100, message = "Nome deve conter entre 1 e 100 caracteres.")
	public String getRa() {
		return ra;
	}

	public void setRa(String ra) {
		this.ra = ra;
	}

	@NotNull(message = "O campo usuário não pode ser vazio")
	@Length(min = 1, max = 100, message = "Nome deve conter entre 1 e 100 caracteres.")
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@NotNull(message = "O campo senha não pode ser vazio")
	@Length(min = 1, max = 100, message = "Nome deve conter entre 1 e 100 caracteres.")
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "AlunoDto [id=" + id + ", nome=" + nome + ", serie=" + serie + ", ra=" + ra + ", usuario=" + usuario
				+ ", senha=" + senha + "]";
	}

}
