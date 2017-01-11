package br.jus.cnj.corregedoria.service;

import java.util.List;

import br.jus.cnj.corporativo.entity.Cidade;
import br.jus.cnj.corregedoria.entity.Cartorio;
import br.jus.cnj.utils.exception.ServiceException;

public interface CartorioService {

	List<Cartorio> getAll() throws ServiceException;

	List<Cartorio> pesquisarPorCidade(Cidade b) throws ServiceException;
	
	Cartorio pesquisarPorId(int id) throws ServiceException;
	
}
