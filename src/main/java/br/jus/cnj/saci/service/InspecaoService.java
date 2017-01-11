package br.jus.cnj.saci.service;

import java.util.List;

import br.jus.cnj.saci.entity.Deliberacao;
import br.jus.cnj.saci.entity.Inspecao;
import br.jus.cnj.utils.exception.ServiceException;

public interface InspecaoService {
	boolean persistirEntidade(Inspecao inspecao) throws ServiceException;

	void excluirEntidade(Inspecao inspecao) throws ServiceException;

	List<Inspecao> getAll() throws ServiceException;
	
	List<Inspecao> getAllJusticaEstadual(int i) throws ServiceException;
	
	Inspecao pesquisarPorId(int id) throws ServiceException;

	boolean updateEntidade(Inspecao inspecao) throws ServiceException;
	
	boolean verificaDeliberacaoParaInspecao(Inspecao inspecao) throws ServiceException;
	
	List<Deliberacao> decisaoPorInspecao(Inspecao inspecao) throws ServiceException;
	
	public int geraNumInspecao(Inspecao inspecao) throws ServiceException;

}
