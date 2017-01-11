package br.jus.cnj.saci.service;

import java.util.Collection;
import java.util.List;

import br.jus.cnj.saci.entity.Questionario;
import br.jus.cnj.saci.entity.TipoAplicacaoPergunta;
import br.jus.cnj.saci.entity.TipoObjetoAplicacao;
import br.jus.cnj.utils.exception.ServiceException;

public interface TipoAplicacaoPerguntaService {

	boolean persistirEntidade(TipoAplicacaoPergunta tipoModeloBasico) throws ServiceException;

	void excluirEntidade(TipoAplicacaoPergunta tipoModeloBasico) throws ServiceException;

	List<TipoAplicacaoPergunta> getAll() throws ServiceException;

	TipoAplicacaoPergunta pesquisarPorId(int id) throws ServiceException;

	boolean updateEntidade(TipoAplicacaoPergunta tipoModeloBasico) throws ServiceException;

	List<TipoObjetoAplicacao> listaTipoObjetoAplicacao (List<TipoAplicacaoPergunta> tipoAplicacaoPerguntaList) throws ServiceException;
}
