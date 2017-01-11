package br.jus.cnj.saci.service.impl;

import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.cnj.corporativo.autenticacao.Credencial;
import br.jus.cnj.saci.dao.DocumentoAcompanhamentoDAO;
import br.jus.cnj.saci.entity.DocumentoAcompanhamento;
import br.jus.cnj.saci.service.DocumentoAcompanhamentoService;
import br.jus.cnj.utils.exception.ServiceException;

	@Service("documentoAcompanhamentoService")
	public class DocumentoAcompanhamentoServiceImpl implements DocumentoAcompanhamentoService {

		@Autowired
		private DocumentoAcompanhamentoDAO documentoAcompanhamentoDao;

		@Transactional
		public void persistirEntidade(DocumentoAcompanhamento documentoAcompanhamento) throws ServiceException {
			try {
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
				Credencial credencial_session = (Credencial) session.getAttribute("credencial");
				int idUsuario = credencial_session.getUsuario().getSeqUsuario();
				documentoAcompanhamentoDao.persistirEntidade(documentoAcompanhamento);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServiceException(e.getMessage());
			}
		}
		
		@Transactional
		public void excluirEntidade(DocumentoAcompanhamento documentoAcompanhamento) throws ServiceException {
			try {
				documentoAcompanhamentoDao.excluirEntidade(documentoAcompanhamento);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServiceException(e.getMessage());
			}
		}

		@Transactional
		public List<DocumentoAcompanhamento> getAll() throws ServiceException {
			try {
				return documentoAcompanhamentoDao.getAll();
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServiceException(e.getMessage());
			}
		}

		@Transactional
		public DocumentoAcompanhamento pesquisarPorId(int id) throws ServiceException {
			try {
				return documentoAcompanhamentoDao.pesquisarPorId(id);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServiceException(e.getMessage());
			}
		}

		@Transactional
		public void updateEntidade(DocumentoAcompanhamento documentoAcompanhamento) throws ServiceException {
			try {
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
				Credencial credencial_session = (Credencial) session.getAttribute("credencial");
				int idUsuario = credencial_session.getUsuario().getSeqUsuario();
				documentoAcompanhamentoDao.updateEntidade(documentoAcompanhamento);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServiceException(e.getMessage());
			}
		}



}
