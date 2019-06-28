package com.romaflix.entidades;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "telefones")
public class Telefone {

	@Id
	@Column(name = "id_telefone", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTelefone;
	
	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "id_usuario", foreignKey = @ForeignKey(name = "fk__id_usuario__telefones"))
	private Usuario usuario;
	
	@Column(length = 13, nullable = false)
	private String numero;

	public Telefone() {

	}

	public Telefone(Usuario _idUsuario,String _numero) {
		this.setUsuario(_idUsuario);
		this.setNumero(_numero);
	}

	public Integer getIdTelefone() {
		return idTelefone;
	}

	public void setIdTelefone(Integer _idTelefone) {
		this.idTelefone = _idTelefone;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario _usuario) {
		this.usuario = _usuario;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String _numero) {
		this.numero = _numero;
	}

}
