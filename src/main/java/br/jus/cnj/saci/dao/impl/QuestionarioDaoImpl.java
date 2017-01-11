package br.jus.cnj.saci.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.jus.cnj.corporativo.basecrud.impl.GenericDaoImpl;
import br.jus.cnj.saci.dao.QuestionarioDAO;
import br.jus.cnj.saci.entity.Questionario;
import br.jus.cnj.saci.entity.TipoAplicacaoPergunta;
import br.jus.cnj.saci.entity.TipoObjetoAplicacao;
import br.jus.cnj.utils.exception.DaoException;
@Repository
public class QuestionarioDaoImpl extends GenericDaoImpl<Questionario> implements QuestionarioDAO {

	@SuppressWarnings("unchecked")	
	public boolean descricaoExists(String descricao, int id) throws DaoException {
		String hql = "select toa from Questionario toa where toa.nomQuestionario = :descricao AND toa.id != :id";
		Query objQuery = getSession().createQuery(hql);		
		objQuery.setParameter("descricao", descricao);
		objQuery.setParameter("id", id);	
		
		List<Questionario> questionarioList = (List<Questionario>) objQuery.list(); 	
		if (questionarioList.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<TipoAplicacaoPergunta> listaTipoAplicacaoPergunta(Questionario questionario) throws DaoException {
		String hql = "select tap from Pergunta per join per.tipoAplicacaoPergunta as tap where per.questionario = :questionario group by tap";
		Query objQuery = getSession().createQuery(hql);		
		objQuery.setParameter("questionario", questionario);	
		
		List<TipoAplicacaoPergunta> tipoAplicacao = (List<TipoAplicacaoPergunta>) objQuery.list(); 	
		return tipoAplicacao;
	}
}
