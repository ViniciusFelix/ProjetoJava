/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.jus.cnj.corporativo.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author fabiotavarespr
 */
@Entity
@Table(name = "uf", schema = "corporativo")
public class CorporativoEstado implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6706194190697344932L;

	@Id
	@Column(name = "sig_uf")
	private String uf;

	@Column(name = "dsc_uf")
	private String nome;

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
