package br.jus.cnj.saci.dao;

import br.jus.cnj.corporativo.basecrud.GenericDao;
import br.jus.cnj.saci.entity.TipoFormatoResposta;
import br.jus.cnj.utils.exception.DaoException;

public interface TipoFormatoRespostaDAO extends GenericDao<TipoFormatoResposta> {

	public boolean descricaoExists(String descricao, int id) throws DaoException;
}
