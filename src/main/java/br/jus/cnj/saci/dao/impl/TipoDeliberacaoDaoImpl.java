package br.jus.cnj.saci.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.jus.cnj.corporativo.basecrud.impl.GenericDaoImpl;
import br.jus.cnj.saci.dao.TipoDeliberacaoDAO;
import br.jus.cnj.saci.entity.TipoDeliberacao;
import br.jus.cnj.saci.entity.TipoDocumento;
import br.jus.cnj.utils.exception.DaoException;
@Repository
public class TipoDeliberacaoDaoImpl extends GenericDaoImpl<TipoDeliberacao> implements TipoDeliberacaoDAO {

	@SuppressWarnings("unchecked")	
	public boolean descricaoExists(String descricao) throws DaoException {
		String hql = "select del from TipoDeliberacao del where del.dscTipoDeliberacao = :descricao";
		Query objQuery = getSession().createQuery(hql);		
		objQuery.setParameter("descricao", descricao);
		
		List<TipoDeliberacao> tipoDeliberacaoList = (List<TipoDeliberacao>) objQuery.list(); 	
		if (tipoDeliberacaoList.size() > 0) {
			return true;
		}
		return false;
	}
	
}
