package br.jus.cnj.saci.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.cnj.corporativo.autenticacao.Credencial;
import br.jus.cnj.saci.dao.TipoObjetoAplicacaoDAO;
import br.jus.cnj.saci.entity.TipoObjetoAplicacao;
import br.jus.cnj.saci.service.TipoObjetoAplicacaoService;
import br.jus.cnj.utils.exception.ServiceException;

@Service("tipoObjetoAplicacaoService")
public class TipoObjetoAplicacaoServiceImpl implements TipoObjetoAplicacaoService {
	@Autowired
	private TipoObjetoAplicacaoDAO tipoObjetoAplicacaoDao;

	@Transactional
	public boolean persistirEntidade(TipoObjetoAplicacao tipoObjetoAplicacao) throws ServiceException {
		boolean descricaoExists = false;
		String descricao = tipoObjetoAplicacao.getDscTipoObjetoAplicacao();
		try {
			descricaoExists = tipoObjetoAplicacaoDao.descricaoExists(descricao, 0);			
			if (descricaoExists) {
				return false;
			} else {
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
				Credencial credencial_session = (Credencial) session.getAttribute("credencial");
				tipoObjetoAplicacao.setFlagAtivo('S');
	//			int idUsuario = credencial_session.getUsuario().getSeqUsuario();
	//			tipoObjetoAplicacao.setUsuInclusao(idUsuario);
	//			tipoObjetoAplicacao.setDatInclusao(new Date(System.currentTimeMillis()));
				tipoObjetoAplicacaoDao.persistirEntidade(tipoObjetoAplicacao);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

	@Transactional
	public void excluirEntidade(TipoObjetoAplicacao tipoObjetoAplicacao) throws ServiceException {
		try {
			tipoObjetoAplicacaoDao.excluirEntidade(tipoObjetoAplicacao);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	public List<TipoObjetoAplicacao> getAll() throws ServiceException {
		try {
			return tipoObjetoAplicacaoDao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	public TipoObjetoAplicacao pesquisarPorId(int id) throws ServiceException {
		try {
			return tipoObjetoAplicacaoDao.pesquisarPorId(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	public boolean updateEntidade(TipoObjetoAplicacao tipoObjetoAplicacao) throws ServiceException {
		boolean descricaoExists = false;
		String descricao = tipoObjetoAplicacao.getDscTipoObjetoAplicacao();
		int id = tipoObjetoAplicacao.getId();
		try {
			descricaoExists = tipoObjetoAplicacaoDao.descricaoExists(descricao, id);			
			if (descricaoExists) {
				return false;
			} else {
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
				Credencial credencial_session = (Credencial) session.getAttribute("credencial");
	//			int idUsuario = credencial_session.getUsuario().getSeqUsuario();
	//			tipoObjetoAplicacao.setUsuAlteracao(idUsuario);
	//			tipoObjetoAplicacao.setDatAlteracao(new Date(System.currentTimeMillis()));
				tipoObjetoAplicacaoDao.updateEntidade(tipoObjetoAplicacao);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}
	
	@Transactional
	public Collection<TipoObjetoAplicacao> convertIdListToObjectList(Collection<String> tipoObjetoAplicacao) throws ServiceException {
		Collection<TipoObjetoAplicacao> listTipoObjetoAplicacao = new ArrayList<TipoObjetoAplicacao>();
		try {
			for (String idTipoObjetoAplicacao : tipoObjetoAplicacao) {
				listTipoObjetoAplicacao.add(pesquisarPorId(Integer.parseInt(idTipoObjetoAplicacao)));
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
			
		return listTipoObjetoAplicacao;
	}
}
