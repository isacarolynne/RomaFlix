package com.romaflix.controladores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.swing.JOptionPane;

import com.romaflix.conexao.ConnectionFactory;
import com.romaflix.dao.DAO;
import com.romaflix.entidades.ControleContas;
import com.romaflix.enums.Resposta;
import com.romaflix.interfaces.IControlador;
import com.romaflix.interfaces.IDAO;
import com.romaflix.utils.Utils;

public class ControladorControleContas implements IControlador<ControleContas> {
	private IDAO<ControleContas> dao;

	public ControladorControleContas() {
		this.dao = new DAO<ControleContas>();
	}

	@Override
	public Resposta cadastrar(ControleContas controleContas) {
		if (this.dao.cadastrar(controleContas))
			return Resposta.CADASTRADO;

		return Resposta.NAO_CADASTRADO;
	}

	@Override
	public Resposta atualizar(ControleContas controleContas) {
		if (this.dao.atualizar(controleContas))
			return Resposta.ATUALIZADO;

		return Resposta.NAO_ATUALIZADO;
	}

	@Override
	public Resposta remover(ControleContas controleContas, Integer id) {
		if (this.dao.remover(controleContas, id))
			return Resposta.REMOVIDO;

		return Resposta.NAO_REMOVIDO;
	}

	@Override
	public ControleContas buscarPor(Integer id) {
		if (id != null) {
			
			EntityManager em;

			if (Utils.ehContaConvidado != 0) {
				em = ConnectionFactory.obterConexao();
			} else {
				em = ConnectionFactory.obterConexaoPrivada();
			}
			
			try {
				return em.find(ControleContas.class, id);
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
	public List<ControleContas> listar(ControleContas controleContas) {
		return this.dao.listar(controleContas);
	}
}
