package br.jus.cnj.saci.service;

import java.util.List;

import br.jus.cnj.saci.entity.TipoDeliberacao;
import br.jus.cnj.saci.entity.TipoDocumento;
import br.jus.cnj.utils.exception.ServiceException;

public interface TipoDeliberacaoService {
	
	boolean persistirEntidade(TipoDeliberacao tipoDeliberacao) throws ServiceException;
	
	List<TipoDeliberacao> getAll() throws ServiceException;
	
	void excluirEntidade(TipoDeliberacao tipoDeliberacao) throws ServiceException;
	
	boolean updateEntidade(TipoDeliberacao tipoDeliberacao) throws ServiceException;
}
