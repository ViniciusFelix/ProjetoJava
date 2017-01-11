package br.jus.cnj.saci.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.jus.cnj.corporativo.basecrud.impl.GenericDaoImpl;
import br.jus.cnj.saci.dao.BoasPraticasDAO;
import br.jus.cnj.saci.entity.BoasPraticas;
import br.jus.cnj.saci.entity.InspecaoOrgao;
import br.jus.cnj.utils.exception.DaoException;

	@Repository
	public class BoasPraticasDaoImpl extends GenericDaoImpl<BoasPraticas> implements BoasPraticasDAO {


		@Override
		public List<BoasPraticas> pesquisaPorInspecaoOrgao(
				InspecaoOrgao inspecaoOrgao) throws DaoException {
			String hql = "select b from BoasPraticas b where b.inspecaoOrgao = :inspecaoOrgao";
			Query objQuery = getSession().createQuery(hql);		
			objQuery = getSession().createQuery(hql);		
			objQuery.setParameter("inspecaoOrgao", inspecaoOrgao);
			
			List<BoasPraticas> boasPraticasListagem = (List<BoasPraticas>) objQuery.list(); 	
			if (boasPraticasListagem.size() > 0) {
				return boasPraticasListagem;
			}else{
				return null;
			}
		}

}
