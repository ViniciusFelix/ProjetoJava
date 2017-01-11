/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.jus.cnj.corporativo.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author fabio.pereira
 */
@Entity
@Table(name = "usuario_sistema_perfil", schema = "corporativo")
public class CorporativoUsuarioSistemaPerfil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6885650041925388204L;

	@EmbeddedId
	protected CorporativoUsuarioSistemaPerfilPK idPK;

	@Column(name = "SEQ_ORGAO")
	private int orgao;

	@JoinColumn(name = "SEQ_PERFIL", referencedColumnName = "SEQ_PERFIL", insertable = false, updatable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private CorporativoPerfil corporativoPerfil;

	@JoinColumn(name = "SEQ_SISTEMA", referencedColumnName = "SEQ_SISTEMA", insertable = false, updatable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private CorporativoSistema corporativoSistema;

	@JoinColumn(name = "SEQ_USUARIO", referencedColumnName = "SEQ_USUARIO", insertable = false, updatable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private CorporativoUsuario corporativoUsuario;

	public CorporativoUsuarioSistemaPerfilPK getIdPK() {
		return idPK;
	}

	public void setIdPK(CorporativoUsuarioSistemaPerfilPK idPK) {
		this.idPK = idPK;
	}

	public int getOrgao() {
		return orgao;
	}

	public void setOrgao(int orgao) {
		this.orgao = orgao;
	}

	public CorporativoPerfil getCorporativoPerfil() {
		return corporativoPerfil;
	}

	public void setCorporativoPerfil(CorporativoPerfil corporativoPerfil) {
		this.corporativoPerfil = corporativoPerfil;
	}

	public CorporativoSistema getCorporativoSistema() {
		return corporativoSistema;
	}

	public void setCorporativoSistema(CorporativoSistema corporativoSistema) {
		this.corporativoSistema = corporativoSistema;
	}

	public CorporativoUsuario getCorporativoUsuario() {
		return corporativoUsuario;
	}

	public void setCorporativoUsuario(CorporativoUsuario corporativoUsuario) {
		this.corporativoUsuario = corporativoUsuario;
	}

}
