package com.romaflix.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "usuarios", uniqueConstraints = { @UniqueConstraint(columnNames = "email", name = "uk__email__usuarios") })
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario", nullable = false)
	private Integer idUsuario;

	@Column(name = "email", length = 120, nullable = false)
	private String email;

	@Column(name = "senha", length = 150, nullable = false)
	private String senha;

	public Usuario() {}

	public Usuario(String _email, String _senha) {
		this.setEmail(_email);
		this.setSenha(_senha);
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer _idUsuario) {
		this.idUsuario = _idUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String _email) {
		this.email = _email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String _senha) {
		this.senha = _senha;
	}

}