package br.jus.cnj.saci.service.impl;

import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.cnj.corporativo.autenticacao.Credencial;
import br.jus.cnj.saci.dao.RespostaDAO;
import br.jus.cnj.saci.entity.Resposta;
import br.jus.cnj.saci.service.RespostaService;
import br.jus.cnj.utils.exception.DaoException;
import br.jus.cnj.utils.exception.ServiceException;

@Service("respostaService")
public class RespostaServiceImpl implements RespostaService {
	

	@Autowired
	private RespostaDAO respostaDao;

	@Transactional
	public void persistirEntidade(Resposta resposta) throws ServiceException {
		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			Credencial credencial_session = (Credencial) session.getAttribute("credencial");
			int idUsuario = credencial_session.getUsuario().getSeqUsuario();
			resposta.setUsuInclusao(idUsuario);
			resposta.setDatInclusao(new Date(System.currentTimeMillis()));
			respostaDao.persistirEntidade(resposta);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	
	
	@Transactional
	public void excluirEntidade(Resposta resposta) throws ServiceException {
		try {
			respostaDao.excluirEntidade(resposta);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	public List<Resposta> getAll() throws ServiceException {
		try {
			return respostaDao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	public Resposta pesquisarPorId(int id) throws ServiceException {
		try {
			return respostaDao.pesquisarPorId(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	public void updateEntidade(Resposta resposta) throws ServiceException {
		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			Credencial credencial_session = (Credencial) session.getAttribute("credencial");
			int idUsuario = credencial_session.getUsuario().getSeqUsuario();
			resposta.setUsuAlteracao(idUsuario);
			resposta.setDatAlteracao(new Date(System.currentTimeMillis()));
			respostaDao.updateEntidade(resposta);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}



}
