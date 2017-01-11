package br.jus.cnj.utils.exception;

public class CamposNaoPreenchidosRuntimeException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8865814737452081664L;
	
	public CamposNaoPreenchidosRuntimeException(String message) {
		super(message);
	}
	
	public CamposNaoPreenchidosRuntimeException() {
		super("Campos obrigat�rios n�o preenchidos");
	}

}
