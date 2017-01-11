package br.jus.cnj.saci.service.impl;

import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.cnj.corporativo.autenticacao.Credencial;
import br.jus.cnj.saci.dao.AcompanhamentoDeliberacaoDAO;
import br.jus.cnj.saci.entity.AcompanhamentoDeliberacao;
import br.jus.cnj.saci.service.AcompanhamentoDeliberacaoService;
import br.jus.cnj.utils.exception.ServiceException;

	@Service("acompanhamentoDeliberacaoService")
	public class AcompanhamentoDeliberacaoServiceImpl implements AcompanhamentoDeliberacaoService {

		

		@Autowired
		private AcompanhamentoDeliberacaoDAO acompanhamentoDeliberacaoDao;

		@Transactional
		public void persistirEntidade(AcompanhamentoDeliberacao acompanhamentoDeliberacao) throws ServiceException {
			try {
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
				Credencial credencial_session = (Credencial) session.getAttribute("credencial");
				int idUsuario = credencial_session.getUsuario().getSeqUsuario();
				acompanhamentoDeliberacao.setUsuInclusao(idUsuario);
				acompanhamentoDeliberacao.setDatInclusao(new Date(System.currentTimeMillis()));
				acompanhamentoDeliberacaoDao.persistirEntidade(acompanhamentoDeliberacao);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServiceException(e.getMessage());
			}
		}
		
		@Transactional
		public void excluirEntidade(AcompanhamentoDeliberacao acompanhamentoDeliberacao) throws ServiceException {
			try {
				acompanhamentoDeliberacaoDao.excluirEntidade(acompanhamentoDeliberacao);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServiceException(e.getMessage());
			}
		}

		@Transactional
		public List<AcompanhamentoDeliberacao> getAll() throws ServiceException {
			try {
				return acompanhamentoDeliberacaoDao.getAll();
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServiceException(e.getMessage());
			}
		}

		@Transactional
		public AcompanhamentoDeliberacao pesquisarPorId(int id) throws ServiceException {
			try {
				return acompanhamentoDeliberacaoDao.pesquisarPorId(id);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServiceException(e.getMessage());
			}
		}

		@Transactional
		public void updateEntidade(AcompanhamentoDeliberacao acompanhamentoDeliberacao) throws ServiceException {
			try {
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
				Credencial credencial_session = (Credencial) session.getAttribute("credencial");
				int idUsuario = credencial_session.getUsuario().getSeqUsuario();
				acompanhamentoDeliberacao.setUsuAlteracao(idUsuario);
				acompanhamentoDeliberacao.setDatAlteracao(new Date(System.currentTimeMillis()));
				acompanhamentoDeliberacaoDao.updateEntidade(acompanhamentoDeliberacao);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServiceException(e.getMessage());
			}
		}



}
