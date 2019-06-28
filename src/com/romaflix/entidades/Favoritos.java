package com.romaflix.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "favoritos", indexes = {
		@Index(
				name = "idx__codigo_catalogo__favoritos", 
				columnList = "codigo_catalogo", unique = false
	)})
public class Favoritos {

	@Id
	@Column(name = "id_favorito", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idFavorito;

	@Column(name = "id_controle_conta", nullable = false)
	private Integer idControleConta;

	@Column(name = "codigo_catalogo", length = 8, nullable = false)
	private String codigoCatologo;

	public Favoritos() {
	}

	public Favoritos(Integer _controleConta, String _codigoCatologo) {
		this.setIdControleConta(_controleConta);
		this.setCodigoCatologo(_codigoCatologo);
	}

	public Integer getIdFavorito() {
		return idFavorito;
	}

	public void setIdFavorito(Integer _idFavorito) {
		this.idFavorito = _idFavorito;
	}


	public Integer getIdControleConta() {
		return idControleConta;
	}

	public void setIdControleConta(Integer _controleConta) {
		this.idControleConta = _controleConta;
	}

	public String getCodigoCatologo() {
		return codigoCatologo;
	}

	public void setCodigoCatologo(String _codigoCatologo) {
		this.codigoCatologo = _codigoCatologo;
	}

}
