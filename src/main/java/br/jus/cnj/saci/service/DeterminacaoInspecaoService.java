package br.jus.cnj.saci.service;

import java.util.List;

import br.jus.cnj.saci.entity.AchadoInspecao;
import br.jus.cnj.saci.entity.DeterminacaoInspecao;
import br.jus.cnj.saci.entity.Inspecao;
import br.jus.cnj.utils.exception.ServiceException;

public interface DeterminacaoInspecaoService {


	boolean persistirEntidade(DeterminacaoInspecao determinacaoInspecao) throws ServiceException;

	void excluirEntidade(DeterminacaoInspecao determinacaoInspecao) throws ServiceException;

	boolean updateEntidade(DeterminacaoInspecao determinacaoInspecao) throws ServiceException;
	
}
