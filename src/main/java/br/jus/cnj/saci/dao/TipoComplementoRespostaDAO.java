package br.jus.cnj.saci.dao;

import br.jus.cnj.corporativo.basecrud.GenericDao;
import br.jus.cnj.saci.entity.TipoComplementoResposta;
import br.jus.cnj.utils.exception.DaoException;

public interface TipoComplementoRespostaDAO extends GenericDao<TipoComplementoResposta>{
	
	boolean descricaoExists(String descricao, int i) throws DaoException;

}
