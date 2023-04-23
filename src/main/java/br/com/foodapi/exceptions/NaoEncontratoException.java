package br.com.foodapi.exceptions;

import br.com.foodapi.constantes.Mensagens;

public class NaoEncontratoException extends Exception{
	
	private static final long serialVersionUID = 2304202304131L;

	public NaoEncontratoException(String mensagem) {
		super(mensagem);
	}
	
	public NaoEncontratoException() {
		super(Mensagens.NOT_FOUND);
	}
}
