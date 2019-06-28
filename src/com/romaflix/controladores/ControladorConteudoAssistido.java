package com.romaflix.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import com.romaflix.conexao.ConnectionFactory;
import com.romaflix.dao.DAO;
import com.romaflix.entidades.ConteudoAssistido;
import com.romaflix.enums.Resposta;
import com.romaflix.interfaces.IControlador;
import com.romaflix.interfaces.IDAO;
import com.romaflix.utils.Utils;

public class ControladorConteudoAssistido implements IControlador<ConteudoAssistido> {
	private IDAO<ConteudoAssistido> dao;

	public ControladorConteudoAssistido() {
		this.dao = new DAO<ConteudoAssistido>();
	}

	@Override
	public Resposta cadastrar(ConteudoAssistido _conteudoAssistido) {
		if (this.dao.cadastrar(_conteudoAssistido))
			return Resposta.CADASTRADO;

		return Resposta.NAO_CADASTRADO;
	}

	@Override
	public Resposta atualizar(ConteudoAssistido _conteudoAssistido) {
		if (this.dao.atualizar(_conteudoAssistido))
			return Resposta.ATUALIZADO;

		return Resposta.NAO_ATUALIZADO;
	}

	@Override
	public Resposta remover(ConteudoAssistido _conteudoAssistido, Integer id) {
		if (this.dao.remover(_conteudoAssistido, id))
			return Resposta.REMOVIDO;

		return Resposta.NAO_REMOVIDO;
	}

	@Override
	public ConteudoAssistido buscarPor(Integer id) {
		if (id != null) {
			
			EntityManager em;

			if (Utils.ehContaConvidado != 0) {
				em = ConnectionFactory.obterConexao();
			} else {
				em = ConnectionFactory.obterConexaoPrivada();
			}
			
			try {
				return em.find(ConteudoAssistido.class, id);
			} catch (PersistenceException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				return null;
			} finally {
				em.close();
			}
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public List<ConteudoAssistido> listarPorConta(Integer idControleConta) {

		EntityManager em;
		
		if (Utils.ehContaConvidado != 0) {
			em = ConnectionFactory.obterConexao();
		} else {
			em = ConnectionFactory.obterConexaoPrivada();
		}

		try {
			Query query = em
					.createQuery("SELECT ca FROM ConteudoAssistido ca WHERE id_controle_conta = :idControleConta");
			query.setParameter("idControleConta", idControleConta);

			return (List<ConteudoAssistido>) query.getResultList();

		} catch (PersistenceException e) {
			JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
		} finally {
			em.close();
		}

		return new ArrayList<ConteudoAssistido>();
	}

	@Override
	public List<ConteudoAssistido> listar(ConteudoAssistido _conteudoAssistido) {
		return this.dao.listar(_conteudoAssistido);
	}
}
