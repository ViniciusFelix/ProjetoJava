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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="inspecao", schema="saci")
public class Inspecao extends BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="seq_inspecao")
	private Integer id;

	@Column(name="num_inspecao")
	private int numInspecao;
	
	@Column(name="num_processo_pje")
	private String numProcessoPje;

	@Column(name="num_ano_inspecao")
	private int numAnoInspecao;

	@Column(name="num_portaria")
	private String numPortaria;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dat_inicio_inspecao")
	private Date datInicioInspecao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dat_final_inspecao")
	private Date datFinalInspecao;

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

	@Column(name="tip_inspecao")
	private char tipoInspecao;

	@Lob
	@Column(name="dsc_assunto")
	private String dscAssunto;

	@Lob
	@Column(name="dsc_introducao")
	private String dscIntroducao;

	@Lob
	@Column(name="dsc_conclusao")
	private String dscConclusao;
	
	@Column(name="flg_inspecao_publicada")
	private char flgInspecaoPublicada;
	
	@OneToMany(mappedBy="inspecao", orphanRemoval=true)
	@Cascade(CascadeType.ALL)
	private List<InspecaoOrgao> orgaos;

	@ManyToMany
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinTable(name = "documento_inspecao", joinColumns = { @JoinColumn(name = "seq_inspecao") }, inverseJoinColumns = { @JoinColumn(name = "seq_documento") })
    private List<Documento> documentos;
	
	
	@OneToMany(mappedBy="inspecao", orphanRemoval=true)
	@Cascade(CascadeType.ALL)
	private List<InspecaoObjetoAplicacao> inspecaoObjetoAplicacao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dat_publicacao_inspecao")
	private Date datPublicacaoInspecao;

	public Date getDatPublicacaoInspecao() {
		return datPublicacaoInspecao;
	}

	public void setDatPublicacaoInspecao(Date datPublicacaoInspecao) {
		this.datPublicacaoInspecao = datPublicacaoInspecao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getNumInspecao() {
		return numInspecao;
	}

	public void setNumInspecao(int numInspecao) {
		this.numInspecao = numInspecao;
	}
	
	public String getNumProcessoPje() {
		return numProcessoPje;
	}

	public void setNumProcessoPje(String numProcessoPje) {
		this.numProcessoPje = numProcessoPje;
	}

	public int getNumAnoInspecao() {
		return numAnoInspecao;
	}

	public void setNumAnoInspecao(int numAnoInspecao) {
		this.numAnoInspecao = numAnoInspecao;
	}

	public String getNumPortaria() {
		return numPortaria;
	}

	public void setNumPortaria(String numPortaria) {
		this.numPortaria = numPortaria;
	}

	public Date getDatInicioInspecao() {
		return datInicioInspecao;
	}

	public void setDatInicioInspecao(Date datInicioInspecao) {
		this.datInicioInspecao = datInicioInspecao;
	}

	public Date getDatFinalInspecao() {
		return datFinalInspecao;
	}

	public void setDatFinalInspecao(Date datFinalInspecao) {
		this.datFinalInspecao = datFinalInspecao;
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

	public char getTipoInspecao() {
		return tipoInspecao;
	}

	public void setTipoInspecao(char tipoInspecao) {
		this.tipoInspecao = tipoInspecao;
	}

	public String getdscAssunto() {
		return dscAssunto;
	}

	public void setdscAssunto(String dscAssunto) {
		this.dscAssunto = dscAssunto;
	}

	public List<InspecaoOrgao> getOrgaos() {
		return orgaos;
	}

	public void setOrgaos(List<InspecaoOrgao> orgaos) {
		this.orgaos = orgaos;
	}
	
	public List<Documento> getDocumentos() {
		return documentos;
	}

	public List<InspecaoObjetoAplicacao> getInspecaoObjetoAplicacao() {
		return inspecaoObjetoAplicacao;
	}

	public void setInspecaoObjetoAplicacao(List<InspecaoObjetoAplicacao> inspecaoObjetoAplicacao) {
		this.inspecaoObjetoAplicacao = inspecaoObjetoAplicacao;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}

	public char getFlgInspecaoPublicada() {
		return flgInspecaoPublicada;
	}

	public void setFlgInspecaoPublicada(char flgInspecaoPublicada) {
		this.flgInspecaoPublicada = flgInspecaoPublicada;
	}


	public String getDscIntroducao() {
		return dscIntroducao;
	}

	public void setDscIntroducao(String dscIntroducao) {
		this.dscIntroducao = dscIntroducao;
	}

	public String getDscConclusao() {
		return dscConclusao;
	}

	public void setDscConclusao(String dscConclusao) {
		this.dscConclusao = dscConclusao;
	}
}
