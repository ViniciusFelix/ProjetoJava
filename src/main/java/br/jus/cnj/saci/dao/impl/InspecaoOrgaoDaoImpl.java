package br.jus.cnj.saci.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.jus.cnj.corporativo.basecrud.impl.GenericDaoImpl;
import br.jus.cnj.corporativo.bean.CorporativoOrgao;
import br.jus.cnj.corregedoria.entity.Cartorio;
import br.jus.cnj.saci.dao.InspecaoOrgaoDAO;
import br.jus.cnj.saci.entity.Inspecao;
import br.jus.cnj.saci.entity.InspecaoOrgao;
import br.jus.cnj.saci.entity.Questionario;
import br.jus.cnj.utils.exception.DaoException;

@Repository
public class InspecaoOrgaoDaoImpl extends GenericDaoImpl<CorporativoOrgao> implements InspecaoOrgaoDAO {
	
	public List<CorporativoOrgao> pesquisarUnidadePorQuestionario(Questionario questionario) throws DaoException {
		String hql = "select org from InspecaoOrgao io join io.orgao as org where io.questionario = :questionario";
		Query objQuery = getSession().createQuery(hql);		
		objQuery.setParameter("questionario", questionario);	
		
		List<CorporativoOrgao> orgaos = (List<CorporativoOrgao>) objQuery.list();		
		return orgaos;
	}
	
	public List<CorporativoOrgao> pesquisarUnidadePorInspecao(Inspecao inspecao) throws DaoException {
		String hql = "select DISTINCT org from InspecaoOrgao io "
				+ "join io.orgao as org "
				+ "join io.inspecao as i "
				+ "where io.inspecao = :inspecao "
				+ "AND i.flgInspecaoPublicada = '1' ";
		Query objQuery = getSession().createQuery(hql);		
		objQuery.setParameter("inspecao", inspecao);	
		
		List<CorporativoOrgao> orgaos = (List<CorporativoOrgao>) objQuery.list();		
		return orgaos;
	}
	
	public List<Cartorio> pesquisarCartorioPorInspecao(Inspecao inspecao) throws DaoException {
		String hql = "select DISTINCT cart from InspecaoOrgao io "
				+ "join io.cartorio as cart "
				+ "join io.inspecao as i "
				+ "where io.inspecao = :inspecao "
				+ "AND i.flgInspecaoPublicada = '1' ";
		Query objQuery = getSession().createQuery(hql);		
		objQuery.setParameter("inspecao", inspecao);	
		
		List<Cartorio> cartorios = (List<Cartorio>) objQuery.list();		
		return cartorios;
	}
	
	public List<Cartorio> pesquisarCartorioPorQuestionario(Questionario questionario) throws DaoException {
		String hql = "select cart from InspecaoOrgao io "
				+ "join io.cartorio as cart "
				+ "where io.questionario = :questionario";
		Query objQuery = getSession().createQuery(hql);		
		objQuery.setParameter("questionario", questionario);	
		
		List<Cartorio> cartorios = (List<Cartorio>) objQuery.list();		
		return cartorios;
	}
	
	
	public InspecaoOrgao pesquisarPorOrgao(CorporativoOrgao orgao, Questionario questionario) throws DaoException {
		String hql = "select io from InspecaoOrgao io"
				+ " WHERE io.questionario = :questionario"
				+ " AND io.orgao = :orgao";
		Query objQuery = getSession().createQuery(hql);		
		objQuery.setParameter("questionario", questionario);
		objQuery.setParameter("orgao", orgao);	
		
		InspecaoOrgao orgaos = (InspecaoOrgao) objQuery.uniqueResult();		
		return orgaos;
	}
	
	public InspecaoOrgao pesquisarPorCartorio(Cartorio cartorio, Questionario questionario) throws DaoException {
		String hql = "select io from InspecaoOrgao io"
				+ " WHERE io.questionario = :questionario"
				+ " AND io.cartorio = :cartorio";
		Query objQuery = getSession().createQuery(hql);		
		objQuery.setParameter("questionario", questionario);
		objQuery.setParameter("cartorio", cartorio);	
		
		InspecaoOrgao cartorios = (InspecaoOrgao) objQuery.uniqueResult();		
		return cartorios;
	}
	
	public List<CorporativoOrgao> getOrgaosArvore(Inspecao inspecao) {
		String hql = "select co from InspecaoOrgao io"
				+ " JOIN io.orgao co"
				+ " WHERE io.inspecao = :inspecao";
		Query objQuery = getSession().createQuery(hql);		
		objQuery.setParameter("inspecao", inspecao);	
		

		List<CorporativoOrgao> orgaos = (List<CorporativoOrgao>) objQuery.list();		
		return orgaos;
	}
	
	public InspecaoOrgao pesquisarPorId(int id) throws DaoException {
		String hql = "select io from InspecaoOrgao io"
				+ " WHERE io.id = :id";
		Query objQuery = getSession().createQuery(hql);		
		objQuery.setParameter("id", id);
		
		InspecaoOrgao cartorios = (InspecaoOrgao) objQuery.uniqueResult();		
		return cartorios;
	}

}
