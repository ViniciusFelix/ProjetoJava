/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.jus.cnj.corporativo.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.WordUtils;

import br.jus.cnj.corporativo.autenticacao.Credencial;

/**
 * 
 * @author fabio.pereira
 */
@Entity
@Table(name = "sistema", schema = "corporativo")
public class CorporativoSistema implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1585554794555812344L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SEQ_SISTEMA")
	private Integer id;

	@Column(name = "DSC_SISTEMA")
	private String descricao;

	@Column(name = "DSC_SISTEMA_SIGLA")
	private String sigla;

	@Column(name = "DSC_SISTEMA_URL")
	private String url;

	@Column(name = "FLG_SISTEMA_ATIVO")
	private char ativo;

	@Column(name = "FLG_SISTEMA_EXTERNO")
	private char isExterno;

	@Column(name = "FLG_DADOS_ESPECIFICOS")
	private char isDadosEspecificos;

	@Column(name = "FLG_MULTIPERFIL")
	private char isMultiperfil;

	@Column(name = "USU_INCLUSAO")
	private String usuarioInclusao;

	@Column(name = "DAT_INCLUSAO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInclusao;

	@Column(name = "DSC_IP_USU_INCLUSAO")
	private String ipUsuInclusao;

	@Column(name = "USU_ALTERACAO")
	private String usuarioAlteracao;

	@Column(name = "DAT_ALTERACAO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAlteracao;

	@Column(name = "DSC_IP_USU_ALTERACAO")
	private String ipUsuAlteracao;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "corporativoSistema", fetch = FetchType.LAZY)
	private List<CorporativoPerfil> corporativoPerfilList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "corporativoSistema", fetch = FetchType.LAZY)
	private List<CorporativoUsuarioSistemaPerfil> corporativoUsuarioSistemaPerfilList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public char getAtivo() {
		return ativo;
	}

	public void setAtivo(char ativo) {
		this.ativo = ativo;
	}

	public char getIsExterno() {
		return isExterno;
	}

	public void setIsExterno(char isExterno) {
		this.isExterno = isExterno;
	}

	public char getIsDadosEspecificos() {
		return isDadosEspecificos;
	}

	public void setIsDadosEspecificos(char isDadosEspecificos) {
		this.isDadosEspecificos = isDadosEspecificos;
	}

	public char getIsMultiperfil() {
		return isMultiperfil;
	}

	public void setIsMultiperfil(char isMultiperfil) {
		this.isMultiperfil = isMultiperfil;
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

	public String getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(String usuarioAlteracao) {
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

	public List<CorporativoPerfil> getCorporativoPerfilList() {
		return corporativoPerfilList;
	}

	public void setCorporativoPerfilList(
			List<CorporativoPerfil> corporativoPerfilList) {
		this.corporativoPerfilList = corporativoPerfilList;
	}

	public List<CorporativoUsuarioSistemaPerfil> getCorporativoUsuarioSistemaPerfilList() {
		return corporativoUsuarioSistemaPerfilList;
	}

	public void setCorporativoUsuarioSistemaPerfilList(
			List<CorporativoUsuarioSistemaPerfil> corporativoUsuarioSistemaPerfilList) {
		this.corporativoUsuarioSistemaPerfilList = corporativoUsuarioSistemaPerfilList;
	}	

}
