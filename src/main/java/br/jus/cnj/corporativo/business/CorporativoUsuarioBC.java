package br.jus.cnj.corporativo.business;

import org.springframework.beans.factory.annotation.Autowired;

import br.jus.cnj.corporativo.bean.CorporativoUsuario;
import br.jus.cnj.corporativo.persistence.CorporativoUsuarioDAO;

public class CorporativoUsuarioBC {

	@Autowired
	private CorporativoUsuarioDAO corporativoUsuarioDAO;
	
	public CorporativoUsuario pesquisaPorId(CorporativoUsuario usuario){
		return corporativoUsuarioDAO.pesquisaPorId(usuario);
	}
}