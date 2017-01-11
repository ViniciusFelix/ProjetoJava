package br.jus.cnj.corregedoria.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.jus.cnj.corporativo.entity.Cidade;
import br.jus.cnj.saci.entity.BaseEntity;
import br.jus.cnj.saci.entity.Deliberacao;

@Entity
@Table(name="serventia_extrajudicial", schema="serventias_extrajudiciais")
public class Cartorio extends BaseEntity<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="SEQ_SERVENTIA_EXTRAJUDICIAL")
	private Integer id;

	@Column(name="DSC_DENOMINACAO_SERVENTIA")
	private String denominacaoCartorio;

	@ManyToOne
	@JoinColumn(name="ID_CIDADE")
	private Cidade cidade;
	
	@Transient
	private List<Deliberacao> deliberacoes;
	

	public List<Deliberacao> getDeliberacoes() {
		return deliberacoes;
	}

	public void setDeliberacoes(List<Deliberacao> deliberacoes) {
		this.deliberacoes = deliberacoes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDenominacaoCartorio() {
		return denominacaoCartorio;
	}

	public void setDenominacaoCartorio(String denominacaoCartorio) {
		this.denominacaoCartorio = denominacaoCartorio;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	@Override
	public String toString(){
		return denominacaoCartorio;
	}
}
