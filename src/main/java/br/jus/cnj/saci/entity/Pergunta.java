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
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="pergunta", schema="saci")
public class Pergunta extends BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="seq_pergunta")
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dat_alteracao")
	private Date datAlteracao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dat_inclusao")
	private Date datInclusao;

	@Lob
	@Column(name="dsc_pergunta")
	private String dscPergunta;

	@Column(name="num_ordem")
	private int numOrdem;
	
	@Column(name="usu_alteracao")
	private int usuAlteracao;

	@Column(name="usu_inclusao")
	private int usuInclusao;
	
	@Column(name="flg_pergunta_modelo")
	private char perguntaModelo;

	//bi-directional many-to-one association to Questionario
	@ManyToOne
	@JoinColumn(name="seq_questionario")
	//@Cascade(CascadeType.ALL)
	private Questionario questionario;

	//bi-directional many-to-one association to TipoFormatoResposta
	@ManyToOne
	@JoinColumn(name="seq_tipo_formato_resposta")
	private TipoFormatoResposta tipoFormatoResposta;
 	
	//bi-directional many-to-one association to TipoFormatoResposta
	@ManyToOne
	@JoinColumn(name="seq_tipo_aplicacao_pergunta")
	private TipoAplicacaoPergunta tipoAplicacaoPergunta;

	//bi-directional many-to-one association to Resposta
	@OneToMany(mappedBy="pergunta")
	private List<Resposta> respostas;
	
	@OneToMany(mappedBy="pergunta", orphanRemoval=true)
	@Cascade(CascadeType.ALL)
	private List<PerguntaAutorizadaOrgao> perguntaAutorizada;
	
	@Transient
	private boolean autorizada;
	
	
	
	public boolean isAutorizada() {
		return autorizada;
	}

	public void setAutorizada(boolean autorizada) {
		this.autorizada = autorizada;
	}

	public Pergunta() {
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

	public String getDscPergunta() {
		return this.dscPergunta;
	}

	public void setDscPergunta(String dscPergunta) {
		this.dscPergunta = dscPergunta;
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

	public Questionario getQuestionario() {
		return this.questionario;
	}

	public void setQuestionario(Questionario questionario) {
		this.questionario = questionario;
	}
	
	public char getPerguntaModelo() {
		return perguntaModelo;
	}

	public void setPerguntaModelo(char perguntaModelo) {
		this.perguntaModelo = perguntaModelo;
	}

	public TipoFormatoResposta getTipoFormatoResposta() {
		return this.tipoFormatoResposta;
	}

	public void setTipoFormatoResposta(TipoFormatoResposta tipoFormatoResposta) {
		this.tipoFormatoResposta = tipoFormatoResposta;
	}
	
	public List<Resposta> getRespostas() {
		return this.respostas;
	}

	public void setRespostas(List<Resposta> respostas) {
		this.respostas = respostas;
	}

	public Resposta addResposta(Resposta resposta) {
		getRespostas().add(resposta);
		resposta.setPergunta(this);

		return resposta;
	}

	public Resposta removeResposta(Resposta resposta) {
		getRespostas().remove(resposta);
		resposta.setPergunta(null);

		return resposta;
	}
	
	public int getNumOrdem() {
		return numOrdem;
	}

	public void setNumOrdem(int numOrdem) {
		this.numOrdem = numOrdem;
	}

	public TipoAplicacaoPergunta getTipoAplicacaoPergunta() {
		return tipoAplicacaoPergunta;
	}

	public void setTipoAplicacaoPergunta(TipoAplicacaoPergunta tipoAplicacaoPergunta) {
		this.tipoAplicacaoPergunta = tipoAplicacaoPergunta;
	}

	public List<PerguntaAutorizadaOrgao> getPerguntaAutorizada() {
		return perguntaAutorizada;
	}

	public void setPerguntaAutorizada(
			List<PerguntaAutorizadaOrgao> perguntaAutorizada) {
		this.perguntaAutorizada = perguntaAutorizada;
	}

	
}