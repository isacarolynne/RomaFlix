package com.romaflix.interfaces;

import java.util.List;

public interface IDAO<T> {
	public boolean cadastrar(T entity);
	public boolean atualizar(T entity);
	public boolean remover(T entity, Integer id);
	public List<T> listar(T entity);
}
