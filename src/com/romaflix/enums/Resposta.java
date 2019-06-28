package com.romaflix.enums;

public enum Resposta {
	CADASTRADO(true, "Cadastro realizado com sucesso!"),
	NAO_CADASTRADO(false, "Cadastro não realizado"),
	
	ATUALIZADO(true, "Alteração realizada com sucesso!"),
	NAO_ATUALIZADO(false, "Alteração não realizada"),
	
	REMOVIDO(true, "Remoção realizada com sucesso!"),
	NAO_REMOVIDO(false, "Remoção não realizada");
	
	private String mensagem;
	private boolean status;
	
	private Resposta(boolean _status, String _mensagem) {
		this.status = _status;
		this.mensagem= _mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String _mensagem) {
		this.mensagem = _mensagem;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean _status) {
		this.status = _status;
	}
	
}
