/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.jus.cnj.corporativo.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.jus.cnj.corporativo.basecrud.impl.GenericDaoImpl;
import br.jus.cnj.corporativo.bean.CorporativoPerfil;

@Repository
public class CorporativoPerfilDAO extends GenericDaoImpl<CorporativoPerfil> {

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CorporativoPerfil pesquisaPorId(CorporativoPerfil perfil) {
		List<CorporativoPerfil> lista = (List)getSession().createQuery(
				"select perfil from CorporativoPerfil perfil where perfil.id = "+ perfil.getId()).list();
				
		if (lista.size() == 1) {
			return lista.get(0);
		}
		return null;
	}

}