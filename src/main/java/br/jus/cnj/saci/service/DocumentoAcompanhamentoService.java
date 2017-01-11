package br.jus.cnj.saci.service;

import java.util.List;

import br.jus.cnj.saci.entity.DocumentoAcompanhamento;
import br.jus.cnj.utils.exception.ServiceException;

public interface DocumentoAcompanhamentoService {

	void persistirEntidade(DocumentoAcompanhamento documentoAcompanhamento) throws ServiceException;

	void excluirEntidade(DocumentoAcompanhamento documentoAcompanhamento) throws ServiceException;
	
	List<DocumentoAcompanhamento> getAll() throws ServiceException;

	DocumentoAcompanhamento pesquisarPorId(int id) throws ServiceException;
	
	void updateEntidade(DocumentoAcompanhamento documentoAcompanhamento) throws ServiceException;
}
