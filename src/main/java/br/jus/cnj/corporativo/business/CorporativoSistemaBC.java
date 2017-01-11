/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.jus.cnj.corporativo.business;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.cnj.corporativo.autenticacao.Credencial;
import br.jus.cnj.corporativo.bean.CorporativoSistema;
import br.jus.cnj.corporativo.persistence.CorporativoSistemaDAO;

@Service("corporativoSistemaBC")
public class CorporativoSistemaBC {

	String nomeUsuario;
	String nomeOrgao;

	@Autowired
	private CorporativoSistemaDAO corporativoSistemaDAO;

	@Transactional
	public CorporativoSistema pesquisaPorId(CorporativoSistema sistema){
		return corporativoSistemaDAO.pesquisaPorId(sistema);
	}
	
	public String getNomeSessao() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		Credencial credencial_session = (Credencial) session.getAttribute("credencial");
		try {
			nomeUsuario = credencial_session.getUsuario().getNomUsuario();
		} catch(Exception e) {
			nomeUsuario = "";
		}
		return WordUtils.capitalizeFully(nomeUsuario);
	}
	
	public String getOrgaoSessao() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		Credencial credencial_session = (Credencial) session.getAttribute("credencial");
		try {
			nomeOrgao = credencial_session.getUsuario().getOrgao();
		} catch(Exception e) {
			nomeOrgao = "";
		}
		return nomeOrgao;
	}
	
	public String doLogout() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.setAttribute("credencial", null);
		
		return "http://wwwh.cnj.jus.br/corporativo";
	}
}