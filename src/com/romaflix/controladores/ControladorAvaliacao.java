package com.romaflix.controladores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.swing.JOptionPane;

import com.romaflix.conexao.ConnectionFactory;
import com.romaflix.dao.DAO;
import com.romaflix.entidades.Avaliacao;
import com.romaflix.enums.Resposta;
import com.romaflix.interfaces.IControlador;
import com.romaflix.interfaces.IDAO;
import com.romaflix.utils.Utils;

public class ControladorAvaliacao implements IControlador<Avaliacao> {
	private IDAO<Avaliacao> dao;

	public ControladorAvaliacao() {
		this.dao = new DAO<Avaliacao>();
	}

	@Override
	public Resposta cadastrar(Avaliacao avaliacao) {
		if (this.dao.cadastrar(avaliacao))
			return Resposta.CADASTRADO;

		return Resposta.NAO_CADASTRADO;
	}

	@Override
	public Resposta atualizar(Avaliacao avaliacao) {
		if (this.dao.atualizar(avaliacao))
			return Resposta.ATUALIZADO;

		return Resposta.NAO_ATUALIZADO;
	}

	@Override
	public Resposta remover(Avaliacao avaliacao, Integer id) {
		if (this.dao.remover(avaliacao, id))
			return Resposta.REMOVIDO;

		return Resposta.NAO_REMOVIDO;
	}

	@Override
	public Avaliacao buscarPor(Integer id) {
		if (id != null) {
			
			EntityManager em;
			
			if (Utils.ehContaConvidado != 0) {
				em = ConnectionFactory.obterConexao();
			} else {
				em = ConnectionFactory.obterConexaoPrivada();
			}
			
			try {
				return em.find(Avaliacao.class, id);
			} catch (PersistenceException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				return null;
			} finally {
				em.close();
			}
		}

		return null;
	}

	@Override
	public List<Avaliacao> listar(Avaliacao avaliacao) {
		return this.dao.listar(avaliacao);
	}
}
