package br.jus.cnj.saci.service.impl;

import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.cnj.corporativo.autenticacao.Credencial;
import br.jus.cnj.saci.dao.PerguntaDAO;
import br.jus.cnj.saci.entity.ModeloPergunta;
import br.jus.cnj.saci.entity.Pergunta;
import br.jus.cnj.saci.entity.Questionario;
import br.jus.cnj.saci.entity.TipoAplicacaoPergunta;
import br.jus.cnj.saci.service.PerguntaService;
import br.jus.cnj.utils.exception.DaoException;
import br.jus.cnj.utils.exception.ServiceException;

@Service("perguntaService")
public class PerguntaServiceImpl implements PerguntaService {

	@Autowired
	private PerguntaDAO perguntaDao;
	
	@Transactional
	public void persistirEntidade(Pergunta pergunta) throws ServiceException {
		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			Credencial credencial_session = (Credencial) session.getAttribute("credencial");
			int idUsuario = credencial_session.getUsuario().getSeqUsuario();
			pergunta.setNumOrdem(perguntaDao.pesquisarUltimoNumOrdem(pergunta));
			pergunta.setUsuInclusao(idUsuario);
			pergunta.setDatInclusao(new Date(System.currentTimeMillis()));
			perguntaDao.persistirEntidade(pergunta);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	public int pesquisarUltimoNumOrdem(Pergunta pergunta) throws ServiceException {
		int numOrdem = 1;
		try {
			numOrdem = perguntaDao.pesquisarUltimoNumOrdem(pergunta);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return numOrdem;
	}

	
	@Transactional
	public Pergunta pesquisarPorNumOrdem(int numOrdem, TipoAplicacaoPergunta tipoAplicacaoPergunta, Questionario questionario) throws ServiceException {
		try {
			return perguntaDao.pesquisarPorNumOrdem(numOrdem, tipoAplicacaoPergunta, questionario);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	@Transactional
	public void excluirEntidade(Pergunta pergunta) throws ServiceException {
		try {
			perguntaDao.excluirEntidade(pergunta);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	public List<Pergunta> getAll() throws ServiceException {
		try {
			return perguntaDao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	public Pergunta pesquisarPorId(int id) throws ServiceException {
		try {
			return perguntaDao.pesquisarPorId(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	public void updateEntidade(Pergunta pergunta) throws ServiceException {
		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			Credencial credencial_session = (Credencial) session.getAttribute("credencial");
			int idUsuario = credencial_session.getUsuario().getSeqUsuario();
			pergunta.setUsuAlteracao(idUsuario);
			pergunta.setDatAlteracao(new Date(System.currentTimeMillis()));
			perguntaDao.updateEntidade(pergunta);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}
	
	@Transactional
	public List<Pergunta> pesquisarPorQuestionario(Questionario questionario) throws ServiceException {
		try {
			return perguntaDao.pesquisarPorQuestionario(questionario);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	@Transactional
	public void mudarOrdem(int ini, int fim, TipoAplicacaoPergunta tap, Questionario questionario) throws ServiceException {
		try {
			int aux = fim;
			if (ini > fim) {
				aux = ini;
			}
			Pergunta p = perguntaDao.pesquisarPorNumOrdem(ini, tap, questionario);
			perguntaDao.mudarOrdem(ini, fim, tap, questionario, aux);
			p.setNumOrdem(fim);
			perguntaDao.updateEntidade(p);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

}
