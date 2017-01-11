package br.jus.cnj.saci.service.impl;

import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.cnj.corporativo.autenticacao.Credencial;
import br.jus.cnj.saci.dao.TipoDeliberacaoDAO;
import br.jus.cnj.saci.entity.TipoDeliberacao;
import br.jus.cnj.saci.entity.TipoDocumento;
import br.jus.cnj.saci.service.TipoDeliberacaoService;
import br.jus.cnj.utils.exception.ServiceException;

@Service("tipoDeliberacaoService")
public class TipoDeliberacaoServiceImpl implements TipoDeliberacaoService {
	
	@Autowired
	private TipoDeliberacaoDAO tipoDeliberacaoDao;

	@Transactional
	public List<TipoDeliberacao> getAll() throws ServiceException {
		try {
			return tipoDeliberacaoDao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	@Transactional
	public boolean persistirEntidade(TipoDeliberacao tipoDeliberacao) throws ServiceException {
		boolean descricaoExists = false;
		String descricao = tipoDeliberacao.getDscTipoDeliberacao();
		try {
			descricaoExists = tipoDeliberacaoDao.descricaoExists(descricao);			
			if (descricaoExists) {
				return false;
			} else{
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
				tipoDeliberacaoDao.persistirEntidade(tipoDeliberacao);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	public void excluirEntidade(TipoDeliberacao tipoDeliberacao) throws ServiceException {
		try {
			tipoDeliberacaoDao.excluirEntidade(tipoDeliberacao);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	public boolean updateEntidade(TipoDeliberacao tipoDeliberacao) throws ServiceException {
		String descricao = tipoDeliberacao.getDscTipoDeliberacao();
		boolean descricaoExists = false;
		try {
			descricaoExists = tipoDeliberacaoDao.descricaoExists(descricao);			
			if (descricaoExists) {
				return false;
			} else{
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
				Credencial credencial_session = (Credencial) session.getAttribute("credencial");
				tipoDeliberacaoDao.updateEntidade(tipoDeliberacao);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}
}
