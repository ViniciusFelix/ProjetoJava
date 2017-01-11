package br.jus.cnj.saci.dao;

import br.jus.cnj.corporativo.basecrud.GenericDao;
import br.jus.cnj.saci.entity.TipoDeliberacao;
import br.jus.cnj.utils.exception.DaoException;

public interface TipoDeliberacaoDAO extends GenericDao<TipoDeliberacao> {

	boolean descricaoExists(String descricao) throws DaoException;
	
}
