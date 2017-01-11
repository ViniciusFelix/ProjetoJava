package br.jus.cnj.saci.dao;

import java.util.List;

import br.jus.cnj.corporativo.basecrud.GenericDao;
import br.jus.cnj.saci.entity.Questionario;
import br.jus.cnj.saci.entity.TipoAplicacaoPergunta;
import br.jus.cnj.utils.exception.DaoException;

public interface QuestionarioDAO extends GenericDao<Questionario> {
	public boolean descricaoExists(String descricao, int id) throws DaoException;
	
	public List<TipoAplicacaoPergunta> listaTipoAplicacaoPergunta(Questionario questionario) throws DaoException;
}
