package br.jus.cnj.saci.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="modelo_pergunta", schema="saci")
public class ModeloPergunta extends BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="seq_modelo_pergunta")
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dat_alteracao")
	private Date datAlteracao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dat_inclusao")
	private Date datInclusao;

	@Lob
	@Column(name="dsc_modelo_pergunta")
	private String descricao;

	@Column(name="num_ordem")
	private int numOrdem;

	@Column(name="usu_alteracao")
	private int usuAlteracao;

	@Column(name="usu_inclusao")
	private int usuInclusao;

	@ManyToOne
	@JoinColumn(name="seq_tipo_formato_resposta")
	private TipoFormatoResposta tipoFormatoResposta;

	@ManyToOne
	@JoinColumn(name="seq_tipo_aplicacao_pergunta")
	private TipoAplicacaoPergunta tipoAplicacaoPergunta;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getNumOrdem() {
		return numOrdem;
	}

	public void setNumOrdem(int numOrdem) {
		this.numOrdem = numOrdem;
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

	public TipoFormatoResposta getTipoFormatoResposta() {
		return tipoFormatoResposta;
	}

	public void setTipoFormatoResposta(TipoFormatoResposta tipoFormatoResposta) {
		this.tipoFormatoResposta = tipoFormatoResposta;
	}

	public TipoAplicacaoPergunta getTipoAplicacaoPergunta() {
		return tipoAplicacaoPergunta;
	}

	public void setTipoAplicacaoPergunta(TipoAplicacaoPergunta tipoAplicacaoPergunta) {
		this.tipoAplicacaoPergunta = tipoAplicacaoPergunta;
	}


}