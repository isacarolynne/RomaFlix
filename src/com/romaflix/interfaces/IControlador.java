package com.romaflix.interfaces;

import java.util.List;

import com.romaflix.enums.Resposta;

public interface IControlador<T> {
	public Resposta cadastrar(T entity);
	public Resposta atualizar(T entity);
	public Resposta remover(T entity, Integer id);
	List<T> listar(T entity);
	public T buscarPor(Integer id);
}
