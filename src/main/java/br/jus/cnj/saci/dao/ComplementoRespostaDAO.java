package br.jus.cnj.saci.dao;

import java.io.Serializable;
import java.util.List;

import br.jus.cnj.corporativo.basecrud.GenericDao;
import br.jus.cnj.saci.entity.ComplementoResposta;
import br.jus.cnj.saci.entity.Resposta;
import br.jus.cnj.utils.exception.DaoException;

public interface ComplementoRespostaDAO extends GenericDao<ComplementoResposta> {
	
	public List<ComplementoResposta> listaComplementoResposta(Resposta id) throws DaoException;
}
