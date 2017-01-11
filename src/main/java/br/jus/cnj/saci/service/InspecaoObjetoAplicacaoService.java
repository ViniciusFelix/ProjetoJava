package br.jus.cnj.saci.service;

import java.util.Collection;
import java.util.List;

import br.jus.cnj.saci.entity.Inspecao;
import br.jus.cnj.saci.entity.InspecaoObjetoAplicacao;
import br.jus.cnj.saci.entity.TipoObjetoAplicacao;
import br.jus.cnj.utils.exception.ServiceException;

public interface InspecaoObjetoAplicacaoService {
	boolean persistirEntidade(InspecaoObjetoAplicacao inspecaoObjetoAplicacao) throws ServiceException;

	void excluirEntidade(InspecaoObjetoAplicacao inspecaoObjetoAplicacao) throws ServiceException;

	List<InspecaoObjetoAplicacao> getAll() throws ServiceException;
	
	List<TipoObjetoAplicacao> pesquisaPorInspecao(Inspecao inspecao) throws ServiceException;

	InspecaoObjetoAplicacao pesquisarPorId(int id) throws ServiceException;

	boolean updateEntidade(InspecaoObjetoAplicacao inspecaoObjetoAplicacao) throws ServiceException;
	
	public Collection<InspecaoObjetoAplicacao> convertIdListToObjectList(Collection<String> inspecaoObjetoAplicacao) throws ServiceException;

}
