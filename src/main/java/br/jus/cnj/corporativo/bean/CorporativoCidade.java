package br.jus.cnj.corporativo.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author fabio.pereira
 */
@Entity
@Table(name = "cidade", schema = "corporativo")
public class CorporativoCidade implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5742714567420380000L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SEQ_CIDADE")
	private Integer id;

	@Column(name = "DSC_CIDADE")
	private String descricao;

	@Column(name = "COD_IBGE")
	private String ibge;

	@JoinColumn(name = "SIG_UF", referencedColumnName = "SIG_UF")
	@ManyToOne(fetch = FetchType.LAZY)
	private CorporativoEstado estado;

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

	public String getIbge() {
		return ibge;
	}

	public void setIbge(String ibge) {
		this.ibge = ibge;
	}

	public CorporativoEstado getEstado() {
		return estado;
	}

	public void setEstado(CorporativoEstado estado) {
		this.estado = estado;
	}

}
