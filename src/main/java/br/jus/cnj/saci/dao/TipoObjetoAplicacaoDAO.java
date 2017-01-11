package br.jus.cnj.saci.dao;

import br.jus.cnj.corporativo.basecrud.GenericDao;
import br.jus.cnj.saci.entity.TipoObjetoAplicacao;
import br.jus.cnj.utils.exception.DaoException;

public interface TipoObjetoAplicacaoDAO extends GenericDao<TipoObjetoAplicacao> {
	public boolean descricaoExists(String descricao, int i) throws DaoException;
}
