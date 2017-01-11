package br.jus.cnj.saci.service;

import java.util.List;

import br.jus.cnj.saci.entity.AcompanhamentoDeliberacao;
import br.jus.cnj.saci.entity.Resposta;
import br.jus.cnj.utils.exception.ServiceException;

public interface AcompanhamentoDeliberacaoService {

	void persistirEntidade(AcompanhamentoDeliberacao acompanhamentoDeliberacao) throws ServiceException;

	void excluirEntidade(AcompanhamentoDeliberacao acompanhamentoDeliberacao) throws ServiceException;
	
	List<AcompanhamentoDeliberacao> getAll() throws ServiceException;

	AcompanhamentoDeliberacao pesquisarPorId(int id) throws ServiceException;
	
	void updateEntidade(AcompanhamentoDeliberacao acompanhamentoDeliberacao) throws ServiceException;
}
