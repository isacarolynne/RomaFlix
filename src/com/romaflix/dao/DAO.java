package com.romaflix.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.swing.JOptionPane;

import com.romaflix.conexao.ConnectionFactory;
import com.romaflix.interfaces.IDAO;
import com.romaflix.utils.Utils;

public class DAO<T> implements IDAO<T> {
	public DAO() {

	}

	@Override
	public boolean cadastrar(T entity) {

		EntityManager em;

		if (Utils.ehContaConvidado != 0) {
			em = ConnectionFactory.obterConexao();
		} else {
			em = ConnectionFactory.obterConexaoPrivada();
		}

		try {

			EntityTransaction e = em.getTransaction();

			e.begin();
			em.persist(entity);
			e.commit();

			return true;
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			JOptionPane.showMessageDialog(null, e.getMessage());

			return false;
		} finally {
			em.close();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> listar(T entity) {

		EntityManager em;

		if (Utils.ehContaConvidado != 0) {
			em = ConnectionFactory.obterConexao();
		} else {
			em = ConnectionFactory.obterConexaoPrivada();
		}

		try {
			return em.createQuery("from " + entity.getClass().getSimpleName()).getResultList();
		} catch (PersistenceException e) {
			JOptionPane.showMessageDialog(null, "ERRO: " + e.getMessage());

			return null;
		} finally {
			em.close();
		}

	}

	@Override
	public boolean atualizar(T entity) {

		EntityManager em;

		if (Utils.ehContaConvidado != 0) {
			em = ConnectionFactory.obterConexao();
		} else {
			em = ConnectionFactory.obterConexaoPrivada();
		}

		try {

			EntityTransaction e = em.getTransaction();

			e.begin();
			em.merge(entity);
			e.commit();

			return true;
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			JOptionPane.showMessageDialog(null, "Desfazendo transação... \nERRO: " + e.getMessage());

			return false;
		} finally {
			em.close();
		}
	}

	@Override
	public boolean remover(T entity, Integer id) {

		EntityManager em;

		if (Utils.ehContaConvidado != 0) {
			em = ConnectionFactory.obterConexao();
		} else {
			em = ConnectionFactory.obterConexaoPrivada();
		}

		try {

			EntityTransaction e = em.getTransaction();

			e.begin();
			em.remove(em.find(entity.getClass(), id));
			e.commit();

			return true;
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			JOptionPane.showMessageDialog(null, "Desfazendo transação... \nERRO: " + e.getMessage());

			return false;
		} finally {
			em.close();
		}
	}
}