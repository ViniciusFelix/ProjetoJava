package br.jus.cnj.corporativo.persistence;

import java.util.List;

import br.jus.cnj.corporativo.basecrud.impl.GenericDaoImpl;
import br.jus.cnj.corporativo.bean.CorporativoUsuario;

public class CorporativoUsuarioDAO extends GenericDaoImpl<CorporativoUsuario> {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CorporativoUsuario pesquisaPorId(CorporativoUsuario usuario) {
		List<CorporativoUsuario> lista = (List) getSession().createQuery(
				"select usuario from CorporativoUsuario usuario where usuario.id = "
						+ usuario.getId()).list();
		if (lista.size() == 1) {
			return lista.get(0);
		}
		return null;
	}
}