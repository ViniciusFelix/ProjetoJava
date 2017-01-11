/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.jus.cnj.corporativo.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.jus.cnj.corporativo.basecrud.impl.GenericDaoImpl;
import br.jus.cnj.corporativo.bean.CorporativoSistema;

@Repository
public class CorporativoSistemaDAO extends GenericDaoImpl<CorporativoSistema> {
	
	@SuppressWarnings("unchecked")
	public CorporativoSistema pesquisaPorId(CorporativoSistema sistema) {
		List<CorporativoSistema> lista = (List<CorporativoSistema>) getSession().createQuery("select sistema from CorporativoSistema sistema where sistema.id = " + sistema.getId()).list();
				
		if (lista.size() == 1) {
			return lista.get(0);
		}
		return null;
	}

}