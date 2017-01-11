package br.jus.cnj.saci.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.jus.cnj.corporativo.basecrud.impl.GenericDaoImpl;
import br.jus.cnj.saci.dao.PerguntaDAO;
import br.jus.cnj.saci.entity.ModeloPergunta;
import br.jus.cnj.saci.entity.Pergunta;
import br.jus.cnj.saci.entity.Questionario;
import br.jus.cnj.saci.entity.TipoAplicacaoPergunta;
import br.jus.cnj.utils.exception.DaoException;

@Repository
public class PerguntaDaoImpl extends GenericDaoImpl<Pergunta> implements PerguntaDAO {
	
	
	@SuppressWarnings("unchecked")	
	public Pergunta pesquisarPorNumOrdem(Serializable numOrdem, TipoAplicacaoPergunta tipoAplicacaoPergunta, Questionario questionario) throws DaoException {
		String hql = "select p from Pergunta p where p.numOrdem = :numOrdem AND p.tipoAplicacaoPergunta = :tipoAplicacaoPergunta AND p.questionario = :questionario";
		Query objQuery = getSession().createQuery(hql);		
		objQuery.setParameter("numOrdem", numOrdem);	
		objQuery.setParameter("tipoAplicacaoPergunta", tipoAplicacaoPergunta);
		objQuery.setParameter("questionario", questionario);	
		
		Pergunta pergunta = (Pergunta) objQuery.uniqueResult();		
		return pergunta;
	}
	
	public List<Pergunta> pesquisarPorQuestionario(Questionario questionario) throws DaoException {
		String hql = "select p from Pergunta p where p.questionario = :questionario ORDER BY p.numOrdem";
		Query objQuery = getSession().createQuery(hql);		
		objQuery.setParameter("questionario", questionario);	
		
		List<Pergunta> perguntas = (List<Pergunta>) objQuery.list();		
		return perguntas;
	}
	
	public int pesquisarUltimoNumOrdem(Pergunta pergunta) throws DaoException {
		String hql = "select p from Pergunta p where p.tipoAplicacaoPergunta = :tipoAplicacaoPergunta AND questionario = :questionario ORDER BY p.numOrdem DESC";
		Query objQuery = getSession().createQuery(hql);		
		objQuery.setParameter("tipoAplicacaoPergunta", pergunta.getTipoAplicacaoPergunta());
		objQuery.setParameter("questionario", pergunta.getQuestionario());	
		
		List<Pergunta> perguntaList = (List<Pergunta>) objQuery.list();
		int numOrdem = 1;
		if (perguntaList.size() > 0) { 
			numOrdem = perguntaList.get(0).getNumOrdem() + 1;
		}
		return numOrdem;
	}
	
	public void mudarOrdem(int ini, int fim, TipoAplicacaoPergunta tap, Questionario questionario, int aux) throws DaoException {
		String hql = "update Pergunta p set p.numOrdem = p.numOrdem - 1 where p.numOrdem > :ini AND p.numOrdem <= :fim AND p.tipoAplicacaoPergunta = :tipoAplicacaoPergunta AND questionario = :questionario";
		if (aux == ini) {
			hql = "update Pergunta p set p.numOrdem = p.numOrdem + 1 where p.numOrdem < :ini AND p.numOrdem >= :fim AND p.tipoAplicacaoPergunta = :tipoAplicacaoPergunta AND questionario = :questionario";
		}
		Query objQuery = getSession().createQuery(hql);
		objQuery.setParameter("ini", ini);
		objQuery.setParameter("fim", fim);
		objQuery.setParameter("tipoAplicacaoPergunta", tap);
		objQuery.setParameter("questionario", questionario);
		
		objQuery.executeUpdate();
	}
	
}
