package br.jus.cnj.saci.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.jus.cnj.corporativo.basecrud.impl.GenericDaoImpl;
import br.jus.cnj.saci.dao.TipoObjetoAplicacaoDAO;
import br.jus.cnj.saci.entity.TipoAplicacaoPergunta;
import br.jus.cnj.saci.entity.TipoObjetoAplicacao;
import br.jus.cnj.utils.exception.DaoException;
@Repository
public class TipoObjetoAplicacaoDaoImpl extends GenericDaoImpl<TipoObjetoAplicacao> implements TipoObjetoAplicacaoDAO {

	@SuppressWarnings("unchecked")	
	public boolean descricaoExists(String descricao, int id) throws DaoException {
		String hql = "select toa from TipoObjetoAplicacao toa where toa.dscTipoObjetoAplicacao = :descricao AND toa.id != :id";
		Query objQuery = getSession().createQuery(hql);		
		objQuery.setParameter("descricao", descricao);
		objQuery.setParameter("id", id);	
		
		List<TipoObjetoAplicacao> tipoObjetoAplicacaoList = (List<TipoObjetoAplicacao>) objQuery.list(); 	
		if (tipoObjetoAplicacaoList.size() > 0) {
			return true;
		}
		return false;
	}
}
