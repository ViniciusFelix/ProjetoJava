package br.jus.cnj.saci.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="subunidade_orgao", schema="saci")
public class SubUnidadeOrgao extends BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="seq_subunidade_orgao")
	private Integer id;

	@Column(name="dsc_subunidade")
	private String dscSubunidade;
	
	@OneToMany(mappedBy="subUnidadeOrgao")
	@Cascade(CascadeType.SAVE_UPDATE)
	private List<InspecaoOrgao> orgaos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDscSubunidade() {
		return dscSubunidade;
	}

	public void setDscSubunidade(String dscSubunidade) {
		this.dscSubunidade = dscSubunidade;
	}

	public List<InspecaoOrgao> getOrgaos() {
		return orgaos;
	}

	public void setOrgaos(List<InspecaoOrgao> orgaos) {
		this.orgaos = orgaos;
	}	

}
