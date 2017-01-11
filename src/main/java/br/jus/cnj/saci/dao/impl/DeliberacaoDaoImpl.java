package br.jus.cnj.saci.dao.impl;

import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.jus.cnj.corporativo.autenticacao.Credencial;
import br.jus.cnj.corporativo.basecrud.impl.GenericDaoImpl;
import br.jus.cnj.corporativo.bean.CorporativoOrgao;
import br.jus.cnj.corregedoria.entity.Cartorio;
import br.jus.cnj.saci.dao.DeliberacaoDAO;
import br.jus.cnj.saci.entity.AcompanhamentoDeliberacao;
import br.jus.cnj.saci.entity.Deliberacao;
import br.jus.cnj.saci.entity.DocumentoAcompanhamento;
import br.jus.cnj.saci.entity.Inspecao;
import br.jus.cnj.saci.entity.Resposta;
import br.jus.cnj.utils.exception.DaoException;


@Repository
public class DeliberacaoDaoImpl extends GenericDaoImpl<Deliberacao> implements DeliberacaoDAO {

	private Query objQuery;

	@Override
	public List<Deliberacao> listaDeliberacao(Resposta id) throws DaoException {
		String hql = "select del from Deliberacao del where del.resposta = :id";
		Query objQuery = getSession().createQuery(hql);		
		objQuery.setParameter("id", id);
		
		List<Deliberacao> deliberacaoList = (List<Deliberacao>) objQuery.list(); 	
		if (deliberacaoList.size() > 0) {
			return deliberacaoList;
		}else{
			return null;
		}
	}

	@Override
	public List<Deliberacao> pesquisaPorInspecaoOrgao(CorporativoOrgao orgao,
			Inspecao inspecao, Credencial credencialSession) throws DaoException {
		
		String hql = "SELECT del FROM Deliberacao "
				+ "JOIN del.resposta r"
				+ "JOIN r.inspecaoOrgao io"
				+ "WHERE io.orgao = :orgao "
				+ "AND io.inspecao = :inspecao "
				+ "AND del.tipoDeliberacao.id = 2 ";
		
		if(credencialSession.getPerfil().contains("Tribunal")){
			hql = hql + "AND del.flagResolvida = 'N'"
					+ " AND del.numProcedimento IS NULL";
		}
				 
		Query objQuery = getSession().createQuery(hql);		
		objQuery.setParameter("orgao", orgao);	
		objQuery.setParameter("inspecao", inspecao);
		
		List<Deliberacao> deliberacaoList = (List<Deliberacao>) objQuery.list(); 
		return deliberacaoList;
	}

	@Override
	public List<Deliberacao> pesquisaPorInspecaoCartorio(Cartorio cartorio,
			Inspecao inspecao, Credencial credencialSession) throws DaoException {
		
		String hql = "SELECT del FROM Deliberacao del "
				+ "JOIN del.resposta AS r "
				+ "JOIN r.inspecaoOrgao AS io "
				+ "WHERE io.cartorio = :cartorio "
				+ "AND io.inspecao = :inspecao "
				+ "AND del.tipoDeliberacao.id = 2";
		
		if(credencialSession.getPerfil().contains("Tribunal")){
			hql = hql + "AND del.flagResolvida = 'N'";
		}
		
		Query objQuery = getSession().createQuery(hql);		
		objQuery.setParameter("cartorio", cartorio);	
		objQuery.setParameter("inspecao", inspecao);
		
		List<Deliberacao> deliberacaoList = (List<Deliberacao>) objQuery.list(); 
		return deliberacaoList;
		
	}

	@Override
	public List<AcompanhamentoDeliberacao> pesquisaAcompanhamentoPorDeliberacao(Deliberacao deliberacao) throws DaoException {
		String hql = "Select ad FROM AcompanhamentoDeliberacao ad "
				+ " WHERE ad.deliberacao =:deliberacao ";
		objQuery = getSession().createQuery(hql);		
		objQuery.setParameter("deliberacao", deliberacao);
		
		List<AcompanhamentoDeliberacao> deliberacaoList =  objQuery.list(); 
		return deliberacaoList;
	}

	@Override
	public List<DocumentoAcompanhamento> pesquisaDocumentosPorDeliberacao(
			AcompanhamentoDeliberacao acompanhamentoDeliberacao) throws DaoException {
		String hql = "Select dd FROM DocumentoAcompanhamento dd "
				+ " WHERE dd.acompanhamentoDeliberacao =:acompanhamentoDeliberacao ";
		objQuery = getSession().createQuery(hql);		
		objQuery.setParameter("acompanhamentoDeliberacao", acompanhamentoDeliberacao);
		
		List<DocumentoAcompanhamento> documentoDeliberacaoList =  objQuery.list(); 
		return documentoDeliberacaoList;
	}
	
	
	
}
