package br.jus.cnj.saci.service;

import java.util.List;

import br.jus.cnj.saci.entity.TipoStatusDeterminacao;
import br.jus.cnj.utils.exception.ServiceException;


public interface TipoStatusDeterminacaoService {

	List<TipoStatusDeterminacao> getAll() throws ServiceException;

}
