package br.jus.cnj.saci.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.jus.cnj.corporativo.basecrud.impl.GenericDaoImpl;
import br.jus.cnj.saci.dao.TipoAplicacaoPerguntaDAO;
import br.jus.cnj.saci.entity.Questionario;
import br.jus.cnj.saci.entity.TipoAplicacaoPergunta;
import br.jus.cnj.saci.entity.TipoFormatoResposta;
import br.jus.cnj.saci.entity.TipoObjetoAplicacao;
import br.jus.cnj.utils.exception.DaoException;

@Repository
public class TipoAplicacaoPerguntaDaoImpl extends GenericDaoImpl<TipoAplicacaoPergunta> implements TipoAplicacaoPerguntaDAO {
	@SuppressWarnings("unchecked")	
	public boolean descricaoExists(String descricao, int id) throws DaoException {
		String hql = "select tap from TipoAplicacaoPergunta tap where tap.dscTipoAplicacao = :descricao AND tap.id != :id";
		Query objQuery = getSession().createQuery(hql);		
		objQuery.setParameter("descricao", descricao);
		objQuery.setParameter("id", id);	
		
		List<TipoAplicacaoPergunta> tipoAplicacaoPerguntaList = (List<TipoAplicacaoPergunta>) objQuery.list(); 	
		if (tipoAplicacaoPerguntaList.size() > 0) {
			return true;
		}
		return false;
	}
	
	@Override
	public List<TipoObjetoAplicacao> listaTipoObjetoAplicacao(List<TipoAplicacaoPergunta> tipoAplicacaoPerguntaList) throws DaoException {
		String hql = "select toa from TipoObjetoAplicacao toa join toa.tipoAplicacaoPergunta as tap where tap in (:tipoAplicacaoPerguntaList) group by toa";
		Query objQuery = getSession().createQuery(hql);	
		objQuery.setParameterList("tipoAplicacaoPerguntaList", tipoAplicacaoPerguntaList);	
		
		List<TipoObjetoAplicacao> tipoObjetoAplicacao = (List<TipoObjetoAplicacao>) objQuery.list(); 	
		return tipoObjetoAplicacao;
	}
	
}
