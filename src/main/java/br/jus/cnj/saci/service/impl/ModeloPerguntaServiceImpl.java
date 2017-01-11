package br.jus.cnj.saci.service.impl;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.cnj.corporativo.autenticacao.Credencial;
import br.jus.cnj.saci.dao.ModeloPerguntaDAO;
import br.jus.cnj.saci.entity.ModeloPergunta;
import br.jus.cnj.saci.entity.Pergunta;
import br.jus.cnj.saci.entity.Questionario;
import br.jus.cnj.saci.entity.TipoAplicacaoPergunta;
import br.jus.cnj.saci.service.ModeloPerguntaService;
import br.jus.cnj.saci.service.TipoAplicacaoPerguntaService;
import br.jus.cnj.utils.exception.ServiceException;

@Service("modeloPerguntaService")
public class ModeloPerguntaServiceImpl implements ModeloPerguntaService {

	@Autowired
	private ModeloPerguntaDAO modeloPerguntaDao;
	
	@Transactional
	public void persistirEntidade(ModeloPergunta modeloPergunta) throws ServiceException {
		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			Credencial credencial_session = (Credencial) session.getAttribute("credencial");
			int idUsuario = credencial_session.getUsuario().getSeqUsuario();
			modeloPergunta.setNumOrdem(modeloPerguntaDao.pesquisarUltimoNumOrdem(modeloPergunta));
			modeloPergunta.setUsuInclusao(idUsuario);
			modeloPergunta.setDatInclusao(new Date(System.currentTimeMillis()));
			modeloPerguntaDao.persistirEntidade(modeloPergunta);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	public void excluirEntidade(ModeloPergunta modeloPergunta) throws ServiceException {
		try {
			modeloPerguntaDao.excluirEntidade(modeloPergunta);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	public List<ModeloPergunta> getAll() throws ServiceException {
		try {
			return modeloPerguntaDao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	public ModeloPergunta pesquisarPorId(int id) throws ServiceException {
		try {
			return modeloPerguntaDao.pesquisarPorId(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	@Transactional
	public ModeloPergunta pesquisarPorNumOrdem(int numOrdem, TipoAplicacaoPergunta tipoAplicacaoPergunta) throws ServiceException {
		try {
			return modeloPerguntaDao.pesquisarPorNumOrdem(numOrdem, tipoAplicacaoPergunta);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	@Transactional
	public List<ModeloPergunta> pesquisarPorTipoAplicacaoPergunta(TipoAplicacaoPergunta tipoAplicacaoPergunta) throws ServiceException {
		try {			
			return modeloPerguntaDao.pesquisarPorTipoAplicacaoPergunta(tipoAplicacaoPergunta);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	public void updateEntidade(ModeloPergunta modeloPergunta) throws ServiceException {
		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			Credencial credencial_session = (Credencial) session.getAttribute("credencial");
			int idUsuario = credencial_session.getUsuario().getSeqUsuario();
			modeloPergunta.setUsuAlteracao(idUsuario);
			modeloPergunta.setDatAlteracao(new Date(System.currentTimeMillis()));
			modeloPerguntaDao.updateEntidade(modeloPergunta);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}
	
	@Transactional
	public void mudarOrdem(int ini, int fim, TipoAplicacaoPergunta tap) throws ServiceException {
		try {
			int aux = fim;
			if (ini > fim) {
				aux = ini;
			}
			ModeloPergunta p = modeloPerguntaDao.pesquisarPorNumOrdem(ini, tap);
			modeloPerguntaDao.mudarOrdem(ini, fim, tap, aux);
			p.setNumOrdem(fim);
			modeloPerguntaDao.updateEntidade(p);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

}
