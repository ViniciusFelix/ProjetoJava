package br.jus.cnj.saci.dao;

import java.util.List;

import br.jus.cnj.corporativo.basecrud.GenericDao;
import br.jus.cnj.corporativo.bean.CorporativoOrgao;
import br.jus.cnj.corregedoria.entity.Cartorio;
import br.jus.cnj.saci.entity.Inspecao;
import br.jus.cnj.saci.entity.InspecaoOrgao;
import br.jus.cnj.saci.entity.Questionario;
import br.jus.cnj.utils.exception.DaoException;
import br.jus.cnj.utils.exception.ServiceException;

public interface InspecaoOrgaoDAO extends GenericDao<CorporativoOrgao> {

	public List<CorporativoOrgao> pesquisarUnidadePorQuestionario(Questionario questionario) throws DaoException;
	
	public List<CorporativoOrgao> pesquisarUnidadePorInspecao(Inspecao inspecao) throws DaoException;

	public List<Cartorio> pesquisarCartorioPorQuestionario(Questionario questionario) throws DaoException;

	public List<Cartorio> pesquisarCartorioPorInspecao(Inspecao inspecao) throws DaoException;

	public InspecaoOrgao pesquisarPorOrgao(CorporativoOrgao orgao, Questionario questionario) throws DaoException;
	
	public List<CorporativoOrgao> getOrgaosArvore(Inspecao inspecao) throws ServiceException;

	public InspecaoOrgao pesquisarPorCartorio(Cartorio cartorio, Questionario questionario) throws DaoException;
	
	public InspecaoOrgao pesquisarPorId(int id) throws DaoException;
}
