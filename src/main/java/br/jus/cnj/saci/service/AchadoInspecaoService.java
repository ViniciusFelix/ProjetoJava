package br.jus.cnj.saci.service;

import java.util.List;

import br.jus.cnj.saci.entity.AchadoInspecao;
import br.jus.cnj.saci.entity.Inspecao;
import br.jus.cnj.utils.exception.ServiceException;

public interface AchadoInspecaoService {
	
	void persistirEntidade(AchadoInspecao achadoInspecao) throws ServiceException;

	void excluirEntidade(AchadoInspecao achadoInspecao) throws ServiceException;

	List<AchadoInspecao> getAll() throws ServiceException;

	AchadoInspecao pesquisarPorId(int id) throws ServiceException;
	
	List<AchadoInspecao> pesquisarPorInspecao(Inspecao inspecao) throws ServiceException;
	
	void updateEntidade(AchadoInspecao achadoInspecao) throws ServiceException;

}
