package br.jus.cnj.saci.dao;

import java.util.List;

import br.jus.cnj.corporativo.basecrud.GenericDao;
import br.jus.cnj.saci.entity.Deliberacao;
import br.jus.cnj.saci.entity.Inspecao;
import br.jus.cnj.utils.exception.DaoException;

public interface InspecaoDAO extends GenericDao<Inspecao> {

	public int pesquisarUltimoNumInspecao(Inspecao inspecao) throws DaoException;

	public List<Inspecao> getAllJusticaEstadual(int tj);
	
	public boolean verificaDeliberacaoParaInspecao(Inspecao inspecao);

	public List<Deliberacao> decisaoPorInspecao(Inspecao inspecao);
}
