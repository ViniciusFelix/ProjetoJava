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
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="determinacao_inspecao", schema="saci")
public class DeterminacaoInspecao extends BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="seq_determinacao_inspecao")
	private Integer id;

	@ManyToMany
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinTable(name = "saci.documento_determinacao_inspecao", joinColumns = { @JoinColumn(name = "seq_determinacao_inspecao") }, inverseJoinColumns = { @JoinColumn(name = "seq_documento") })
	private List<Documento> documentos;
	
	@ManyToOne
	@JoinColumn(name="seq_achado_inspecao")
	private AchadoInspecao achadoInspecao;
	
	@ManyToOne
	@JoinColumn(name="seq_tipo_status_determinacao")
	private TipoStatusDeterminacao statusDeterminacao;

	@Lob
	@Column(name="dsc_determinacao_inspecao")
	private String dscDeterminacaoInspecao;

	@Lob
	@Column(name="dsc_resposta_tribunal ")
	private String dscRespostaTribunal ;

	@Column(name="dias_prazo")
	private int diasPrazo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_inicial_prazo")
	private Date datInicialPrazo;
	
	@Column(name="flg_cumprimento")
	private char flgCumprimento;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AchadoInspecao getAchadoInspecao() {
		return achadoInspecao;
	}

	public void setAchadoInspecao(AchadoInspecao achadoInspecao) {
		this.achadoInspecao = achadoInspecao;
	}

	public String getDscDeterminacaoInspecao() {
		return dscDeterminacaoInspecao;
	}

	public void setDscDeterminacaoInspecao(String dscDeterminacaoInspecao) {
		this.dscDeterminacaoInspecao = dscDeterminacaoInspecao;
	}

	public int getDiasPrazo() {
		return diasPrazo;
	}

	public void setDiasPrazo(int diasPrazo) {
		this.diasPrazo = diasPrazo;
	}

	public Date getDatInicialPrazo() {
		return datInicialPrazo;
	}

	public void setDatInicialPrazo(Date datInicialPrazo) {
		this.datInicialPrazo = datInicialPrazo;
	}

	public char getFlgCumprimento() {
		return flgCumprimento;
	}

	public void setFlgCumprimento(char flgCumprimento) {
		this.flgCumprimento = flgCumprimento;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public TipoStatusDeterminacao getStatusDeterminacao() {
		return statusDeterminacao;
	}

	public void setStatusDeterminacao(TipoStatusDeterminacao statusDeterminacao) {
		this.statusDeterminacao = statusDeterminacao;
	}

	public List<Documento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}

	public String getDscRespostaTribunal() {
		return dscRespostaTribunal;
	}

	public void setDscRespostaTribunal(String dscRespostaTribunal) {
		this.dscRespostaTribunal = dscRespostaTribunal;
	}
	
}
