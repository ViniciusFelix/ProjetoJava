package br.jus.cnj.saci.service.impl;

import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.cnj.corporativo.autenticacao.Credencial;
import br.jus.cnj.corporativo.bean.CorporativoOrgao;
import br.jus.cnj.corregedoria.entity.Cartorio;
import br.jus.cnj.saci.dao.DeliberacaoDAO;
import br.jus.cnj.saci.entity.AcompanhamentoDeliberacao;
import br.jus.cnj.saci.entity.Deliberacao;
import br.jus.cnj.saci.entity.DocumentoAcompanhamento;
import br.jus.cnj.saci.entity.Inspecao;
import br.jus.cnj.saci.entity.Resposta;
import br.jus.cnj.saci.service.DeliberacaoService;
import br.jus.cnj.utils.exception.ServiceException;

@Service("deliberacaoService")
public class DeliberacaoServiceImpl implements DeliberacaoService {

	@Autowired
	private DeliberacaoDAO deliberacaoDao;

	@Transactional
	public void persistirEntidade(Deliberacao deliberacao) throws ServiceException {
		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			Credencial credencial_session = (Credencial) session.getAttribute("credencial");
			int idUsuario = credencial_session.getUsuario().getSeqUsuario();
			deliberacao.setUsuInclusao(idUsuario);
			deliberacao.setDatInclusao(new Date(System.currentTimeMillis()));
			deliberacaoDao.persistirEntidade(deliberacao);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}


	@Transactional
	public void excluirEntidade(Deliberacao deliberacao) throws ServiceException {
		try {
			deliberacaoDao.excluirEntidade(deliberacao);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	public List<Deliberacao> getAll() throws ServiceException {
		try {
			return deliberacaoDao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}


	@Transactional
	public Deliberacao pesquisarPorId(int id) throws ServiceException {
		try {
			return deliberacaoDao.pesquisarPorId(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}



	@Transactional
	public void updateEntidade(Deliberacao deliberacao) throws ServiceException {
		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			Credencial credencial_session = (Credencial) session.getAttribute("credencial");
			int idUsuario = credencial_session.getUsuario().getSeqUsuario();
			deliberacao.setUsuAlteracao(idUsuario);
			deliberacao.setDatAlteracao(new Date(System.currentTimeMillis()));
			deliberacaoDao.updateEntidade(deliberacao);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}
	
	@Transactional
	public List<Deliberacao> listaDeliberacao(Resposta id) throws ServiceException {
		try {
			return deliberacaoDao.listaDeliberacao(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	@Transactional
	public List<Deliberacao> pesquisaPorInspecaoOrgao(CorporativoOrgao orgao, Inspecao inspecao, Credencial credencialSession) throws ServiceException {
		try {
			return deliberacaoDao.pesquisaPorInspecaoOrgao(orgao, inspecao, credencialSession);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	@Transactional
	public List<Deliberacao> pesquisaPorInspecaoCartorio(Cartorio cartorio, Inspecao inspecao, Credencial credencialSession) throws ServiceException {
		try {
			return deliberacaoDao.pesquisaPorInspecaoCartorio(cartorio, inspecao, credencialSession);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<AcompanhamentoDeliberacao> pesquisaAcompanhamentoPorDeliberacao(
			Deliberacao deliberacao) throws ServiceException {
		try {
			return deliberacaoDao.pesquisaAcompanhamentoPorDeliberacao(deliberacao);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<DocumentoAcompanhamento> pesquisaDocumentosPorDeliberacao(AcompanhamentoDeliberacao acompanhamentoDeliberacao) throws ServiceException {
		try {
			return deliberacaoDao.pesquisaDocumentosPorDeliberacao(acompanhamentoDeliberacao);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
}
