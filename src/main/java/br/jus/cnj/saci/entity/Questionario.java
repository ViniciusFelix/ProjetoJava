package br.jus.cnj.saci.entity;

import java.util.Date;
import java.util.List;

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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="questionario", schema="saci")
public class Questionario extends BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="seq_questionario")
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dat_alteracao")
	private Date datAlteracao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dat_inclusao")
	private Date datInclusao;

	@Column(name="flg_ativo")
	private int flgAtivo;

	@Column(name="nom_questionario")
	private String nomQuestionario;

	@Column(name="usu_alteracao")
	private int usuAlteracao;

	@Column(name="usu_inclusao")
	private int usuInclusao;

	//bi-directional many-to-one association to Pergunta
//	@OneToMany(mappedBy="questionario", orphanRemoval=true)
//	@Cascade(CascadeType.ALL)
//	private List<Pergunta> perguntas;
	
//	@OneToMany(mappedBy="questionario", fetch=FetchType.EAGER, orphanRemoval=true)
//	@Cascade(CascadeType.ALL)
//	private List<InspecaoOrgao> orgaos;

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


	public String getNomQuestionario() {
		return this.nomQuestionario;
	}

	public void setNomQuestionario(String nomQuestionario) {
		this.nomQuestionario = nomQuestionario;
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

//	public List<Pergunta> getPerguntas() {
//		return this.perguntas;
//	}
//
//	public void setPerguntas(List<Pergunta> perguntas) {
//		this.perguntas = perguntas;
//	}

	public int getFlgAtivo() {
		return flgAtivo;
	}

	public void setFlgAtivo(int flgAtivo) {
		this.flgAtivo = flgAtivo;
	}
	
//	public List<InspecaoOrgao> getOrgaos() {
//		return orgaos;
//	}
//
//	public void setOrgaos(List<InspecaoOrgao> orgaos) {
//		this.orgaos = orgaos;
//	}

}