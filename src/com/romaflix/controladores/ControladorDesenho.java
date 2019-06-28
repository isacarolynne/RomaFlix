package com.romaflix.controladores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.swing.JOptionPane;

import com.romaflix.conexao.ConnectionFactory;
import com.romaflix.dao.DAO;
import com.romaflix.entidades.Desenho;
import com.romaflix.enums.Resposta;
import com.romaflix.interfaces.IControlador;
import com.romaflix.interfaces.IDAO;
import com.romaflix.utils.Utils;

public class ControladorDesenho implements IControlador<Desenho> {
	private IDAO<Desenho> dao;

	public ControladorDesenho() {
		this.dao = new DAO<Desenho>();
	}

	@Override
	public Resposta cadastrar(Desenho desenho) {
		if (this.dao.cadastrar(desenho))
			return Resposta.CADASTRADO;

		return Resposta.NAO_CADASTRADO;
	}

	@Override
	public Resposta atualizar(Desenho desenho) {
		if (this.dao.atualizar(desenho))
			return Resposta.ATUALIZADO;

		return Resposta.NAO_ATUALIZADO;
	}

	@Override
	public Resposta remover(Desenho desenho, Integer id) {
		if (this.dao.remover(desenho, id))
			return Resposta.REMOVIDO;

		return Resposta.NAO_REMOVIDO;
	}

	@Override
	public Desenho buscarPor(Integer id) {
		if (id != null) {
			
			EntityManager em;

			if (Utils.ehContaConvidado != 0) {
				em = ConnectionFactory.obterConexao();
			} else {
				em = ConnectionFactory.obterConexaoPrivada();
			}
			
			try {
				return em.find(Desenho.class, id);
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
	public List<Desenho> listar(Desenho desenho) {
		return this.dao.listar(desenho);
	}
}
