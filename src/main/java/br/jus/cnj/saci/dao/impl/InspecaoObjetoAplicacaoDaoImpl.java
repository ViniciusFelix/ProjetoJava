package br.jus.cnj.saci.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.jus.cnj.corporativo.basecrud.impl.GenericDaoImpl;
import br.jus.cnj.saci.dao.InspecaoObjetoAplicacaoDAO;
import br.jus.cnj.saci.entity.Inspecao;
import br.jus.cnj.saci.entity.InspecaoObjetoAplicacao;
import br.jus.cnj.saci.entity.TipoObjetoAplicacao;
@Repository
public class InspecaoObjetoAplicacaoDaoImpl extends GenericDaoImpl<InspecaoObjetoAplicacao> implements InspecaoObjetoAplicacaoDAO {
	@Override
	public List<TipoObjetoAplicacao> pesquisaPorInspecao(
			Inspecao inspecao) {
		String hql = "select toa from InspecaoObjetoAplicacao ioa "
				+ "JOIN ioa.inspecao AS i "
				+ "JOIN ioa.tipoObjetoAplicacao AS toa "
				+ "where ioa.inspecao =:inspecao";
		Query objQuery = getSession().createQuery(hql);		
		objQuery.setParameter("inspecao", inspecao);
		
		List<TipoObjetoAplicacao> tipoObjetoAplicacao = (List<TipoObjetoAplicacao>) objQuery.list();
		return tipoObjetoAplicacao;
		
	}
}
