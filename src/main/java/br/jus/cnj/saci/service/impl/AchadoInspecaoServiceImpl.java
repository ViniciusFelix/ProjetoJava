package br.jus.cnj.saci.service.impl;

import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.cnj.corporativo.autenticacao.Credencial;
import br.jus.cnj.saci.dao.AchadoInspecaoDAO;
import br.jus.cnj.saci.entity.AchadoInspecao;
import br.jus.cnj.saci.entity.Inspecao;
import br.jus.cnj.saci.service.AchadoInspecaoService;
import br.jus.cnj.utils.exception.ServiceException;

	@Service("achadoInspecaoService")
	public class AchadoInspecaoServiceImpl implements AchadoInspecaoService {

		

		@Autowired
		private AchadoInspecaoDAO achadoInspecaoDao;

		@Transactional
		public void persistirEntidade(AchadoInspecao achadoInspecao) throws ServiceException {
			try {
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
				Credencial credencial_session = (Credencial) session.getAttribute("credencial");
				int idUsuario = credencial_session.getUsuario().getSeqUsuario();
				achadoInspecao.setUsuInclusao(idUsuario);
				achadoInspecao.setDatInclusao(new Date(System.currentTimeMillis()));
				achadoInspecaoDao.persistirEntidade(achadoInspecao);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServiceException(e.getMessage());
			}
		}
		
		@Transactional
		public void excluirEntidade(AchadoInspecao achadoInspecao) throws ServiceException {
			try {
				achadoInspecaoDao.excluirEntidade(achadoInspecao);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServiceException(e.getMessage());
			}
		}

		@Transactional
		public List<AchadoInspecao> getAll() throws ServiceException {
			try {
				return achadoInspecaoDao.getAll();
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServiceException(e.getMessage());
			}
		}

		@Transactional
		public AchadoInspecao pesquisarPorId(int id) throws ServiceException {
			try {
				return achadoInspecaoDao.pesquisarPorId(id);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServiceException(e.getMessage());
			}
		}

		@Transactional
		public void updateEntidade(AchadoInspecao achadoInspecao) throws ServiceException {
			try {
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
				Credencial credencial_session = (Credencial) session.getAttribute("credencial");
				int idUsuario = credencial_session.getUsuario().getSeqUsuario();
				achadoInspecao.setUsuAlteracao(idUsuario);
				achadoInspecao.setDatAlteracao(new Date(System.currentTimeMillis()));
				achadoInspecaoDao.updateEntidade(achadoInspecao);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServiceException(e.getMessage());
			}
		}

		@Override
		public List<AchadoInspecao> pesquisarPorInspecao(Inspecao inspecao) throws ServiceException{
			try {
				return achadoInspecaoDao.pesquisarPorInspecao(inspecao);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServiceException(e.getMessage());
			}

		}

}
