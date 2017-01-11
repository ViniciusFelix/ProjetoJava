package br.jus.cnj.saci.dao;

import br.jus.cnj.corporativo.basecrud.GenericDao;
import br.jus.cnj.saci.entity.TipoDocumento;
import br.jus.cnj.utils.exception.DaoException;

public interface TipoDocumentoDAO extends GenericDao<TipoDocumento> {

	boolean descricaoExists(String descricao, int i) throws DaoException;
	
}
