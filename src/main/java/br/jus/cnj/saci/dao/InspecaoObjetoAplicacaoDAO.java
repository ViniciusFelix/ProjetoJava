package br.jus.cnj.saci.dao;

import java.util.List;

import br.jus.cnj.corporativo.autenticacao.Credencial;
import br.jus.cnj.corporativo.basecrud.GenericDao;
import br.jus.cnj.corregedoria.entity.Cartorio;
import br.jus.cnj.saci.entity.Deliberacao;
import br.jus.cnj.saci.entity.Inspecao;
import br.jus.cnj.saci.entity.InspecaoObjetoAplicacao;
import br.jus.cnj.saci.entity.TipoObjetoAplicacao;
import br.jus.cnj.utils.exception.DaoException;

public interface InspecaoObjetoAplicacaoDAO extends GenericDao<InspecaoObjetoAplicacao> {
	public List<TipoObjetoAplicacao> pesquisaPorInspecao(Inspecao inspecao) throws DaoException;

}
