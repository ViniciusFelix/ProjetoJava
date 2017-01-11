package br.jus.cnj.saci.service.impl;

import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.cnj.corporativo.autenticacao.Credencial;
import br.jus.cnj.saci.dao.TipoFormatoRespostaDAO;
import br.jus.cnj.saci.entity.TipoFormatoResposta;
import br.jus.cnj.saci.service.TipoFormatoRespostaService;
import br.jus.cnj.utils.exception.ServiceException;

@Service("tipoFormatoRespostaService")
public class TipoFormatoRespostaServiceImpl implements TipoFormatoRespostaService {

	@Autowired
	private TipoFormatoRespostaDAO tipoFormatoRespostaDao;
	
	@Transactional
	public boolean persistirEntidade(TipoFormatoResposta tipoFormatoResposta) throws ServiceException {
		boolean descricaoExists = false;
		String descricao = tipoFormatoResposta.getDscTipoResposta();
		try {
			descricaoExists = tipoFormatoRespostaDao.descricaoExists(descricao, 0);			
			if (descricaoExists) {
				return false;
			} else {
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
				Credencial credencial_session = (Credencial) session.getAttribute("credencial");
				int idUsuario = credencial_session.getUsuario().getSeqUsuario();
				tipoFormatoResposta.setUsuInclusao(idUsuario);
				tipoFormatoResposta.setDatInclusao(new Date(System.currentTimeMillis()));
				tipoFormatoRespostaDao.persistirEntidade(tipoFormatoResposta);			
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} 
	}

	@Transactional
	public void excluirEntidade(TipoFormatoResposta tipoFormatoResposta) throws ServiceException {
		try {
			tipoFormatoRespostaDao.excluirEntidade(tipoFormatoResposta);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	public List<TipoFormatoResposta> getAll() throws ServiceException {
		try {
			return tipoFormatoRespostaDao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	public TipoFormatoResposta pesquisarPorId(int id) throws ServiceException {
		try {
			return tipoFormatoRespostaDao.pesquisarPorId(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	public boolean updateEntidade(TipoFormatoResposta tipoFormatoResposta) throws ServiceException {
		boolean descricaoExists = false;
		String descricao = tipoFormatoResposta.getDscTipoResposta();
		int id = tipoFormatoResposta.getId();
		try {
			descricaoExists = tipoFormatoRespostaDao.descricaoExists(descricao, id);			
			if (descricaoExists) {
				return false;
			} else {
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
				Credencial credencial_session = (Credencial) session.getAttribute("credencial");
				int idUsuario = credencial_session.getUsuario().getSeqUsuario();
				tipoFormatoResposta.setUsuAlteracao(idUsuario);
				tipoFormatoResposta.setDatAlteracao(new Date(System.currentTimeMillis()));
				tipoFormatoRespostaDao.updateEntidade(tipoFormatoResposta);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

}
