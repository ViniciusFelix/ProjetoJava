package br.jus.cnj.saci.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="resposta", schema="saci")
public class Resposta extends BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="seq_resposta")
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dat_alteracao")
	private Date datAlteracao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dat_inclusao")
	private Date datInclusao;

	@Lob
	@Column(name="dsc_resposta")
	private String dscResposta;

	@Column(name="usu_alteracao")
	private int usuAlteracao;

	@Column(name="usu_inclusao")
	private int usuInclusao;
	
	//bi-directional many-to-one association to Pergunta
	@ManyToOne
	@JoinColumn(name="seq_pergunta")
	@Cascade(CascadeType.ALL)
	private Pergunta pergunta;

	//bi-directional many-to-one association to Inspecao Orgao
	@ManyToOne
	@JoinColumn(name="seq_inspecao_orgao")
	private InspecaoOrgao inspecaoOrgao;
	
	@OneToMany(mappedBy="resposta", fetch=FetchType.EAGER, orphanRemoval=true)
	@Cascade(CascadeType.ALL)
	private List<ComplementoResposta> complementoResposta;
	
	@OneToMany(mappedBy="resposta", fetch=FetchType.EAGER, orphanRemoval=true)
	@Cascade(CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Deliberacao> deliberacao;

	public Resposta() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDatAlteracao() {
		return this.datAlteracao;
	}

	public void setDatAlteracao(Date datAlteracao) {
		this.datAlteracao = datAlteracao;
	}

	public Date getDatInclusao() {
		return this.datInclusao;
	}

	public void setDatInclusao(Date datInclusao) {
		this.datInclusao = datInclusao;
	}

	public String getDscResposta() {
		return this.dscResposta;
	}

	public void setDscResposta(String dscResposta) {
		this.dscResposta = dscResposta;
	}

	public int getUsuAlteracao() {
		return this.usuAlteracao;
	}

	public void setUsuAlteracao(int usuAlteracao) {
		this.usuAlteracao = usuAlteracao;
	}

	public int getUsuInclusao() {
		return this.usuInclusao;
	}

	public void setUsuInclusao(int usuInclusao) {
		this.usuInclusao = usuInclusao;
	}

	public Pergunta getPergunta() {
		return this.pergunta;
	}

	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}

	public InspecaoOrgao getInspecaoOrgao() {
		return inspecaoOrgao;
	}

	public void setInspecaoOrgao(InspecaoOrgao inspecaoOrgao) {
		this.inspecaoOrgao = inspecaoOrgao;
	}

	public List<ComplementoResposta> getComplementoResposta() {
		return complementoResposta;
	}

	public void setComplementoResposta(List<ComplementoResposta> complementoResposta) {
		this.complementoResposta = complementoResposta;
	}

	public List<Deliberacao> getDeliberacao() {
		return deliberacao;
	}

	public void setDeliberacao(List<Deliberacao> deliberacao) {
		this.deliberacao = deliberacao;
	}
	
	
}