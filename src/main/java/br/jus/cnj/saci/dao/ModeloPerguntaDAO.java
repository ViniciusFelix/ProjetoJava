package br.jus.cnj.saci.dao;

import java.io.Serializable;
import java.util.List;

import br.jus.cnj.corporativo.basecrud.GenericDao;
import br.jus.cnj.saci.entity.ModeloPergunta;
import br.jus.cnj.saci.entity.Questionario;
import br.jus.cnj.saci.entity.TipoAplicacaoPergunta;
import br.jus.cnj.utils.exception.DaoException;

public interface ModeloPerguntaDAO extends GenericDao<ModeloPergunta> {

	public ModeloPergunta pesquisarPorNumOrdem(Serializable numOrdem, TipoAplicacaoPergunta tipoAplicacaoPergunta) throws DaoException;
	
	public int pesquisarUltimoNumOrdem(ModeloPergunta modeloPergunta) throws DaoException;
	
	public List<ModeloPergunta> pesquisarPorTipoAplicacaoPergunta(TipoAplicacaoPergunta tipoAplicacaoPergunta) throws DaoException;
	
	public void mudarOrdem(int ini, int fim, TipoAplicacaoPergunta tap, int aux) throws DaoException;
	
}
