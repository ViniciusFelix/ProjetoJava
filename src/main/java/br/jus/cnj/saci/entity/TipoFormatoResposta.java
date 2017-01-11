package br.jus.cnj.saci.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tipo_formato_resposta", schema="saci")
public class TipoFormatoResposta extends BaseEntity<Integer> {	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="seq_tipo_formato_resposta")
	private Integer id;

	@Column(name="dsc_tipo_resposta")
	private String dscTipoResposta;

	@OneToMany(mappedBy="tipoFormatoResposta")
	private List<ModeloPergunta> modeloPerguntaBasicos;
	
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDscTipoResposta() {
		return dscTipoResposta;
	}

	public void setDscTipoResposta(String dscTipoResposta) {
		this.dscTipoResposta = dscTipoResposta;
	}

	public List<ModeloPergunta> getModeloPerguntaBasicos() {
		return modeloPerguntaBasicos;
	}

	public void setModeloPerguntaBasicos(
			List<ModeloPergunta> modeloPerguntaBasicos) {
		this.modeloPerguntaBasicos = modeloPerguntaBasicos;
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
	

}