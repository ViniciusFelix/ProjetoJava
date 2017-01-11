package br.jus.cnj.corregedoria.dao;

import java.util.List;

import br.jus.cnj.corporativo.basecrud.GenericDao;
import br.jus.cnj.corporativo.entity.Cidade;
import br.jus.cnj.corregedoria.entity.Cartorio;
import br.jus.cnj.utils.exception.DaoException;

public interface CartorioDAO extends GenericDao<Cartorio> {

	public List<Cartorio> pesquisarPorCidade(Cidade b) throws DaoException;
	
}
