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
import br.jus.cnj.saci.dao.QuestionarioDAO;
import br.jus.cnj.saci.entity.Questionario;
import br.jus.cnj.saci.entity.TipoAplicacaoPergunta;
import br.jus.cnj.saci.service.QuestionarioService;
import br.jus.cnj.utils.exception.ServiceException;

@Service("questionarioService")
public class QuestionarioServiceImpl implements QuestionarioService {
	@Autowired
	private QuestionarioDAO questionarioDao;

	
	@Transactional
	public void persistirEntidade(Questionario questionario) throws ServiceException {
		try {
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
				Credencial credencial_session = (Credencial) session.getAttribute("credencial");
				questionario.setFlgAtivo(1);
				int idUsuario = credencial_session.getUsuario().getSeqUsuario();
				questionario.setUsuInclusao(idUsuario);
				questionario.setDatInclusao(new Date(System.currentTimeMillis()));
				questionarioDao.persistirEntidade(questionario);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	public void excluirEntidade(Questionario questionario) throws ServiceException {
		try {
			questionarioDao.excluirEntidade(questionario);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	public List<Questionario> getAll() throws ServiceException {
		try {
			return questionarioDao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	public Questionario pesquisarPorId(int id) throws ServiceException {
		try {
			return questionarioDao.pesquisarPorId(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	public boolean updateEntidade(Questionario questionario) throws ServiceException {
		boolean descricaoExists = false;
		String descricao = questionario.getNomQuestionario();
		int id = questionario.getId();
		try {
			descricaoExists = questionarioDao.descricaoExists(descricao, id);			
			if (descricaoExists) {
				return false;
			} else {
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
				Credencial credencial_session = (Credencial) session.getAttribute("credencial");
				int idUsuario = credencial_session.getUsuario().getSeqUsuario();
				questionario.setUsuAlteracao(idUsuario);
				questionario.setDatAlteracao(new Date(System.currentTimeMillis()));
				questionarioDao.updateEntidade(questionario);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}
	
	@Transactional
	public Collection<Questionario> convertIdListToObjectList(Collection<String> questionario) throws ServiceException {
		Collection<Questionario> listQuestionario = new ArrayList<Questionario>();
		try {
			for (String idQuestionario : questionario) {
				listQuestionario.add(pesquisarPorId(Integer.parseInt(idQuestionario)));
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
			
		return listQuestionario;
	}
	
	@Transactional
	public List<TipoAplicacaoPergunta> listaTipoAplicacaoPergunta(Questionario questionario) throws ServiceException {
		try {
			return questionarioDao.listaTipoAplicacaoPergunta(questionario);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
}
