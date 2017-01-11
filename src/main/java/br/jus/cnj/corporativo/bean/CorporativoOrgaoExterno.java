package br.jus.cnj.corporativo.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
@Table(name = "orgao_externo", schema = "corporativo")
public class CorporativoOrgaoExterno implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5108380717407478881L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SEQ_ORGAO")
	private Integer id;

	@Column(name = "DSC_ORGAO")
	private String descricao;

	@Column(name = "COD_HIERARQUIA")
	private String hierarquia;

	@Column(name = "FLG_ATIVO")
	private String ativo;

	@Column(name = "USU_INCLUSAO")
	private String usuarioInclusao;

	@Column(name = "DAT_INCLUSAO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInclusao;

	@Column(name = "DSC_IP_USU_INCLUSAO")
	private String ipUsuarioInclusao;

	@Column(name = "DAT_ALTERACAO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAlteracao;

	@Column(name = "USU_ALTERACAO")
	private String usuarioAlteracao;

	@OneToMany(mappedBy = "orgaoExterno", fetch = FetchType.LAZY)
	private List<CorporativoOrgaoExterno> orgaoExternos;

	@JoinColumn(name = "SEQ_ORGAO_PAI", referencedColumnName = "SEQ_ORGAO")
	@ManyToOne(fetch = FetchType.LAZY)
	private CorporativoOrgaoExterno orgaoExterno;

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

	public String getHierarquia() {
		return hierarquia;
	}

	public void setHierarquia(String hierarquia) {
		this.hierarquia = hierarquia;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
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

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public String getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(String usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public List<CorporativoOrgaoExterno> getOrgaoExternos() {
		return orgaoExternos;
	}

	public void setOrgaoExternos(List<CorporativoOrgaoExterno> orgaoExternos) {
		this.orgaoExternos = orgaoExternos;
	}

	public CorporativoOrgaoExterno getOrgaoExterno() {
		return orgaoExterno;
	}

	public void setOrgaoExterno(CorporativoOrgaoExterno orgaoExterno) {
		this.orgaoExterno = orgaoExterno;
	}

}