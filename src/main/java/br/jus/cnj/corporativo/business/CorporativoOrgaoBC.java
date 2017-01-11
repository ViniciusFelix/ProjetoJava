/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.jus.cnj.corporativo.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.jus.cnj.corporativo.bean.CorporativoOrgao;
import br.jus.cnj.corporativo.bean.CorporativoTipoOrgao;
import br.jus.cnj.corporativo.persistence.CorporativoOrgaoDAO;
import br.jus.cnj.utils.exception.DaoException;
import br.jus.cnj.utils.exception.ServiceException;

@Service("corporativoOrgaoBC")
public class CorporativoOrgaoBC {

	@Autowired
	private CorporativoOrgaoDAO corporativoOrgaoDAO;

	public CorporativoOrgao pesquisaPorId(CorporativoOrgao orgao) {
		return corporativoOrgaoDAO.pesquisaPorId(orgao);
	}
	
	public CorporativoOrgao pesquisaPorId(int id) throws ServiceException {
		try {
			return corporativoOrgaoDAO.pesquisarPorId(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	public List<CorporativoOrgao> pesquisaPorEsfera(CorporativoTipoOrgao tipoOrgao) throws ServiceException {
		return corporativoOrgaoDAO.pesquisaPorTipoOrgao(tipoOrgao);
	}
	
	public List<CorporativoOrgao> pesquisaPorOrgaoPai(CorporativoOrgao orgao) throws ServiceException {
		return corporativoOrgaoDAO.pesquisaPorOrgaoPai(orgao);
	}
	
	public List<CorporativoOrgao> pesquisaSegundoNivel(CorporativoOrgao orgao, CorporativoTipoOrgao esfera) throws ServiceException {
		List<CorporativoOrgao> listCorporativoOrgao = new ArrayList<CorporativoOrgao>();
		if (esfera.getId().equals("TRIBE")) {
			listCorporativoOrgao = corporativoOrgaoDAO.pesquisaSegundoNivelPulaUm(orgao);
		} else if (esfera.getId().equals("TRIBF")) {
			listCorporativoOrgao = corporativoOrgaoDAO.pesquisaSegundoNivelPulaUm(orgao);
		} else if (esfera.getId().equals("TRIBL")) {
			listCorporativoOrgao = corporativoOrgaoDAO.pesquisaPorOrgaoPai(orgao);
		} else if (esfera.getId().equals("TRIBM")) {
			listCorporativoOrgao = corporativoOrgaoDAO.pesquisaSegundoNivelPulaUm(orgao);
		} else if (esfera.getId().equals("TRIBT")) {
			listCorporativoOrgao = corporativoOrgaoDAO.pesquisaPorOrgaoPai(orgao);
		}
		
		return listCorporativoOrgao;
	}
}