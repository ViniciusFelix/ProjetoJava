package br.jus.cnj.saci.entity;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.jus.cnj.saci.model.PerguntaModel;

@Entity
@Table(name="tipo_aplicacao_pergunta", schema="saci")
public class TipoAplicacaoPergunta extends BaseEntity<Integer> {	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="seq_tipo_aplicacao_pergunta")
	private Integer id;

	@Column(name="dsc_tipo_aplicacao")
	private String dscTipoAplicacao;

	@OneToMany(mappedBy="tipoAplicacaoPergunta")
	private List<ModeloPergunta> modeloPerguntaBasicos;
	
	@OneToMany(mappedBy="tipoAplicacaoPergunta")
	private List<Pergunta> perguntas;
	
	@JoinTable(name="aplicacao_objeto_pergunta", schema = "saci", joinColumns = { 
            @JoinColumn(name = "seq_tipo_aplicacao_pergunta", referencedColumnName = "seq_tipo_aplicacao_pergunta")
     }, 
     inverseJoinColumns = { 
            @JoinColumn(name = "seq_tipo_objeto_aplicacao", referencedColumnName = "seq_tipo_objeto_aplicacao")
     })
	@ManyToMany
	private Collection<TipoObjetoAplicacao> tipoObjetoAplicacao;

	@Column(name="usu_alteracao")
	private int usuAlteracao;

	@Column(name="usu_inclusao")
	private int usuInclusao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dat_alteracao")
	private Date datAlteracao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dat_inclusao")
	private Date datInclusao;

	@Transient
	private PerguntaModel perguntaModel;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDscTipoAplicacao() {
		return dscTipoAplicacao;
	}

	public void setDscTipoAplicacao(String dscTipoAplicacao) {
		this.dscTipoAplicacao = dscTipoAplicacao;
	}

	public List<ModeloPergunta> getModeloPerguntaBasicos() {
		return modeloPerguntaBasicos;
	}

	public void setModeloPerguntaBasicos(List<ModeloPergunta> modeloPerguntaBasicos) {
		this.modeloPerguntaBasicos = modeloPerguntaBasicos;
	}

	public List<Pergunta> getPerguntas() {
		return perguntas;
	}

	public void setPerguntas(List<Pergunta> perguntas) {
		this.perguntas = perguntas;
	}

	public Collection<TipoObjetoAplicacao> getTipoObjetoAplicacao() {
		return tipoObjetoAplicacao;
	}

	public void setTipoObjetoAplicacao(
			Collection<TipoObjetoAplicacao> tipoObjetoAplicacao) {
		this.tipoObjetoAplicacao = tipoObjetoAplicacao;
	}

	public int getUsuAlteracao() {
		return usuAlteracao;
	}

	public void setUsuAlteracao(int usuAlteracao) {
		this.usuAlteracao = usuAlteracao;
	}

	public int getUsuInclusao() {
		return usuInclusao;
	}

	public void setUsuInclusao(int usuInclusao) {
		this.usuInclusao = usuInclusao;
	}

	public Date getDatAlteracao() {
		return datAlteracao;
	}

	public void setDatAlteracao(Date datAlteracao) {
		this.datAlteracao = datAlteracao;
	}

	public Date getDatInclusao() {
		return datInclusao;
	}

	public void setDatInclusao(Date datInclusao) {
		this.datInclusao = datInclusao;
	}

	@Override
	public String toString(){
		return dscTipoAplicacao;
	}

	public PerguntaModel getPerguntaModel() {
		return perguntaModel;
	}

	public void setPerguntaModel(PerguntaModel perguntaModel) {
		this.perguntaModel = perguntaModel;
	}

	
}