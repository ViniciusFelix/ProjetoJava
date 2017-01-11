/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.jus.cnj.corporativo.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author fabio.pereira
 */
@Entity
@Table(name = "perfil", schema = "corporativo")
public class CorporativoPerfil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4432046226894542031L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SEQ_PERFIL")
	private Integer id;

	@Column(name = "NOM_PERFIL")
	private String nome;

	@Column(name = "DSC_PERFIL")
	private String descricao;

	@Column(name = "FLG_ATIVO")
	private char isAtivo;

	@Column(name = "FLG_VISIBILIDADE_PUBLICA")
	private char isVisibilidadePublica;

	@Column(name = "USU_INCLUSAO")
	private String usuarioInclusao;

	@Column(name = "DAT_INCLUSAO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInclusao;

	@Column(name = "DSC_IP_USU_INCLUSAO")
	private String ipUsuInclusao;

	@Column(name = "USU_ALTERACAO")
	private Integer usuarioAlteracao;

	@Column(name = "DAT_ALTERACAO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAlteracao;

	@Column(name = "DSC_IP_USU_ALTERACAO")
	private String ipUsuAlteracao;

	@JoinColumn(name = "SEQ_SISTEMA", referencedColumnName = "SEQ_SISTEMA")
	@ManyToOne(fetch = FetchType.LAZY)
	private CorporativoSistema corporativoSistema;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "corporativoPerfil", fetch = FetchType.LAZY)
	private List<CorporativoUsuarioSistemaPerfil> corporativoUsuarioSistemaPerfilList;

	public CorporativoPerfil() {
	}

	public CorporativoPerfil(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public char getIsAtivo() {
		return isAtivo;
	}

	public void setIsAtivo(char isAtivo) {
		this.isAtivo = isAtivo;
	}

	public char getIsVisibilidadePublica() {
		return isVisibilidadePublica;
	}

	public void setIsVisibilidadePublica(char isVisibilidadePublica) {
		this.isVisibilidadePublica = isVisibilidadePublica;
	}

	public String getUsuarioInclusao() {
		return usuarioInclusao;
	}

	public void setUsuarioInclusao(String usuarioInclusao) {
		this.usuarioInclusao = usuarioInclusao;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public String getIpUsuInclusao() {
		return ipUsuInclusao;
	}

	public void setIpUsuInclusao(String ipUsuInclusao) {
		this.ipUsuInclusao = ipUsuInclusao;
	}

	public Integer getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Integer usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public String getIpUsuAlteracao() {
		return ipUsuAlteracao;
	}

	public void setIpUsuAlteracao(String ipUsuAlteracao) {
		this.ipUsuAlteracao = ipUsuAlteracao;
	}

	public CorporativoSistema getCorporativoSistema() {
		return corporativoSistema;
	}

	public void setCorporativoSistema(CorporativoSistema corporativoSistema) {
		this.corporativoSistema = corporativoSistema;
	}

	public List<CorporativoUsuarioSistemaPerfil> getCorporativoUsuarioSistemaPerfilList() {
		return corporativoUsuarioSistemaPerfilList;
	}

	public void setCorporativoUsuarioSistemaPerfilList(
			List<CorporativoUsuarioSistemaPerfil> corporativoUsuarioSistemaPerfilList) {
		this.corporativoUsuarioSistemaPerfilList = corporativoUsuarioSistemaPerfilList;
	}

}
