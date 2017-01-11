package br.jus.cnj.saci.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.cnj.corporativo.bean.CorporativoOrgao;
import br.jus.cnj.corregedoria.entity.Cartorio;
import br.jus.cnj.saci.dao.InspecaoOrgaoDAO;
import br.jus.cnj.saci.entity.Inspecao;
import br.jus.cnj.saci.entity.InspecaoOrgao;
import br.jus.cnj.saci.entity.Questionario;
import br.jus.cnj.saci.service.InspecaoOrgaoService;
import br.jus.cnj.utils.exception.ServiceException;

@Service("inspecaoOrgaoService")
public class InspecaoOrgaoServiceImpl implements InspecaoOrgaoService {

	@Autowired
	private InspecaoOrgaoDAO inspecaoOrgaoDao;
	
	@Transactional
	public List<CorporativoOrgao> pesquisarUnidadePorQuestionario(Questionario questionario) throws ServiceException {
		try {
			return inspecaoOrgaoDao.pesquisarUnidadePorQuestionario(questionario);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	@Transactional
	public List<CorporativoOrgao> pesquisarUnidadePorInspecao(Inspecao inspecao) throws ServiceException {
		try {
			return inspecaoOrgaoDao.pesquisarUnidadePorInspecao(inspecao);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}


	@Transactional
	public InspecaoOrgao pesquisarPorOrgao(CorporativoOrgao orgao, Questionario questionario) throws ServiceException {
		try {
			return inspecaoOrgaoDao.pesquisarPorOrgao(orgao, questionario);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}


	@Transactional
	public List<CorporativoOrgao> getOrgaosArvore(Inspecao inspecao) throws ServiceException {
		try {
			return inspecaoOrgaoDao.getOrgaosArvore(inspecao);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}


	@Transactional
	public InspecaoOrgao pesquisarPorCartorio(Cartorio cartorio, Questionario questionario) throws ServiceException {
		try {
			return inspecaoOrgaoDao.pesquisarPorCartorio(cartorio, questionario);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}


	@Transactional
	public List<Cartorio> pesquisarCartorioPorQuestionario(Questionario questionario) throws ServiceException {
		try {
			return inspecaoOrgaoDao.pesquisarCartorioPorQuestionario(questionario);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}


	@Transactional
	public List<Cartorio> pesquisarCartorioPorInspecao(Inspecao inspecao) throws ServiceException {
		try {
			return inspecaoOrgaoDao.pesquisarCartorioPorInspecao(inspecao);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}




	@Transactional
	public InspecaoOrgao pesquisarPorId(int id) throws ServiceException {
		try {
			return inspecaoOrgaoDao.pesquisarPorId(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

}
