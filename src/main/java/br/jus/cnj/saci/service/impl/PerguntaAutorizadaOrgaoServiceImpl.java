package br.jus.cnj.saci.service.impl;


	import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.jus.cnj.corporativo.autenticacao.Credencial;
import br.jus.cnj.saci.dao.PerguntaAutorizadaOrgaoDAO;
import br.jus.cnj.saci.entity.InspecaoOrgao;
import br.jus.cnj.saci.entity.Pergunta;
import br.jus.cnj.saci.entity.PerguntaAutorizadaOrgao;
import br.jus.cnj.saci.service.PerguntaAutorizadaOrgaoService;
import br.jus.cnj.utils.exception.ServiceException;
	
	@Service("perguntaAutorizadaOrgaoService")
	public class PerguntaAutorizadaOrgaoServiceImpl implements PerguntaAutorizadaOrgaoService {		

		@Autowired
		private PerguntaAutorizadaOrgaoDAO perguntaAutorizadaOrgaoDao;

		@Override
		public void persistirEntidade(
				PerguntaAutorizadaOrgao perguntaAutorizadaOrgao)
				throws ServiceException {
			try {
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
				Credencial credencial_session = (Credencial) session.getAttribute("credencial");
				int idUsuario = credencial_session.getUsuario().getSeqUsuario();
				perguntaAutorizadaOrgao.setUsuInclusao(idUsuario);
				perguntaAutorizadaOrgao.setDatInclusao(new Date(System.currentTimeMillis()));
				perguntaAutorizadaOrgaoDao.persistirEntidade(perguntaAutorizadaOrgao);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServiceException(e.getMessage());
			}
			
		}

		@Override
		public void excluirEntidade(
				PerguntaAutorizadaOrgao perguntaAutorizadaOrgao)
				throws ServiceException {
			try {
				perguntaAutorizadaOrgaoDao.excluirEntidade(perguntaAutorizadaOrgao);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServiceException(e.getMessage());
			}
			
		}

		@Override
		public List<PerguntaAutorizadaOrgao> getAll() throws ServiceException {
			try {
				return perguntaAutorizadaOrgaoDao.getAll();
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServiceException(e.getMessage());
			}
		}

		@Override
		public PerguntaAutorizadaOrgao pesquisarPorId(int id)
				throws ServiceException {
			try {
				return perguntaAutorizadaOrgaoDao.pesquisarPorId(id);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServiceException(e.getMessage());
			}
		}

		@Override
		public void updateEntidade(
				PerguntaAutorizadaOrgao perguntaAutorizadaOrgao)
				throws ServiceException {

			try {
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
				Credencial credencial_session = (Credencial) session.getAttribute("credencial");
				int idUsuario = credencial_session.getUsuario().getSeqUsuario();
				perguntaAutorizadaOrgao.setUsuAlteracao(idUsuario);
				perguntaAutorizadaOrgao.setDatAlteracao(new Date(System.currentTimeMillis()));
				perguntaAutorizadaOrgaoDao.updateEntidade(perguntaAutorizadaOrgao);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServiceException(e.getMessage());
			}

			
		}

		@Override
		public List<Pergunta> pesquisaPorPerguntaAta(
				InspecaoOrgao inspecaoOrgao)
				throws ServiceException {
			try {
				return perguntaAutorizadaOrgaoDao.pesquisaPorPerguntaAta(inspecaoOrgao);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServiceException(e.getMessage());
			}
		}


		@Override
		public List<Pergunta> pesquisaAtaTribunal(
				InspecaoOrgao inspecaoOrgao)
				throws ServiceException {
			try {
				return perguntaAutorizadaOrgaoDao.pesquisaAtaTribunal(inspecaoOrgao);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServiceException(e.getMessage());
			}
		}


		@Override
		public	boolean pesquisaPerguntaAutorizada(Pergunta p, InspecaoOrgao ata) throws ServiceException {
			try {
				return perguntaAutorizadaOrgaoDao.pesquisaPerguntaAutorizada(p, ata);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServiceException(e.getMessage());
			}
		}

		@Override
		public void removerPerguntasAutorizadas(InspecaoOrgao inspecaoOrgao)
				throws ServiceException {
			try {
				perguntaAutorizadaOrgaoDao.removerPerguntasAutorizadas(inspecaoOrgao);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServiceException(e.getMessage());
			}
		}

		
}
