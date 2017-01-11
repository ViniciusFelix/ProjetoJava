package br.jus.cnj.saci.service;

import java.util.Collection;
import java.util.List;

import br.jus.cnj.saci.entity.TipoDocumento;
import br.jus.cnj.saci.entity.TipoObjetoAplicacao;
import br.jus.cnj.utils.exception.ServiceException;

public interface TipoDocumentoService {
	
	boolean persistirEntidade(TipoDocumento tipoDocumento) throws ServiceException;
	
	List<TipoDocumento> getAll() throws ServiceException;

	void excluirEntidade(TipoDocumento tipoDocumento) throws ServiceException;

	boolean updateEntidade(TipoDocumento tipoDocumento) throws ServiceException;
}
