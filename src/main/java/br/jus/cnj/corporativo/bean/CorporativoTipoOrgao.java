package br.jus.cnj.corporativo.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * @author fabio.pereira
 */
@Entity
@Table(name = "tipo_orgao", schema = "corporativo")
public class CorporativoTipoOrgao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7344342113475019547L;

	@Id
	@Column(name = "TIP_ORGAO")
	private String id;

	@Column(name = "DSC_TIP_ORGAO")
	private String descricao;

	@Column(name = "TIP_ESFERA_JUSTICA")
	private Character tipoEsferaJustica;

	@Column(name = "INT_ORDEM")
	private int ordem;

	@Column(name = "FLG_VISIBILIDADE_PUBLICA")
	private char visibilidadePublica;

	@OneToMany(mappedBy = "tipoOrgao", fetch = FetchType.LAZY)
	private List<CorporativoTipoOrgao> tipoOrgaoList;

	@JoinColumn(name = "TIP_ORGAO_PAI", referencedColumnName = "TIP_ORGAO")
	@ManyToOne(fetch = FetchType.LAZY)
	private CorporativoTipoOrgao tipoOrgao;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Character getTipoEsferaJustica() {
		return tipoEsferaJustica;
	}

	public void setTipoEsferaJustica(Character tipoEsferaJustica) {
		this.tipoEsferaJustica = tipoEsferaJustica;
	}

	public int getOrdem() {
		return ordem;
	}

	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}

	public char getVisibilidadePublica() {
		return visibilidadePublica;
	}

	public void setVisibilidadePublica(char visibilidadePublica) {
		this.visibilidadePublica = visibilidadePublica;
	}

	public List<CorporativoTipoOrgao> getTipoOrgaoList() {
		return tipoOrgaoList;
	}

	public void setTipoOrgaoList(List<CorporativoTipoOrgao> tipoOrgaoList) {
		this.tipoOrgaoList = tipoOrgaoList;
	}

	public CorporativoTipoOrgao getTipoOrgao() {
		return tipoOrgao;
	}

	public void setTipoOrgao(CorporativoTipoOrgao tipoOrgao) {
		this.tipoOrgao = tipoOrgao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ordem;
		result = prime
				* result
				+ ((tipoEsferaJustica == null) ? 0 : tipoEsferaJustica
						.hashCode());
		result = prime * result
				+ ((tipoOrgao == null) ? 0 : tipoOrgao.hashCode());
		result = prime * result
				+ ((tipoOrgaoList == null) ? 0 : tipoOrgaoList.hashCode());
		result = prime * result + visibilidadePublica;
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
		CorporativoTipoOrgao other = (CorporativoTipoOrgao) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ordem != other.ordem)
			return false;
		if (tipoEsferaJustica == null) {
			if (other.tipoEsferaJustica != null)
				return false;
		} else if (!tipoEsferaJustica.equals(other.tipoEsferaJustica))
			return false;
		if (tipoOrgao == null) {
			if (other.tipoOrgao != null)
				return false;
		} else if (!tipoOrgao.equals(other.tipoOrgao))
			return false;
		if (tipoOrgaoList == null) {
			if (other.tipoOrgaoList != null)
				return false;
		} else if (!tipoOrgaoList.equals(other.tipoOrgaoList))
			return false;
		if (visibilidadePublica != other.visibilidadePublica)
			return false;
		return true;
	}

	

}