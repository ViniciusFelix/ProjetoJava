package br.jus.cnj.saci.service;

import java.util.Collection;
import java.util.List;

import br.jus.cnj.saci.entity.Questionario;
import br.jus.cnj.saci.entity.TipoAplicacaoPergunta;
import br.jus.cnj.utils.exception.ServiceException;

public interface QuestionarioService {
	void persistirEntidade(Questionario questionario) throws ServiceException;

	void excluirEntidade(Questionario questionario) throws ServiceException;

	List<Questionario> getAll() throws ServiceException;

	Questionario pesquisarPorId(int id) throws ServiceException;

	boolean updateEntidade(Questionario questionario) throws ServiceException;
	
	public Collection<Questionario> convertIdListToObjectList(Collection<String> questionario) throws ServiceException;
	
	List<TipoAplicacaoPergunta> listaTipoAplicacaoPergunta (Questionario questionario) throws ServiceException;

}
