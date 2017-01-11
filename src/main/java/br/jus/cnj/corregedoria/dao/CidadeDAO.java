package br.jus.cnj.corregedoria.dao;

import java.util.List;

import br.jus.cnj.corporativo.basecrud.GenericDao;
import br.jus.cnj.corporativo.entity.Cidade;
import br.jus.cnj.utils.exception.DaoException;

public interface CidadeDAO extends GenericDao<Cidade> {

	public List<Cidade> pesquisarPorUf(String uf) throws DaoException;
	
}
