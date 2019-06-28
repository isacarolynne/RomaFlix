package com.romaflix.excecoes;

public class CampoInvalido extends Exception {
	private static final long serialVersionUID = 1L;

	public CampoInvalido(String message) {
		super(message);
	}

}
