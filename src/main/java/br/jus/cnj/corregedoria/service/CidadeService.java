package br.jus.cnj.corregedoria.service;

import java.util.List;

import br.jus.cnj.corporativo.entity.Cidade;
import br.jus.cnj.utils.exception.ServiceException;

public interface CidadeService {

	List<Cidade> getAll() throws ServiceException;

	List<Cidade> pesquisarPorUf(String uf) throws ServiceException;
	
	Cidade pesquisarPorId(int id) throws ServiceException;
}
