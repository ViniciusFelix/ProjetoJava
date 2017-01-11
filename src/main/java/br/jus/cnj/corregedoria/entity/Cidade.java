package br.jus.cnj.corregedoria.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.jus.cnj.saci.entity.BaseEntity;

@Entity
@Table(name="TB_CIDADE", schema="COMPARTILHADO")
public class Cidade extends BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_CIDADE")
	private Integer id;

	@Column(name="CO_UF")
	private String uf;

	@Column(name="DS_CIDADE")
	private String dscCidade;
	
	@OneToMany(mappedBy="cidade")
	private List<Cartorio> cartorios;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getDscCidade() {
		return dscCidade;
	}

	public void setDscCidade(String dscCidade) {
		this.dscCidade = dscCidade;
	}

	public List<Cartorio> getCartorios() {
		return cartorios;
	}

	public void setCartorios(List<Cartorio> cartorios) {
		this.cartorios = cartorios;
	}

	@Override
	public String toString(){
		return dscCidade;
	}

	

}
