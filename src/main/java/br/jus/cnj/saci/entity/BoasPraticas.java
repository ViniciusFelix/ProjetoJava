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
@Table(name="boas_praticas", schema="saci")
public class BoasPraticas extends BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="seq_boas_praticas")
	private Integer id;

	@ManyToOne
	@JoinColumn(name="seq_inspecao_orgao")
	private InspecaoOrgao inspecaoOrgao;
	
	@Lob
	@Column(name="dsc_boas_praticas")
	private String dscBoasPraticas;
	
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

	public InspecaoOrgao getInspecaoOrgao() {
		return inspecaoOrgao;
	}

	public void setInspecaoOrgao(InspecaoOrgao inspecaoOrgao) {
		this.inspecaoOrgao = inspecaoOrgao;
	}

	public String getDscBoasPraticas() {
		return dscBoasPraticas;
	}

	public void setDscBoasPraticas(String descricao) {
		this.dscBoasPraticas = descricao;
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
