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
@Table(name = "enderecos")
public class Endereco {

	@Id
	@Column(name = "id_endereco", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEndereco;
	
	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "id_usuario", foreignKey = @ForeignKey(name = "fk__id_usuario__endereco"))
	private Usuario usuario;

	@Column(length = 120, nullable = false)
	private String rua;

	@Column(length = 7, nullable = false)
	private String numero;

	@Column(length = 30, nullable = false)
	private String complemento;

	@Column(length = 60, nullable = false)
	private String bairro;

	@Column(length = 60, nullable = false)
	private String cidade;

	@Column(length = 2, nullable = false)
	private String estado;

	@Column(length = 8, nullable = false)
	private String cep;

	public Endereco() {

	}

	public Endereco(Usuario _usuario,String _rua, String _numero, String _complemento, String _bairro, String _cidade, String _estado,
			String _cep) {
		this.setUsuario(_usuario);
		this.setRua(_rua);
		this.setNumero(_numero);
		this.setComplemento(_complemento);
		this.setBairro(_bairro);
		this.setCidade(_cidade);
		this.setEstado(_estado);
		this.setCep(_cep);
	}

	public Integer getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(Integer idEndereco) {
		this.idEndereco = idEndereco;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario _usuario) {
		this.usuario = _usuario;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

}
