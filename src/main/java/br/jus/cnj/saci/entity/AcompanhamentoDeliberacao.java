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
@Table(name="acompanhamento_deliberacao", schema="saci")
public class AcompanhamentoDeliberacao extends BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="seq_acompanhamento_deliberacao")
	private Integer id;

	@Column(name="dsc_resposta")
	private String dscResposta;
	
	@Column(name="tip_envio_acompanhamento")
	private String tipoEnvioAcompanhamento;
	@ManyToOne
	@JoinColumn(name="seq_deliberacao")
	private Deliberacao deliberacao;
	
	@Column(name="usu_alteracao")
	private int usuAlteracao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dat_alteracao")
	private Date datAlteracao;

	@Column(name="usu_inclusao")
	private int usuInclusao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dat_inclusao")
	private Date datInclusao;
	
	@Column(name="num_dias_prazo")
	private int numDiasPrazo;
	
	@OneToMany(mappedBy="acompanhamentoDeliberacao", fetch=FetchType.EAGER)
	@Cascade(CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<DocumentoAcompanhamento> documentoAcompanhamento;
	

	public List<DocumentoAcompanhamento> getDocumentoAcompanhamento() {
		return documentoAcompanhamento;
	}

	public void setDocumentoAcompanhamento(
			List<DocumentoAcompanhamento> documentoAcompanhamento) {
		this.documentoAcompanhamento = documentoAcompanhamento;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDscResposta() {
		return dscResposta;
	}

	public void setDscResposta(String dscResposta) {
		this.dscResposta = dscResposta;
	}

	public Deliberacao getDeliberacao() {
		return deliberacao;
	}

	public void setDeliberacao(Deliberacao deliberacao) {
		this.deliberacao = deliberacao;
	}

	public int getUsuAlteracao() {
		return usuAlteracao;
	}

	public void setUsuAlteracao(int usuAlteracao) {
		this.usuAlteracao = usuAlteracao;
	}

	public Date getDatAlteracao() {
		return datAlteracao;
	}

	public void setDatAlteracao(Date datAlteracao) {
		this.datAlteracao = datAlteracao;
	}

	public int getUsuInclusao() {
		return usuInclusao;
	}

	public void setUsuInclusao(int usuInclusao) {
		this.usuInclusao = usuInclusao;
	}

	public Date getDatInclusao() {
		return datInclusao;
	}

	public void setDatInclusao(Date datInclusao) {
		this.datInclusao = datInclusao;
	}

	public int getNumDiasPrazo() {
		return numDiasPrazo;
	}

	public void setNumDiasPrazo(int numDiasPrazo) {
		this.numDiasPrazo = numDiasPrazo;
	}

	public String getTipoEnvioAcompanhamento() {
		return tipoEnvioAcompanhamento;
	}

	public void setTipoEnvioAcompanhamento(String tipoEnvioAcompanhamento) {
		this.tipoEnvioAcompanhamento = tipoEnvioAcompanhamento;
	}

	

	
}
