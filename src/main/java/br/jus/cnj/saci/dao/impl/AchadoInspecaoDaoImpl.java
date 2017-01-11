package br.jus.cnj.saci.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.jus.cnj.corporativo.basecrud.impl.GenericDaoImpl;
import br.jus.cnj.saci.dao.AchadoInspecaoDAO;
import br.jus.cnj.saci.entity.AchadoInspecao;
import br.jus.cnj.saci.entity.Inspecao;
@Repository
public class AchadoInspecaoDaoImpl extends GenericDaoImpl<AchadoInspecao> implements AchadoInspecaoDAO {

	public List<AchadoInspecao> pesquisarPorInspecao(Inspecao inspecao) {
		
		String hql = "SELECT ai FROM AchadoInspecao ai"
				+" JOIN ai.inspecao i"
				+" WHERE ai.inspecao =:inspecao";
				Query objQuery = getSession().createQuery(hql);		
				objQuery.setParameter("inspecao", inspecao);	
				
				List<AchadoInspecao> achadoInspecaoList = (List<AchadoInspecao>) objQuery.list();

		return achadoInspecaoList;
	}
	
}
