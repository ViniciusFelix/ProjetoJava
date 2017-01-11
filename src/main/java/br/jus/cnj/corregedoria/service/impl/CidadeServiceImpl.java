package br.jus.cnj.corregedoria.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.cnj.corporativo.entity.Cidade;
import br.jus.cnj.corregedoria.dao.CidadeDAO;
import br.jus.cnj.corregedoria.service.CidadeService;
import br.jus.cnj.utils.exception.DaoException;
import br.jus.cnj.utils.exception.ServiceException;
@Service("cidadeService")
public class CidadeServiceImpl implements CidadeService {
	@Autowired
	private CidadeDAO cidadeDao;

	@Transactional
	public List<Cidade> getAll() throws ServiceException {
		try {
			return cidadeDao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	public List<Cidade> pesquisarPorUf(String uf) throws ServiceException {
		try {
			return cidadeDao.pesquisarPorUf(uf);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	public Cidade pesquisarPorId(int id) throws ServiceException {
		try {
			return cidadeDao.pesquisarPorId(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

}
