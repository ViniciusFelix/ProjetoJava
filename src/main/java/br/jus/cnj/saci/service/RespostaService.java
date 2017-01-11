package br.jus.cnj.saci.service;

import java.util.List;

import br.jus.cnj.saci.entity.Resposta;
import br.jus.cnj.utils.exception.ServiceException;



public interface RespostaService {
	
	void persistirEntidade(Resposta resposta) throws ServiceException;
		
	void excluirEntidade(Resposta resposta) throws ServiceException;	
	
	List<Resposta> getAll() throws ServiceException;

	Resposta pesquisarPorId(int id) throws ServiceException;
	
	void updateEntidade(Resposta resposta) throws ServiceException;

}
