package br.jus.cnj.corregedoria.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.jus.cnj.corporativo.basecrud.impl.GenericDaoImpl;
import br.jus.cnj.corporativo.entity.Cidade;
import br.jus.cnj.corregedoria.dao.CartorioDAO;
import br.jus.cnj.corregedoria.entity.Cartorio;
import br.jus.cnj.utils.exception.DaoException;
@Repository
public class CartorioDaoImpl extends GenericDaoImpl<Cartorio> implements CartorioDAO {

	@SuppressWarnings("unchecked")
	public List<Cartorio> pesquisarPorCidade(Cidade b) throws DaoException {
		String hql = "select c from Cartorio c where c.cidade = :idCidade  ORDER BY c.denominacaoCartorio ASC";
		Query objQuery = getSession().createQuery(hql);		
		objQuery.setParameter("idCidade", b);	
		
		List<Cartorio> cartorioList = (List<Cartorio>) objQuery.list();
		return cartorioList;
	}

}
