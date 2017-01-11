package br.jus.cnj.saci.service;

import java.util.List;

import br.jus.cnj.saci.entity.InspecaoOrgao;
import br.jus.cnj.saci.entity.Pergunta;
import br.jus.cnj.saci.entity.PerguntaAutorizadaOrgao;
import br.jus.cnj.utils.exception.ServiceException;

	public interface PerguntaAutorizadaOrgaoService {

		void persistirEntidade(PerguntaAutorizadaOrgao perguntaAutorizadaOrgao) throws ServiceException;

		void excluirEntidade(PerguntaAutorizadaOrgao perguntaAutorizadaOrgao) throws ServiceException;

		List<PerguntaAutorizadaOrgao> getAll() throws ServiceException;
		
		List<Pergunta> pesquisaPorPerguntaAta(InspecaoOrgao inspecaoOrgao) throws ServiceException;
		
		List<Pergunta> pesquisaAtaTribunal(InspecaoOrgao inspecaoOrgao) throws ServiceException;
		
		void removerPerguntasAutorizadas(InspecaoOrgao inspecaoOrgao) throws ServiceException;

		PerguntaAutorizadaOrgao pesquisarPorId(int id) throws ServiceException;

		void updateEntidade(PerguntaAutorizadaOrgao perguntaAutorizadaOrgao) throws ServiceException;

		boolean pesquisaPerguntaAutorizada(Pergunta p, InspecaoOrgao ata) throws ServiceException;

}
