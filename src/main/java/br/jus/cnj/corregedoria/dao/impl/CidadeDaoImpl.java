package br.jus.cnj.corregedoria.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.jus.cnj.corporativo.basecrud.impl.GenericDaoImpl;
import br.jus.cnj.corporativo.entity.Cidade;
import br.jus.cnj.corregedoria.dao.CidadeDAO;
import br.jus.cnj.utils.exception.DaoException;
@Repository
public class CidadeDaoImpl extends GenericDaoImpl<Cidade> implements CidadeDAO {

	@SuppressWarnings("unchecked")
	public List<Cidade> pesquisarPorUf(String uf) throws DaoException {
		String hql = "select cid from Cidade cid where cid.uf = :uf ORDER BY cid.dscCidade ASC";
		Query objQuery = getSession().createQuery(hql);
		objQuery.setParameter("uf", uf);	
		
		List<Cidade> cidades = objQuery.list(); 
		return cidades;
	}
}
