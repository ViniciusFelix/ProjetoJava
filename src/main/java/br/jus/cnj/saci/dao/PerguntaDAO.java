package br.jus.cnj.saci.dao;

import java.io.Serializable;
import java.util.List;

import br.jus.cnj.corporativo.basecrud.GenericDao;
import br.jus.cnj.saci.entity.ModeloPergunta;
import br.jus.cnj.saci.entity.Pergunta;
import br.jus.cnj.saci.entity.Questionario;
import br.jus.cnj.saci.entity.TipoAplicacaoPergunta;
import br.jus.cnj.utils.exception.DaoException;

public interface PerguntaDAO extends GenericDao<Pergunta> {
	
	public List<Pergunta> pesquisarPorQuestionario(Questionario questionario) throws DaoException;
	
	public int pesquisarUltimoNumOrdem(Pergunta pergunta) throws DaoException;

	public Pergunta pesquisarPorNumOrdem(Serializable numOrdem, TipoAplicacaoPergunta tipoAplicacaoPergunta, Questionario questionario) throws DaoException;
	
	public void mudarOrdem(int ini, int fim, TipoAplicacaoPergunta tap, Questionario questionario, int aux) throws DaoException;
}
