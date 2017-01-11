package br.jus.cnj.corregedoria.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.cnj.corporativo.entity.Cidade;
import br.jus.cnj.corregedoria.dao.CartorioDAO;
import br.jus.cnj.corregedoria.entity.Cartorio;
import br.jus.cnj.corregedoria.service.CartorioService;
import br.jus.cnj.utils.exception.ServiceException;
@Service("cartorioService")
public class CartorioServiceImpl implements CartorioService {
	@Autowired
	private CartorioDAO cartorioDao;

	@Transactional
	public List<Cartorio> getAll() throws ServiceException {
		try {
			return cartorioDao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	public List<Cartorio> pesquisarPorCidade(Cidade b) throws ServiceException {
		try {
			return cartorioDao.pesquisarPorCidade(b);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	@Transactional
	public Cartorio pesquisarPorId(int id) throws ServiceException {
		try {
			return cartorioDao.pesquisarPorId(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

}
