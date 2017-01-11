package br.jus.cnj.corporativo.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.jus.cnj.saci.entity.BaseEntity;
import br.jus.cnj.saci.entity.Deliberacao;

/**
 * 
 * @author fabio.pereira
 */
@Entity
@Table(name = "orgao", schema = "corporativo")
public class CorporativoOrgao extends BaseEntity<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4333453544315160463L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SEQ_ORGAO")
	private Integer id;

	@Column(name = "INT_ORDEM_ORGAO")
	private Integer ordemOrgao;

	@Column(name = "DSC_ORGAO")
	private String descrica;

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

	@JoinColumn(name = "TIP_ORGAO", referencedColumnName = "TIP_ORGAO")
	@ManyToOne
	private CorporativoTipoOrgao tipoOrgao;

	@JoinColumn(name = "SEQ_ORGAO_PAI", referencedColumnName = "SEQ_ORGAO")
	@ManyToOne
	private CorporativoOrgao orgao;
	
	@OneToMany(mappedBy="orgao")
	private List<CorporativoOrgao> orgaosFilhos;
	
//	@Transient
//	private List<Deliberacao> deliberacoes;
//
//	public List<Deliberacao> getDeliberacoes() {
//		return deliberacoes;
//	}
//
//	public void setDeliberacoes(List<Deliberacao> deliberacoes) {
//		this.deliberacoes = deliberacoes;
//	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrdemOrgao() {
		return ordemOrgao;
	}

	public void setOrdemOrgao(Integer ordemOrgao) {
		this.ordemOrgao = ordemOrgao;
	}

	public String getDescrica() {
		return descrica;
	}

	public void setDescrica(String descrica) {
		this.descrica = descrica;
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

	public CorporativoTipoOrgao getTipoOrgao() {
		return tipoOrgao;
	}

	public void setTipoOrgao(CorporativoTipoOrgao tipoOrgao) {
		this.tipoOrgao = tipoOrgao;
	}

	public CorporativoOrgao getOrgao() {
		return orgao;
	}

	public void setOrgao(CorporativoOrgao orgao) {
		this.orgao = orgao;
	}
	
	public List<CorporativoOrgao> getOrgaosFilhos() {
		return orgaosFilhos;
	}

	public void setOrgaosFilhos(List<CorporativoOrgao> orgaosFilhos) {
		this.orgaosFilhos = orgaosFilhos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString(){
		
		return ((ordemOrgao != null && ordemOrgao != 0) ? ordemOrgao : "") + descrica;
	}
	

}