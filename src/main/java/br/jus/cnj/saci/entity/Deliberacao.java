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
@Table(name = "deliberacao", schema = "saci")
public class Deliberacao extends BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq_deliberacao")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "seq_tipo_deliberacao")
	private TipoDeliberacao tipoDeliberacao;

	@ManyToOne
	@JoinColumn(name = "seq_resposta")
	private Resposta resposta;

	@Lob
	@Column(name = "dsc_deliberacao")
	private String descricao;

	@Column(name = "num_dias_prazo_cumprimento")
	private int numDiasPrazoCumprimento;

	@Column(name = "num_procedimento")
	private String numProcedimento;

	@Column(name = "usu_alteracao")
	private int usuAlteracao;

	@Column(name = "usu_inclusao")
	private int usuInclusao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dat_alteracao")
	private Date datAlteracao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dat_inclusao")
	private Date datInclusao;
	
	@Column(name = "flg_resolvida")
	private char flagResolvida;

	public char getFlagResolvida() {
		return flagResolvida;
	}

	public void setFlagResolvida(char flagResolvida) {
		this.flagResolvida = flagResolvida;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TipoDeliberacao getTipoDeliberacao() {
		return tipoDeliberacao;
	}

	public void setTipoDeliberacao(TipoDeliberacao tipoDeliberacao) {
		this.tipoDeliberacao = tipoDeliberacao;
	}

	public Resposta getResposta() {
		return resposta;
	}

	public void setResposta(Resposta resposta) {
		this.resposta = resposta;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getNumDiasPrazoCumprimento() {
		return numDiasPrazoCumprimento;
	}

	public void setNumDiasPrazoCumprimento(int numDiasPrazoCumprimento) {
		this.numDiasPrazoCumprimento = numDiasPrazoCumprimento;
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

	public String getNumProcedimento() {
		return numProcedimento;
	}

	public void setNumProcedimento(String numProcedimento) {
		this.numProcedimento = numProcedimento;
	}

}
