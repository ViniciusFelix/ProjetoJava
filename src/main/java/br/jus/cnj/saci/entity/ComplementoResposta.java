package br.jus.cnj.saci.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="complemento_resposta", schema="saci")
public class ComplementoResposta extends BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="seq_complemento_resposta")
	private Integer id;
	
	@Column(name="dsc_complemento_resposta")
	private String dscComplementoResposta;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dat_alteracao")
	private Date datAlteracao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dat_inclusao")
	private Date datInclusao;

	@Column(name="usu_alteracao")
	private int usuAlteracao;

	@Column(name="usu_inclusao")
	private int usuInclusao;
	
	@ManyToOne
	@JoinColumn(name="seq_resposta")
	private Resposta resposta;
	
	@ManyToOne
	@JoinColumn(name="seq_tipo_complemento_resposta")
	private TipoComplementoResposta tipoComplementoResposta;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDscComplementoResposta() {
		return dscComplementoResposta;
	}

	public void setDscComplementoResposta(String dscComplementoResposta) {
		this.dscComplementoResposta = dscComplementoResposta;
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

	public Resposta getResposta() {
		return resposta;
	}

	public void setResposta(Resposta resposta) {
		this.resposta = resposta;
	}
	
	public TipoComplementoResposta getTipoComplementoResposta() {
		return tipoComplementoResposta;
	}

	public void setTipoComplementoResposta(TipoComplementoResposta tipoComplementoResposta) {
		this.tipoComplementoResposta = tipoComplementoResposta;
	}

}