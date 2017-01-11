package br.jus.cnj.saci.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.jus.cnj.corporativo.basecrud.impl.GenericDaoImpl;
import br.jus.cnj.saci.dao.TipoFormatoRespostaDAO;
import br.jus.cnj.saci.entity.ModeloPergunta;
import br.jus.cnj.saci.entity.TipoAplicacaoPergunta;
import br.jus.cnj.saci.entity.TipoFormatoResposta;
import br.jus.cnj.utils.exception.DaoException;

@Repository
public class TipoFormatoRespostaDaoImpl extends GenericDaoImpl<TipoFormatoResposta> implements TipoFormatoRespostaDAO {

	@SuppressWarnings("unchecked")	
	public boolean descricaoExists(String descricao, int id) throws DaoException {
		String hql = "select tfr from TipoFormatoResposta tfr where tfr.dscTipoResposta = :descricao AND tfr.id != :id";
		Query objQuery = getSession().createQuery(hql);		
		objQuery.setParameter("descricao", descricao);
		objQuery.setParameter("id", id);		
		
		List<TipoFormatoResposta> tipoFormatoRespostaList = (List<TipoFormatoResposta>) objQuery.list(); 	
		if (tipoFormatoRespostaList.size() > 0) {
			return true;
		}
		return false;
	}
}
