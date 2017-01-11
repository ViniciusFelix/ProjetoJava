package br.jus.cnj.saci.service;

import java.util.List;

import br.jus.cnj.saci.entity.ModeloPergunta;
import br.jus.cnj.saci.entity.Pergunta;
import br.jus.cnj.saci.entity.Questionario;
import br.jus.cnj.saci.entity.TipoAplicacaoPergunta;
import br.jus.cnj.utils.exception.ServiceException;

public interface PerguntaService {
	void persistirEntidade(Pergunta pergunta) throws ServiceException;
	
	public int pesquisarUltimoNumOrdem(Pergunta pergunta) throws ServiceException;
	
	void excluirEntidade(Pergunta pergunta) throws ServiceException;
	
	public Pergunta pesquisarPorNumOrdem(int numOrdem, TipoAplicacaoPergunta tipoAplicacaoPergunta, Questionario questionario) throws ServiceException;
	
	List<Pergunta> getAll() throws ServiceException;

	Pergunta pesquisarPorId(int id) throws ServiceException;
	
	void updateEntidade(Pergunta pergunta) throws ServiceException;
	
	List<Pergunta> pesquisarPorQuestionario(Questionario questionario) throws ServiceException;
	
	void mudarOrdem(int ini, int fim, TipoAplicacaoPergunta tap, Questionario questionario) throws ServiceException;
}

