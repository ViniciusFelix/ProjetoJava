package br.jus.cnj.saci.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.jus.cnj.corporativo.basecrud.impl.GenericDaoImpl;
import br.jus.cnj.saci.dao.ModeloPerguntaDAO;
import br.jus.cnj.saci.entity.ModeloPergunta;
import br.jus.cnj.saci.entity.Questionario;
import br.jus.cnj.saci.entity.TipoAplicacaoPergunta;
import br.jus.cnj.utils.exception.DaoException;

@Repository
public class ModeloPerguntaDaoImpl extends GenericDaoImpl<ModeloPergunta> implements ModeloPerguntaDAO {
		
	@SuppressWarnings("unchecked")	
	public ModeloPergunta pesquisarPorNumOrdem(Serializable numOrdem, TipoAplicacaoPergunta tipoAplicacaoPergunta) throws DaoException {
		String hql = "select mp from ModeloPergunta mp where mp.numOrdem = :numOrdem AND mp.tipoAplicacaoPergunta = :tipoAplicacaoPergunta";
		Query objQuery = getSession().createQuery(hql);		
		objQuery.setParameter("numOrdem", numOrdem);	
		objQuery.setParameter("tipoAplicacaoPergunta", tipoAplicacaoPergunta);	
		
		ModeloPergunta modeloPergunta = (ModeloPergunta) objQuery.uniqueResult();		
		return modeloPergunta;
	}
	
	public int pesquisarUltimoNumOrdem(ModeloPergunta modeloPergunta) throws DaoException {
		String hql = "select mp from ModeloPergunta mp where mp.tipoAplicacaoPergunta = :tipoAplicacaoPergunta ORDER BY mp.numOrdem DESC";
		Query objQuery = getSession().createQuery(hql);		
		objQuery.setParameter("tipoAplicacaoPergunta", modeloPergunta.getTipoAplicacaoPergunta());	
		
		List<ModeloPergunta> modeloPerguntaList = (List<ModeloPergunta>) objQuery.list();
		int numOrdem = 1;
		if (modeloPerguntaList.size() > 0) { 
			numOrdem = modeloPerguntaList.get(0).getNumOrdem() + 1;
		}
		return numOrdem;
	}
	
	public List<ModeloPergunta> pesquisarPorTipoAplicacaoPergunta(TipoAplicacaoPergunta tipoAplicacaoPergunta) throws DaoException {
		String hql = "select mp from ModeloPergunta mp where mp.tipoAplicacaoPergunta = :tipoAplicacaoPergunta ORDER BY mp.numOrdem ASC";
		Query objQuery = getSession().createQuery(hql);
		objQuery.setParameter("tipoAplicacaoPergunta", tipoAplicacaoPergunta);	
		
		List<ModeloPergunta> modeloPerguntaList = (List<ModeloPergunta>) objQuery.list(); 
		return modeloPerguntaList;
	}

	@Override
	public void mudarOrdem(int ini, int fim, TipoAplicacaoPergunta tap, int aux) throws DaoException {
		String hql = "update ModeloPergunta p set p.numOrdem = p.numOrdem - 1 where p.numOrdem > :ini AND p.numOrdem <= :fim AND p.tipoAplicacaoPergunta = :tipoAplicacaoPergunta";
		if (aux == ini) {
			hql = "update ModeloPergunta p set p.numOrdem = p.numOrdem + 1 where p.numOrdem < :ini AND p.numOrdem >= :fim AND p.tipoAplicacaoPergunta = :tipoAplicacaoPergunta";
		}
		Query objQuery = getSession().createQuery(hql);
		objQuery.setParameter("ini", ini);
		objQuery.setParameter("fim", fim);
		objQuery.setParameter("tipoAplicacaoPergunta", tap);
		
		objQuery.executeUpdate();
	}
}