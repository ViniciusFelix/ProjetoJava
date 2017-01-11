package br.jus.cnj.utils.exception;

public class ServiceException extends Exception {

	private static final long serialVersionUID = 8437427396818946098L;

	public ServiceException(String mensagem) {
		super(mensagem);
	}

}
