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
@Table(name = "controle_contas")
public class ControleContas {

	@Id
	@Column(name = "id_controle_conta", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idControleConta;

	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "id_usuario", foreignKey = @ForeignKey(name = "fk__id_usuario__ctrl_contas"))
	private Usuario usuario;

	@Column(name = "nome_conta", length = 40, nullable = false)
	private String nomeConta;

	@Column(name = "senha_conta", length = 16, nullable = false)
	private String senhaConta;

	@Column(name = "eh_conta_convidado", length = 1, nullable = false)
	private Integer ehContaConvidado;

	public ControleContas() {
	}

	public ControleContas(Usuario _usuario, String _nomeConta, String _senhaConta, Integer _ehContaConvidado) {
		this.setUsuario(_usuario);
		this.setNomeConta(_nomeConta);
		this.setSenhaConta(_senhaConta);
		this.setEhContaConvidado(_ehContaConvidado);
	}

	public String getNomeConta() {
		return nomeConta;
	}

	public void setNomeConta(String _nomeConta) {
		this.nomeConta = _nomeConta;
	}

	public String getSenhaConta() {
		return senhaConta;
	}

	public void setSenhaConta(String _senhaConta) {
		this.senhaConta = _senhaConta;
	}

	public Integer getEhContaConvidado() {
		return ehContaConvidado;
	}

	public void setEhContaConvidado(Integer _ehContaConvidado) {
		this.ehContaConvidado = _ehContaConvidado;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario _usuario) {
		this.usuario = _usuario;
	}

	public Integer getIdControleConta() {
		return idControleConta;
	}

	public void setIdControleConta(Integer _idControleConta) {
		this.idControleConta = _idControleConta;
	}

}