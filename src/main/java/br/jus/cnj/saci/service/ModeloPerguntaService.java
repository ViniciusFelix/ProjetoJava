package br.jus.cnj.saci.service;

import java.util.List;

import br.jus.cnj.saci.entity.ModeloPergunta;
import br.jus.cnj.saci.entity.Questionario;
import br.jus.cnj.saci.entity.TipoAplicacaoPergunta;
import br.jus.cnj.utils.exception.ServiceException;

public interface ModeloPerguntaService {

	void persistirEntidade(ModeloPergunta modeloPergunta) throws ServiceException;

	void excluirEntidade(ModeloPergunta modeloPergunta) throws ServiceException;

	List<ModeloPergunta> getAll() throws ServiceException;

	ModeloPergunta pesquisarPorId(int id) throws ServiceException;
	
	public ModeloPergunta pesquisarPorNumOrdem(int numOrdem, TipoAplicacaoPergunta tipoAplicacaoPergunta) throws ServiceException;
	
	public List<ModeloPergunta> pesquisarPorTipoAplicacaoPergunta(TipoAplicacaoPergunta tipoAplicacaoPergunta) throws ServiceException;

	void updateEntidade(ModeloPergunta modeloPergunta) throws ServiceException;

	void mudarOrdem(int ini, int fim, TipoAplicacaoPergunta tap) throws ServiceException;
	
}
