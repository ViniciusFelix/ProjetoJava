package br.jus.cnj.saci.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.jus.cnj.corporativo.basecrud.impl.GenericDaoImpl;
import br.jus.cnj.saci.dao.ComplementoRespostaDAO;
import br.jus.cnj.saci.entity.ComplementoResposta;
import br.jus.cnj.saci.entity.Resposta;
import br.jus.cnj.saci.entity.TipoDeliberacao;
import br.jus.cnj.utils.exception.DaoException;

@Repository
public class ComplementoRespostaDaoImpl extends GenericDaoImpl<ComplementoResposta> implements ComplementoRespostaDAO {

	@Override
	public List<ComplementoResposta> listaComplementoResposta(Resposta id) throws DaoException {
		String hql = "select com from ComplementoResposta com where com.resposta = :id";
		Query objQuery = getSession().createQuery(hql);		
		objQuery.setParameter("id", id);
		
		List<ComplementoResposta> complementoRespostaList = (List<ComplementoResposta>) objQuery.list(); 	
		if (complementoRespostaList.size() > 0) {
			return complementoRespostaList;
		}else{
			return null;
		}
	}
	
}
