package br.jus.cnj.saci.dao;

import java.util.List;

import br.jus.cnj.corporativo.basecrud.GenericDao;
import br.jus.cnj.saci.entity.BoasPraticas;
import br.jus.cnj.saci.entity.InspecaoOrgao;
import br.jus.cnj.utils.exception.DaoException;

	public interface BoasPraticasDAO extends GenericDao<BoasPraticas> {
		
		public List<BoasPraticas> pesquisaPorInspecaoOrgao(InspecaoOrgao inspecaoOrgao) throws DaoException;
		
}
