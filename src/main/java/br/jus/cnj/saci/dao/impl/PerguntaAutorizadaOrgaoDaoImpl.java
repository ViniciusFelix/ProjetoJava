package br.jus.cnj.saci.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.jus.cnj.corporativo.basecrud.impl.GenericDaoImpl;
import br.jus.cnj.saci.dao.PerguntaAutorizadaOrgaoDAO;
import br.jus.cnj.saci.entity.InspecaoOrgao;
import br.jus.cnj.saci.entity.Pergunta;
import br.jus.cnj.saci.entity.PerguntaAutorizadaOrgao;

	@Repository
	public class PerguntaAutorizadaOrgaoDaoImpl extends GenericDaoImpl<PerguntaAutorizadaOrgao> implements PerguntaAutorizadaOrgaoDAO {

		@Override
		public List<Pergunta> pesquisaPorPerguntaAta(
				InspecaoOrgao inspecaoOrgao) {
			String hql = "select p from PerguntaAutorizadaOrgao pa "
					+ "JOIN pa.pergunta AS p "
					+ "where pa.inspecaoOrgao =:inspecaoOrgao ";
			Query objQuery = getSession().createQuery(hql);		
			objQuery.setParameter("inspecaoOrgao", inspecaoOrgao);
			
			List<Pergunta> perguntas = (List<Pergunta>) objQuery.list();
			return perguntas;
			
		}
		

		@Override
		public List<Pergunta> pesquisaAtaTribunal(
				InspecaoOrgao inspecaoOrgao) {
			String hql = "select p from PerguntaAutorizadaOrgao pa "
					+ "JOIN pa.pergunta AS p "
					+ "LEFT JOIN p.respostas AS r "
					+ "where pa.inspecaoOrgao =:inspecaoOrgao "
					+ "AND (r.dscResposta IS NULL OR r.dscResposta = '')";
			Query objQuery = getSession().createQuery(hql);		
			objQuery.setParameter("inspecaoOrgao", inspecaoOrgao);
			
			List<Pergunta> perguntas = (List<Pergunta>) objQuery.list();
			return perguntas;
			
		}
		
		public boolean pesquisaPerguntaAutorizada(Pergunta pergunta, InspecaoOrgao inspecaoOrgao){
			
			String hql = "select pa from PerguntaAutorizadaOrgao pa "
					+ "where pa.inspecaoOrgao =:inspecaoOrgao "
					+ "AND pa.pergunta =:pergunta";
			Query objQuery = getSession().createQuery(hql);		
			objQuery.setParameter("inspecaoOrgao", inspecaoOrgao);	
			objQuery.setParameter("pergunta", pergunta);
			
			List<PerguntaAutorizadaOrgao> perguntaAutorizada = (List<PerguntaAutorizadaOrgao>) objQuery.list();
			if(perguntaAutorizada.size() > 0){
				return true;
			} else {
				return false;
			}
		}


		@Override
		public void removerPerguntasAutorizadas(InspecaoOrgao inspecaoOrgao) {
			String hql = "DELETE FROM PerguntaAutorizadaOrgao ao WHERE ao.inspecaoOrgao =:inspecaoOrgao";
			Query objQuery = getSession().createQuery(hql);		
			objQuery.setParameter("inspecaoOrgao", inspecaoOrgao);
			
			objQuery.executeUpdate();			
		}


}
