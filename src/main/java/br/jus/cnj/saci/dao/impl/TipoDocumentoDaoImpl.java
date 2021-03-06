package br.jus.cnj.saci.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.jus.cnj.corporativo.basecrud.impl.GenericDaoImpl;
import br.jus.cnj.saci.dao.TipoDocumentoDAO;
import br.jus.cnj.saci.entity.TipoDocumento;
import br.jus.cnj.saci.entity.TipoObjetoAplicacao;
import br.jus.cnj.utils.exception.DaoException;
@Repository
public class TipoDocumentoDaoImpl extends GenericDaoImpl<TipoDocumento> implements TipoDocumentoDAO {

	@SuppressWarnings("unchecked")	
	public boolean descricaoExists(String descricao, int id) throws DaoException {
		String hql = "select doc from TipoDocumento doc where doc.dscTipoDocumento = :descricao AND doc.id != :id";
		Query objQuery = getSession().createQuery(hql);		
		objQuery.setParameter("descricao", descricao);
		objQuery.setParameter("id", id);
		
		List<TipoDocumento> tipoDocumentoList = (List<TipoDocumento>) objQuery.list(); 	
		if (tipoDocumentoList.size() > 0) {
			return true;
		}
		return false;
	}
}
