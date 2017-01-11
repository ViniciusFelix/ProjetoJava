package br.jus.cnj.saci.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.jus.cnj.saci.entity.ComplementoResposta;
import br.jus.cnj.saci.entity.Pergunta;
import br.jus.cnj.saci.entity.Resposta;
import br.jus.cnj.utils.exception.ServiceException;

public interface ComplementoRespostaService {
	void persistirEntidade(ComplementoResposta complementoResposta) throws ServiceException;
	
	void excluirEntidade(ComplementoResposta complementoResposta) throws ServiceException;
	
	List<ComplementoResposta> listaComplementoResposta(Resposta id) throws ServiceException;

	void updateEntidade(ComplementoResposta complementoResposta) throws ServiceException;
	
}