package br.jus.cnj.saci.service.impl;

import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.cnj.corporativo.autenticacao.Credencial;
import br.jus.cnj.saci.dao.TipoComplementoRespostaDAO;
import br.jus.cnj.saci.entity.TipoComplementoResposta;
import br.jus.cnj.saci.service.TipoComplementoRespostaService;
import br.jus.cnj.utils.exception.ServiceException;

@Service("tipoComplementoRespostaService")				   
public class TipoComplementoRespostaServiceImpl implements TipoComplementoRespostaService {
	
	@Autowired
	private TipoComplementoRespostaDAO tipoComplementoRespostaDao;

	@Transactional
	public List<TipoComplementoResposta> getAll() throws ServiceException {
		try {
			return tipoComplementoRespostaDao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	@Transactional
	public boolean persistirEntidade(TipoComplementoResposta tipoComplementoResposta) throws ServiceException {
		boolean descricaoExists = false;
		String descricao = tipoComplementoResposta.getDscTipoComplementoResposta();
		try {
			descricaoExists = tipoComplementoRespostaDao.descricaoExists(descricao, 0);			
			if (descricaoExists) {
				return false;
			} else{
				@SuppressWarnings("unused")
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
				tipoComplementoRespostaDao.persistirEntidade(tipoComplementoResposta);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	public void excluirEntidade(TipoComplementoResposta tipoComplementoResposta) throws ServiceException {
		try {
			tipoComplementoRespostaDao.excluirEntidade(tipoComplementoResposta);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	public boolean updateEntidade(TipoComplementoResposta tipoComplementoResposta) throws ServiceException {
		String descricao = tipoComplementoResposta.getDscTipoComplementoResposta();
		int id = tipoComplementoResposta.getId();
		boolean descricaoExists = false;
		try {
			descricaoExists = tipoComplementoRespostaDao.descricaoExists(descricao, id);			
			if (descricaoExists) {
				return false;
			} else{
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
				@SuppressWarnings("unused")
				Credencial credencial_session = (Credencial) session.getAttribute("credencial");
				tipoComplementoRespostaDao.updateEntidade(tipoComplementoResposta);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public TipoComplementoResposta pesquisarPorId(int id)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}



}
