/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.jus.cnj.corporativo.persistence;

import org.springframework.stereotype.Repository;

import br.jus.cnj.corporativo.basecrud.impl.GenericDaoImpl;
import br.jus.cnj.corporativo.bean.CorporativoEstado;
import br.jus.cnj.utils.exception.DaoException;

@Repository
public class CorporativoEstadoDAO extends GenericDaoImpl<CorporativoEstado> {

	public CorporativoEstado buscarCorporativoEstadoPorUf(String uf) throws DaoException {
		
		String hql = "select ce from CorporativoEstado ce where ce.uf='"+uf+"'";
		CorporativoEstado corporativoEstado = (CorporativoEstado)getSession().createQuery(hql).uniqueResult();
		
		return corporativoEstado;
	}

}