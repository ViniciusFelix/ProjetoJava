package br.jus.cnj.saci.service.impl;

import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.jus.cnj.corporativo.autenticacao.Credencial;
import br.jus.cnj.saci.dao.BoasPraticasDAO;
import br.jus.cnj.saci.entity.BoasPraticas;
import br.jus.cnj.saci.entity.InspecaoOrgao;
import br.jus.cnj.saci.service.BoasPraticasService;
import br.jus.cnj.utils.exception.ServiceException;


@Service("boasPraticas")
public class BoasPraticasServiceImpl implements BoasPraticasService {


	@Autowired
	private BoasPraticasDAO boasPraticasDao;
	
	@Override
	public void persistirEntidade(BoasPraticas boasPraticas)
			throws ServiceException {
		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			Credencial credencial_session = (Credencial) session.getAttribute("credencial");
			int idUsuario = credencial_session.getUsuario().getSeqUsuario();
			boasPraticas.setUsuInclusao(idUsuario);
			boasPraticas.setDatInclusao(new Date(System.currentTimeMillis()));
			boasPraticasDao.persistirEntidade(boasPraticas);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		
	}

	@Override
	public void excluirEntidade(BoasPraticas boasPraticas)
			throws ServiceException {
		try {
			boasPraticasDao.excluirEntidade(boasPraticas);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		
	}

	@Override
	public List<BoasPraticas> getAll() throws ServiceException {
		try {
			return boasPraticasDao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public BoasPraticas pesquisarPorId(int id) throws ServiceException {
		try {
			return boasPraticasDao.pesquisarPorId(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void updateEntidade(BoasPraticas boasPraticas)
			throws ServiceException {
		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			Credencial credencial_session = (Credencial) session.getAttribute("credencial");
			int idUsuario = credencial_session.getUsuario().getSeqUsuario();
			boasPraticas.setUsuAlteracao(idUsuario);
			boasPraticas.setDatAlteracao(new Date(System.currentTimeMillis()));
			boasPraticasDao.updateEntidade(boasPraticas);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		
	}

	@Override
	public List<BoasPraticas> pesquisaPorInspecaoOrgao(
			InspecaoOrgao inspecaoOrgao) throws ServiceException {
		try {
			return boasPraticasDao.pesquisaPorInspecaoOrgao(inspecaoOrgao);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	
	
}
