package br.jus.cnj.saci.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.jus.cnj.corporativo.basecrud.impl.GenericDaoImpl;
import br.jus.cnj.saci.dao.InspecaoDAO;
import br.jus.cnj.saci.entity.Deliberacao;
import br.jus.cnj.saci.entity.Inspecao;
import br.jus.cnj.utils.exception.DaoException;
@Repository
public class InspecaoDaoImpl extends GenericDaoImpl<Inspecao> implements InspecaoDAO {

	private Query objQuery;

	public int pesquisarUltimoNumInspecao(Inspecao inspecao) throws DaoException {
		String hql = "select i from Inspecao i where i.tipoInspecao = :tipoInspecao ORDER BY i.numInspecao DESC";
		Query objQuery = getSession().createQuery(hql);		
		objQuery.setParameter("tipoInspecao", inspecao.getTipoInspecao());	
		
		List<Inspecao> inspecaoList = (List<Inspecao>) objQuery.list();
		int numInspecao = 1;
		if (inspecaoList.size() > 0) { 
			numInspecao = inspecaoList.get(0).getNumInspecao() + 1;
		}
		return numInspecao;
	}
	
	public boolean verificaDeliberacaoParaInspecao(Inspecao inspecao){
		
		String hql = "select d from Deliberacao d "
				+ "Join d.resposta r "
				+ "Join r.inspecaoOrgao io "
				+ "WHERE io.inspecao = :inspecao AND d.tipoDeliberacao = 2";
		Query objQuery = getSession().createQuery(hql);		
		objQuery.setParameter("inspecao", inspecao);	
		List<Deliberacao> del = (List<Deliberacao>) objQuery.list();
		
		if(del.size() > 0){
		
			return true;
		
		} else {
			
			return false;			
		
		}
	}
	
	public List<Deliberacao>  decisaoPorInspecao(Inspecao inspecao) {
		
		String hql = "select d from Deliberacao d "
				+ "Join d.resposta r "
				+ "Join r.inspecaoOrgao io "
				+ "WHERE io.inspecao = :inspecao AND d.tipoDeliberacao = 2";
		Query objQuery = getSession().createQuery(hql);		
		objQuery.setParameter("inspecao", inspecao);	
		List<Deliberacao> del = (List<Deliberacao>) objQuery.list();
		return del;
	}
	
	public List<Inspecao>  getAllJusticaEstaduall(int tj) {
		
		String hql = "select i from InspecaoOrgao io "
				+ "Join io.orgao o "
				+ "Join io.inspecao i "
				+ "WHERE o.hierarquia LIKE CONCAT('%:', :tj  ,':,:13606:,:1:') "
				+ "OR o.id =:tj "
				+ "GROUP BY i.id";
		Query objQuery = getSession().createQuery(hql);		
		objQuery.setParameter("tj", tj);	
		List<Inspecao> insp = (List<Inspecao>) objQuery.list();
		
		return insp;
	}
	
	public List<Inspecao>  getAllJusticaEstadual(int tj) {
		
		Query query = getSession().createSQLQuery(
				"SELECT distinct i.* FROM `saci`.`inspecao` i"
				+" JOIN `saci`.`inspecao_orgao` io ON io.seq_inspecao = i.seq_inspecao"
				+" JOIN `corporativo`.`orgao` co ON co.seq_orgao = io.seq_orgao"
				+" WHERE co.COD_HIERARQUIA LIKE CONCAT('%:', :tj  ,':,:13606:,:1:') OR co.SEQ_ORGAO = :tj")
				.addEntity(Inspecao.class)
				.setParameter("tj", tj);
				List<Inspecao> result = query.list();

		return result;
	}
	
}
