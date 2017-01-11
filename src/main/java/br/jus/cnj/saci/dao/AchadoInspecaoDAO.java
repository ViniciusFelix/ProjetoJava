package br.jus.cnj.saci.dao;

import java.util.List;

import br.jus.cnj.corporativo.basecrud.GenericDao;
import br.jus.cnj.saci.entity.AchadoInspecao;
import br.jus.cnj.saci.entity.Inspecao;

	public interface AchadoInspecaoDAO extends GenericDao<AchadoInspecao> {

		List<AchadoInspecao> pesquisarPorInspecao(Inspecao inspecao);

}
