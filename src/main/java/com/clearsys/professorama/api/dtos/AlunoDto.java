package com.clearsys.professorama.api.dtos;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class AlunoDto {

	private long id;
	private String nome;
	private String serie;
	private String senha;
	private String usuario;
	
	public AlunoDto() { }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@NotNull(message = "O campo nome não pode ser vazio")
	@Length(min = 1, max = 200, message = "Nome deve conter entre 1 e 200 caracteres." )
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@NotNull(message = "O campo usuário não pode ser vazio")
	@Length(min = 1, max = 100, message = "Nome deve conter entre 1 e 100 caracteres." )
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@NotNull(message = "O campo senha não pode ser vazio")
	@Length(min = 1, max = 100, message = "Nome deve conter entre 1 e 100 caracteres." )
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
		
	@NotNull(message = "O campo série não pode ser vazio")
	@Length(min = 1, max = 20, message = "Nome deve conter entre 1 e 20 caracteres." )
	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	@Override
	public String toString() {
		return "CadastroAlunoDto [id=" + id + ", nome=" + nome + ", serie=" + serie + ", senha=" + senha + ", usuario="
				+ usuario + "]";
	}
	
}
