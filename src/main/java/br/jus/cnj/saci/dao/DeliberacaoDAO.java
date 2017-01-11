package br.jus.cnj.saci.dao;

import java.util.List;

import br.jus.cnj.corporativo.autenticacao.Credencial;
import br.jus.cnj.corporativo.basecrud.GenericDao;
import br.jus.cnj.corporativo.bean.CorporativoOrgao;
import br.jus.cnj.corregedoria.entity.Cartorio;
import br.jus.cnj.saci.entity.AcompanhamentoDeliberacao;
import br.jus.cnj.saci.entity.Deliberacao;
import br.jus.cnj.saci.entity.DocumentoAcompanhamento;
import br.jus.cnj.saci.entity.Inspecao;
import br.jus.cnj.saci.entity.Resposta;
import br.jus.cnj.utils.exception.DaoException;

public interface DeliberacaoDAO extends GenericDao<Deliberacao> {

	public List<Deliberacao> listaDeliberacao(Resposta id) throws DaoException;

	public List<Deliberacao> pesquisaPorInspecaoOrgao(CorporativoOrgao orgao, Inspecao inspecao, Credencial credencialSession) throws DaoException;

	public List<Deliberacao> pesquisaPorInspecaoCartorio(Cartorio cartorio, Inspecao inspecao, Credencial credencialSession) throws DaoException;

	public List<AcompanhamentoDeliberacao> pesquisaAcompanhamentoPorDeliberacao(Deliberacao deliberacao) throws DaoException;

	public List<DocumentoAcompanhamento> pesquisaDocumentosPorDeliberacao(AcompanhamentoDeliberacao acompanhamentoDeliberacao) throws DaoException;
	
}
