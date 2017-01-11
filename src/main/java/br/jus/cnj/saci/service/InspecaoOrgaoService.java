package br.jus.cnj.saci.service;

import java.util.List;

import br.jus.cnj.corporativo.bean.CorporativoOrgao;
import br.jus.cnj.corregedoria.entity.Cartorio;
import br.jus.cnj.saci.entity.Inspecao;
import br.jus.cnj.saci.entity.InspecaoOrgao;
import br.jus.cnj.saci.entity.Questionario;
import br.jus.cnj.utils.exception.ServiceException;

public interface InspecaoOrgaoService {

	List<CorporativoOrgao> pesquisarUnidadePorQuestionario(Questionario questionario) throws ServiceException;

	List<CorporativoOrgao> pesquisarUnidadePorInspecao(Inspecao inspecao) throws ServiceException;

	List<Cartorio> pesquisarCartorioPorQuestionario(Questionario questionario) throws ServiceException;
	
	List<Cartorio> pesquisarCartorioPorInspecao(Inspecao inspecao) throws ServiceException;
	
	List<CorporativoOrgao> getOrgaosArvore(Inspecao inspecao) throws ServiceException;
	
	InspecaoOrgao pesquisarPorOrgao(CorporativoOrgao orgao, Questionario questionario) throws ServiceException;
	
	InspecaoOrgao pesquisarPorCartorio(Cartorio cartorio, Questionario questionario) throws ServiceException;

	InspecaoOrgao pesquisarPorId(int id) throws ServiceException;

	
}
