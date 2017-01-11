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
@Table(name="achado_inspecao", schema="saci")
public class AchadoInspecao extends BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="seq_achado_inspecao")
	private Integer id;

	@Lob
	@Column(name="dsc_achado")
	private String dscAchado;

	@ManyToMany
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinTable(name = "documento_achado_inspecao", joinColumns = { @JoinColumn(name = "seq_achado_inspecao") }, inverseJoinColumns = { @JoinColumn(name = "seq_documento") })
    private Collection<Documento> documentos;	
	
	@Column(name="flg_achado_bloqueado")
	private char flgAchadoBloqueado;

	@ManyToOne
	@JoinColumn(name="seq_inspecao")
	private Inspecao inspecao;
	
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
		
	@OneToMany(mappedBy="achadoInspecao", orphanRemoval=true)
	private List<DeterminacaoInspecao> determinacoes;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDscAchado() {
		return dscAchado;
	}

	public void setDscAchado(String dscAchado) {
		this.dscAchado = dscAchado;
	}

	public Collection<Documento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(Collection<Documento> documentos) {
		this.documentos = documentos;
	}


	public char getFlgAchadoBloqueado() {
		return flgAchadoBloqueado;
	}

	public void setFlgAchadoBloqueado(char flgAchadoBloqueado) {
		this.flgAchadoBloqueado = flgAchadoBloqueado;
	}

	public Inspecao getInspecao() {
		return inspecao;
	}

	public void setInspecao(Inspecao inspecao) {
		this.inspecao = inspecao;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<DeterminacaoInspecao> getDeterminacoes() {
		return determinacoes;
	}

	public void setDeterminacoes(List<DeterminacaoInspecao> determinacoes) {
		this.determinacoes = determinacoes;
	}


}
