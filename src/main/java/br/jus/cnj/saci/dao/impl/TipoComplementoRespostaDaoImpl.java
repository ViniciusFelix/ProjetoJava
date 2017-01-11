package br.jus.cnj.saci.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.jus.cnj.corporativo.basecrud.impl.GenericDaoImpl;
import br.jus.cnj.saci.dao.TipoComplementoRespostaDAO;
import br.jus.cnj.saci.entity.TipoComplementoResposta;
import br.jus.cnj.utils.exception.DaoException;
@Repository
public class TipoComplementoRespostaDaoImpl extends GenericDaoImpl<TipoComplementoResposta> implements TipoComplementoRespostaDAO{
	
	@SuppressWarnings("unchecked")	
	public boolean descricaoExists(String descricao, int id) throws DaoException {
		String hql = "select doc from TipoComplementoResposta doc where doc.dscTipoComplementoResposta = :descricao AND doc.id != :id";
		Query objQuery = getSession().createQuery(hql);		
		objQuery.setParameter("descricao", descricao);
		objQuery.setParameter("id", id);
		
		List<TipoComplementoResposta> tipoComplementoRespostaList = (List<TipoComplementoResposta>) objQuery.list(); 	
		if (tipoComplementoRespostaList.size() > 0) {
			return true;
		}
		return false;
	}

}
