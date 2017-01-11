/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.jus.cnj.corporativo.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.cnj.corporativo.bean.CorporativoEstado;
import br.jus.cnj.corporativo.persistence.CorporativoEstadoDAO;
import br.jus.cnj.utils.exception.DaoException;
import br.jus.cnj.utils.exception.ServiceException;

@Service("corporativoEstadoBC")
public class CorporativoEstadoBC {

	@Autowired
	private CorporativoEstadoDAO corporativoEstadoDAO;

	@Transactional
	public List<CorporativoEstado> getAll() throws ServiceException {
		try {
			return corporativoEstadoDAO.getAll();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	@Transactional
	public CorporativoEstado pesquisarPorUf(String uf) throws ServiceException {
		try {
			return corporativoEstadoDAO.buscarCorporativoEstadoPorUf(uf);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

}