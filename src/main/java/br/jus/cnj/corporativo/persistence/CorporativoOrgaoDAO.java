/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.jus.cnj.corporativo.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.jus.cnj.corporativo.basecrud.impl.GenericDaoImpl;
import br.jus.cnj.corporativo.bean.CorporativoOrgao;
import br.jus.cnj.corporativo.bean.CorporativoTipoOrgao;

@Repository
public class CorporativoOrgaoDAO extends GenericDaoImpl<CorporativoOrgao> {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CorporativoOrgao pesquisaPorId(CorporativoOrgao orgao) {
		List<CorporativoOrgao> lista = (List)getSession().createQuery(
				"select orgao from CorporativoOrgao orgao where orgao.id = "+ orgao.getId()).list();
				
		if (lista.size() == 1) {
			return lista.get(0);
		}
		return null;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<CorporativoOrgao> pesquisaPorTipoOrgao(CorporativoTipoOrgao tipoOrgao) {
		List<CorporativoOrgao> lista = (List)getSession().createQuery(
				"select orgao from CorporativoOrgao orgao where orgao.tipoOrgao = '"+ tipoOrgao.getId()+"'").list();
				
		return lista;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<CorporativoOrgao> pesquisaPorOrgaoPai(CorporativoOrgao orgao) {
		List<CorporativoOrgao> lista = (List)getSession().createQuery(
				"select orgao from CorporativoOrgao orgao where orgao.orgao = '"+ orgao.getId()+"' order by orgao.ordemOrgao, orgao.descrica").list();
				
		return lista;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<CorporativoOrgao> pesquisaSegundoNivelPulaUm(CorporativoOrgao orgao) {
		List<CorporativoOrgao> lista = (List)getSession().createQuery(
				"select orgao from CorporativoOrgao orgao where orgao.orgao = (select min(orgao.id) from CorporativoOrgao orgao where orgao.orgao = '"+ orgao.getId()+"') order by orgao.ordemOrgao, orgao.descrica").list();
				
		return lista;
	}

}