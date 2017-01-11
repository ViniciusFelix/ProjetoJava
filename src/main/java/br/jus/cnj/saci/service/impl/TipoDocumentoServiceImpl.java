package br.jus.cnj.saci.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.cnj.corporativo.autenticacao.Credencial;
import br.jus.cnj.saci.dao.TipoDocumentoDAO;
import br.jus.cnj.saci.entity.TipoDocumento;
import br.jus.cnj.saci.service.TipoDocumentoService;
import br.jus.cnj.utils.exception.ServiceException;

@Service("tipoDocumentoService")
public class TipoDocumentoServiceImpl implements TipoDocumentoService {
	
	@Autowired
	private TipoDocumentoDAO tipoDocumentoDao;

	@Transactional
	public List<TipoDocumento> getAll() throws ServiceException {
		try {
			return tipoDocumentoDao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	@Transactional
	public boolean persistirEntidade(TipoDocumento tipoDocumento) throws ServiceException {
		boolean descricaoExists = false;
		String descricao = tipoDocumento.getDscTipoDocumento();
		try {
			descricaoExists = tipoDocumentoDao.descricaoExists(descricao, 0);			
			if (descricaoExists) {
				return false;
			} else{
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
				tipoDocumentoDao.persistirEntidade(tipoDocumento);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	public void excluirEntidade(TipoDocumento tipoDocumento) throws ServiceException {
		try {
			tipoDocumentoDao.excluirEntidade(tipoDocumento);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	public boolean updateEntidade(TipoDocumento tipoDocumento) throws ServiceException {
		String descricao = tipoDocumento.getDscTipoDocumento();
		int id = tipoDocumento.getId();
		boolean descricaoExists = false;
		try {
			descricaoExists = tipoDocumentoDao.descricaoExists(descricao, id);			
			if (descricaoExists) {
				return false;
			} else{
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
				Credencial credencial_session = (Credencial) session.getAttribute("credencial");
				tipoDocumentoDao.updateEntidade(tipoDocumento);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}
}
