package br.jus.cnj.saci.service;

import java.util.List;

import br.jus.cnj.saci.entity.BoasPraticas;
import br.jus.cnj.saci.entity.InspecaoOrgao;
import br.jus.cnj.utils.exception.ServiceException;

public interface BoasPraticasService {

	void persistirEntidade(BoasPraticas boasPraticas) throws ServiceException;

	void excluirEntidade(BoasPraticas boasPraticas) throws ServiceException;

	List<BoasPraticas> getAll() throws ServiceException;
	
	List<BoasPraticas> pesquisaPorInspecaoOrgao(InspecaoOrgao inspecaoOrgao) throws ServiceException;

	BoasPraticas pesquisarPorId(int id) throws ServiceException;

	void updateEntidade(BoasPraticas boasPraticas) throws ServiceException;

}
