package br.jus.cnj.saci.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import br.jus.cnj.corporativo.bean.CorporativoOrgao;
import br.jus.cnj.corregedoria.entity.Cartorio;

@Entity
@Table(name="inspecao_orgao", schema="saci")
public class InspecaoOrgao extends BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="seq_inspecao_orgao")
	private Integer id;

	@ManyToOne
	@JoinColumn(name="seq_inspecao")
	@Cascade(CascadeType.MERGE)
	private Inspecao inspecao;

	@ManyToOne
	@JoinColumn(name="SEQ_ORGAO")
	private CorporativoOrgao orgao;

	@ManyToOne
	@Cascade(CascadeType.ALL)
	@JoinColumn(name="seq_subunidade_orgao")
	private SubUnidadeOrgao subUnidadeOrgao;

	@ManyToOne
	@JoinColumn(name="SEQ_SERVENTIA_EXTRAJUDICIAL")
	private Cartorio cartorio;
	
	@Column(name="flg_ativo")
	private String flgAtivo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Inspecao getInspecao() {
		return inspecao;
	}

	public void setInspecao(Inspecao inspecao) {
		this.inspecao = inspecao;
	}

	public CorporativoOrgao getOrgao() {
		return orgao;
	}

	public void setOrgao(CorporativoOrgao orgao) {
		this.orgao = orgao;
	}

	public SubUnidadeOrgao getSubUnidadeOrgao() {
		return subUnidadeOrgao;
	}

	public void setSubUnidadeOrgao(SubUnidadeOrgao subUnidadeOrgao) {
		this.subUnidadeOrgao = subUnidadeOrgao;
	}

	public Cartorio getCartorio() {
		return cartorio;
	}

	public void setCartorio(Cartorio cartorio) {
		this.cartorio = cartorio;
	}
//
//	public Questionario getQuestionario() {
//		return questionario;
//	}
//
//	public void setQuestionario(Questionario questionario) {
//		this.questionario = questionario;
//	}

	public String getFlgAtivo() {
		return flgAtivo;
	}

	public void setFlgAtivo(String flgAtivo) {
		this.flgAtivo = flgAtivo;
	}	
	

}
