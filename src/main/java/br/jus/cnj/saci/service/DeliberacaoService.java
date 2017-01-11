package br.jus.cnj.saci.service;

import java.util.List;

import br.jus.cnj.corporativo.autenticacao.Credencial;
import br.jus.cnj.corporativo.bean.CorporativoOrgao;
import br.jus.cnj.corregedoria.entity.Cartorio;
import br.jus.cnj.saci.entity.AcompanhamentoDeliberacao;
import br.jus.cnj.saci.entity.Deliberacao;
import br.jus.cnj.saci.entity.DocumentoAcompanhamento;
import br.jus.cnj.saci.entity.Inspecao;
import br.jus.cnj.saci.entity.Resposta;
import br.jus.cnj.utils.exception.ServiceException;

public interface DeliberacaoService {
	
	void persistirEntidade(Deliberacao deliberacao) throws ServiceException;

	void excluirEntidade(Deliberacao deliberacao) throws ServiceException;

	List<Deliberacao> getAll() throws ServiceException;

	Deliberacao pesquisarPorId(int id) throws ServiceException;

	void updateEntidade(Deliberacao deliberacao) throws ServiceException;
	
	List<Deliberacao> listaDeliberacao(Resposta id) throws ServiceException;
	
	List<Deliberacao> pesquisaPorInspecaoOrgao(CorporativoOrgao orgao, Inspecao inspecao, Credencial credencialSession) throws ServiceException;
	
	List<Deliberacao> pesquisaPorInspecaoCartorio(Cartorio cartorio, Inspecao inspecao, Credencial credencialSession) throws ServiceException;
	
	List<AcompanhamentoDeliberacao> pesquisaAcompanhamentoPorDeliberacao(Deliberacao deliberacao) throws ServiceException;
	
	List<DocumentoAcompanhamento> pesquisaDocumentosPorDeliberacao(AcompanhamentoDeliberacao acompanhamentoDeliberacao) throws ServiceException;
	
}
