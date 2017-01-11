package br.jus.cnj.corporativo.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author fabio.pereira
 */
@Entity
@Table(name = "usuario", schema = "corporativo")
public class CorporativoUsuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2513302508045295315L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SEQ_USUARIO")
	private Long id;

	@Column(name = "SIG_USUARIO")
	private String sig;

	@Column(name = "DSC_SENHA")
	private String senha;

	@Column(name = "NOM_USUARIO")
	private String nome;

	@Column(name = "NUM_CPF")
	private String cpf;

	@Column(name = "FLG_ATIVO")
	private char ativo;

	@Column(name = "USU_INCLUSAO")
	private String usuarioInclusao;

	@Column(name = "DAT_INCLUSAO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInclusao;

	@Column(name = "DSC_IP_USU_INCLUSAO")
	private String ipUsuarioInclusao;

	@Column(name = "USU_ALTERACAO")
	private String usuarioAlteracao;

	@Column(name = "DAT_ALTERACAO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAlteracao;

	@JoinColumn(name = "SIG_TIPO_CARGO", referencedColumnName = "SIG_TIPO_CARGO")
	@ManyToOne(fetch = FetchType.LAZY)
	private CorporativoTipoCargo tipoCargo;

	@JoinColumn(name = "SIG_UF", referencedColumnName = "SIG_UF")
	@ManyToOne(fetch = FetchType.LAZY)
	private CorporativoEstado estado;

	@JoinColumn(name = "SEQ_ORGAO_EXTERNO", referencedColumnName = "SEQ_ORGAO")
	@ManyToOne(fetch = FetchType.LAZY)
	private CorporativoOrgaoExterno orgaoExterno;

	@JoinColumn(name = "SEQ_ORGAO", referencedColumnName = "SEQ_ORGAO")
	@ManyToOne(fetch = FetchType.LAZY)
	private CorporativoOrgao orgao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSig() {
		return sig;
	}

	public void setSig(String sig) {
		this.sig = sig;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public char getAtivo() {
		return ativo;
	}

	public void setAtivo(char ativo) {
		this.ativo = ativo;
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

	public String getIpUsuarioInclusao() {
		return ipUsuarioInclusao;
	}

	public void setIpUsuarioInclusao(String ipUsuarioInclusao) {
		this.ipUsuarioInclusao = ipUsuarioInclusao;
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

	public CorporativoTipoCargo getTipoCargo() {
		return tipoCargo;
	}

	public void setTipoCargo(CorporativoTipoCargo tipoCargo) {
		this.tipoCargo = tipoCargo;
	}

	public CorporativoEstado getEstado() {
		return estado;
	}

	public void setEstado(CorporativoEstado estado) {
		this.estado = estado;
	}

	public CorporativoOrgaoExterno getOrgaoExterno() {
		return orgaoExterno;
	}

	public void setOrgaoExterno(CorporativoOrgaoExterno orgaoExterno) {
		this.orgaoExterno = orgaoExterno;
	}

	public CorporativoOrgao getOrgao() {
		return orgao;
	}

	public void setOrgao(CorporativoOrgao orgao) {
		this.orgao = orgao;
	}

}