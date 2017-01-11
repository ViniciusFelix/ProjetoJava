package br.jus.cnj.saci.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="inspecao_objeto_aplicacao", schema="saci")
public class InspecaoObjetoAplicacao extends BaseEntity<Integer> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="seq_inspecao_objeto_aplicacao")
	private Integer id;

	@ManyToOne
	@JoinColumn(name="seq_tipo_objeto_aplicacao")
	private TipoObjetoAplicacao tipoObjetoAplicacao;
	
	@ManyToOne
	@JoinColumn(name="seq_inspecao")
	private Inspecao inspecao;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TipoObjetoAplicacao getTipoObjetoAplicacao() {
		return tipoObjetoAplicacao;
	}

	public void setTipoObjetoAplicacao(TipoObjetoAplicacao tipoObjetoAplicacao) {
		this.tipoObjetoAplicacao = tipoObjetoAplicacao;
	}

	public Inspecao getInspecao() {
		return inspecao;
	}

	public void setInspecao(Inspecao inspecao) {
		this.inspecao = inspecao;
	}

}
