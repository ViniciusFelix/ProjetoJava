package br.jus.cnj.saci.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.cnj.corporativo.autenticacao.Credencial;
import br.jus.cnj.saci.dao.InspecaoDAO;
import br.jus.cnj.saci.entity.Deliberacao;
import br.jus.cnj.saci.entity.Inspecao;
import br.jus.cnj.saci.service.InspecaoService;
import br.jus.cnj.utils.exception.DaoException;
import br.jus.cnj.utils.exception.ServiceException;
@Service("inspecaoService")
public class InspecaoServiceImpl implements InspecaoService {
	@Autowired
	private InspecaoDAO inspecaoDao;

	@Transactional
	public boolean persistirEntidade(Inspecao inspecao) throws ServiceException {
		if (inspecao.getDatInicioInspecao().after(inspecao.getDatFinalInspecao())) {
			return false;
		}
		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			Credencial credencial_session = (Credencial) session.getAttribute("credencial");
			int idUsuario = credencial_session.getUsuario().getSeqUsuario();
			
			inspecao.setNumInspecao(geraNumInspecao(inspecao));
			//inspecao.setNumAnoInspecao(Calendar.getInstance().get(Calendar.YEAR));
			Calendar cal = Calendar.getInstance();
		    cal.setTime(inspecao.getDatInicioInspecao());
			inspecao.setNumAnoInspecao(cal.get(Calendar.YEAR));
			
			inspecao.setUsuInclusao(idUsuario);
			inspecao.setDatInclusao(new Date(System.currentTimeMillis()));
			inspecaoDao.persistirEntidade(inspecao);
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	public void excluirEntidade(Inspecao inspecao) throws ServiceException {
		try {
			inspecaoDao.excluirEntidade(inspecao);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	public List<Inspecao> getAll() throws ServiceException {
		try {
			return inspecaoDao.getAllOrdered();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}


	@Transactional
	public List<Inspecao> getAllJusticaEstadual(int tj) throws ServiceException {
		try {
			return inspecaoDao.getAllJusticaEstadual(tj);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	public Inspecao pesquisarPorId(int id) throws ServiceException {
		try {
			return inspecaoDao.pesquisarPorId(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	public boolean updateEntidade(Inspecao inspecao) throws ServiceException {
		if (inspecao.getDatInicioInspecao().after(inspecao.getDatFinalInspecao())) {
			return false;
		}
		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			Credencial credencial_session = (Credencial) session.getAttribute("credencial");
			int idUsuario = credencial_session.getUsuario().getSeqUsuario();
			inspecao.setUsuAlteracao(idUsuario);
			inspecao.setDatAlteracao(new Date(System.currentTimeMillis()));
			inspecaoDao.updateEntidade(inspecao);
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}
	
	@Transactional
	public int geraNumInspecao(Inspecao inspecao) throws ServiceException {
		int numInspecao = 0;
		try {
			numInspecao = inspecaoDao.pesquisarUltimoNumInspecao(inspecao);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return numInspecao;
	}

	@Transactional
	public boolean verificaDeliberacaoParaInspecao(Inspecao inspecao) throws ServiceException {
		boolean del;
		del = inspecaoDao.verificaDeliberacaoParaInspecao(inspecao);
		return del;
	}
	
	
	@Transactional
	public List<Deliberacao> decisaoPorInspecao(Inspecao inspecao) throws ServiceException {
		try {
			return inspecaoDao.decisaoPorInspecao(inspecao);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	
}
