package br.jus.cnj.saci.service;

import java.util.List;

import br.jus.cnj.saci.entity.TipoComplementoResposta;
import br.jus.cnj.utils.exception.ServiceException;

public interface TipoComplementoRespostaService {
	
	boolean persistirEntidade(TipoComplementoResposta tipoComplementoResposta) throws ServiceException;
	
	List<TipoComplementoResposta> getAll() throws ServiceException;
	
	TipoComplementoResposta pesquisarPorId(int id) throws ServiceException;

	void excluirEntidade(TipoComplementoResposta tipoComplementoResposta) throws ServiceException;

	boolean updateEntidade(TipoComplementoResposta tipoComplementoResposta) throws ServiceException;

	
}
