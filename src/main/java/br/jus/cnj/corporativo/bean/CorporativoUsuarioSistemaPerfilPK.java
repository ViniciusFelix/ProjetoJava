/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.jus.cnj.corporativo.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 
 * @author fabio.pereira
 */
@Embeddable
public class CorporativoUsuarioSistemaPerfilPK implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1612746920846611069L;

	@Column(name = "SEQ_USUARIO")
	private int idUsuario;

	@Column(name = "SEQ_SISTEMA")
	private int idSistema;

	@Column(name = "SEQ_PERFIL")
	private int idPerfil;

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdSistema() {
		return idSistema;
	}

	public void setIdSistema(int idSistema) {
		this.idSistema = idSistema;
	}

	public int getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(int idPerfil) {
		this.idPerfil = idPerfil;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idPerfil;
		result = prime * result + idSistema;
		result = prime * result + idUsuario;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CorporativoUsuarioSistemaPerfilPK other = (CorporativoUsuarioSistemaPerfilPK) obj;
		if (idPerfil != other.idPerfil)
			return false;
		if (idSistema != other.idSistema)
			return false;
		if (idUsuario != other.idUsuario)
			return false;
		return true;
	}

}
