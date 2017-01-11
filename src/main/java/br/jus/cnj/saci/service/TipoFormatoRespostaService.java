package br.jus.cnj.saci.service;

import java.util.List;

import br.jus.cnj.saci.entity.TipoFormatoResposta;
import br.jus.cnj.utils.exception.ServiceException;

public interface TipoFormatoRespostaService {

	boolean persistirEntidade(TipoFormatoResposta tipoFormatoResposta) throws ServiceException;

	void excluirEntidade(TipoFormatoResposta tipoFormatoResposta) throws ServiceException;

	List<TipoFormatoResposta> getAll() throws ServiceException;

	TipoFormatoResposta pesquisarPorId(int id) throws ServiceException;

	boolean updateEntidade(TipoFormatoResposta tipoFormatoResposta) throws ServiceException;

}
