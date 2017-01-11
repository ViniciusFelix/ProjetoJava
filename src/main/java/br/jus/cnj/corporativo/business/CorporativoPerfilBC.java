/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.jus.cnj.corporativo.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.jus.cnj.corporativo.bean.CorporativoPerfil;
import br.jus.cnj.corporativo.persistence.CorporativoPerfilDAO;

@Service("corporativoPerfilBC")
public class CorporativoPerfilBC {

	@Autowired
	private CorporativoPerfilDAO corporativoPerfilDAO;

	public CorporativoPerfil pesquisaPorId(CorporativoPerfil perfil) {
		return corporativoPerfilDAO.pesquisaPorId(perfil);
	}

}