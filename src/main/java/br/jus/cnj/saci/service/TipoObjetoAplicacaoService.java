package br.jus.cnj.saci.service;

import java.util.Collection;
import java.util.List;

import br.jus.cnj.saci.entity.TipoObjetoAplicacao;
import br.jus.cnj.utils.exception.ServiceException;

public interface TipoObjetoAplicacaoService {
	boolean persistirEntidade(TipoObjetoAplicacao tipoObjetoAplicacao) throws ServiceException;

	void excluirEntidade(TipoObjetoAplicacao tipoObjetoAplicacao) throws ServiceException;

	List<TipoObjetoAplicacao> getAll() throws ServiceException;

	TipoObjetoAplicacao pesquisarPorId(int id) throws ServiceException;

	boolean updateEntidade(TipoObjetoAplicacao tipoObjetoAplicacao) throws ServiceException;
	
	public Collection<TipoObjetoAplicacao> convertIdListToObjectList(Collection<String> tipoObjetoAplicacao) throws ServiceException;

}
