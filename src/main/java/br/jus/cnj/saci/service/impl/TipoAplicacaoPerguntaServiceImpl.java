package br.jus.cnj.saci.service.impl;

import java.io.Serializable;
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
import br.jus.cnj.saci.dao.TipoAplicacaoPerguntaDAO;
import br.jus.cnj.saci.entity.Questionario;
import br.jus.cnj.saci.entity.TipoAplicacaoPergunta;
import br.jus.cnj.saci.entity.TipoObjetoAplicacao;
import br.jus.cnj.saci.service.TipoAplicacaoPerguntaService;
import br.jus.cnj.saci.service.TipoObjetoAplicacaoService;
import br.jus.cnj.utils.exception.ServiceException;

@Service("tipoAplicacaoPerguntaService")
public class TipoAplicacaoPerguntaServiceImpl implements TipoAplicacaoPerguntaService {

	@Autowired
	private TipoAplicacaoPerguntaDAO tipoAplicacaoPerguntaDao;	
		
	@Transactional
	public boolean persistirEntidade(TipoAplicacaoPergunta tipoAplicacaoPergunta) throws ServiceException {
		boolean descricaoExists = false;
		String descricao = tipoAplicacaoPergunta.getDscTipoAplicacao();
		try {
			descricaoExists = tipoAplicacaoPerguntaDao.descricaoExists(descricao, 0);			
			if (descricaoExists) {
				return false;
			} else {
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
				Credencial credencial_session = (Credencial) session.getAttribute("credencial");
				int idUsuario = credencial_session.getUsuario().getSeqUsuario();
				tipoAplicacaoPergunta.setUsuInclusao(idUsuario);
				tipoAplicacaoPergunta.setDatInclusao(new Date(System.currentTimeMillis()));
				
				tipoAplicacaoPerguntaDao.persistirEntidade(tipoAplicacaoPergunta);
								
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

	@Transactional
	public void excluirEntidade(TipoAplicacaoPergunta tipoAplicacaoPergunta) throws ServiceException {
		try {
			tipoAplicacaoPerguntaDao.excluirEntidade(tipoAplicacaoPergunta);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	public List<TipoAplicacaoPergunta> getAll() throws ServiceException {
		try {
			return tipoAplicacaoPerguntaDao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	public TipoAplicacaoPergunta pesquisarPorId(int id) throws ServiceException {
		try {
			return tipoAplicacaoPerguntaDao.pesquisarPorId(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	public boolean updateEntidade(TipoAplicacaoPergunta tipoAplicacaoPergunta) throws ServiceException {
		boolean descricaoExists = false;
		String descricao = tipoAplicacaoPergunta.getDscTipoAplicacao();
		int id = tipoAplicacaoPergunta.getId();
		try {
			descricaoExists = tipoAplicacaoPerguntaDao.descricaoExists(descricao, id);			
			if (descricaoExists) {
				return false;
			} else {
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
				Credencial credencial_session = (Credencial) session.getAttribute("credencial");
				int idUsuario = credencial_session.getUsuario().getSeqUsuario();
				tipoAplicacaoPergunta.setUsuAlteracao(idUsuario);
				tipoAplicacaoPergunta.setDatAlteracao(new Date(System.currentTimeMillis()));				
				tipoAplicacaoPerguntaDao.updateEntidade(tipoAplicacaoPergunta);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}
	
	@Transactional
	public List<TipoObjetoAplicacao> listaTipoObjetoAplicacao(List<TipoAplicacaoPergunta> tipoAplicacaoPerguntaList) throws ServiceException {
		try {
			return tipoAplicacaoPerguntaDao.listaTipoObjetoAplicacao(tipoAplicacaoPerguntaList);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

}
