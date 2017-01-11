package br.jus.cnj.saci.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="tipo_objeto_aplicacao", schema="saci")
public class TipoObjetoAplicacao extends BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="seq_tipo_objeto_aplicacao")
	private Integer id;

	@Column(name="dsc_tipo_objeto_aplicacao")
	private String dscTipoObjetoAplicacao;

	@Column(name="num_ordem")
	private int numOrdem;
	
	@ManyToMany(mappedBy="tipoObjetoAplicacao")
	private Collection<TipoAplicacaoPergunta> tipoAplicacaoPergunta;

	@Column(name="flg_ativo")
	private char flgAtivo;
	
	public char getFlagAtivo() {
		return flgAtivo;
	}
	
	public void setFlagAtivo(char flgAtivo) {
		this.flgAtivo = flgAtivo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDscTipoObjetoAplicacao() {
		return dscTipoObjetoAplicacao;
	}

	public void setDscTipoObjetoAplicacao(String dscTipoObjetoAplicacao) {
		this.dscTipoObjetoAplicacao = dscTipoObjetoAplicacao;
	}

	public int getNumOrdem() {
		return numOrdem;
	}

	public void setNumOrdem(int numOrdem) {
		this.numOrdem = numOrdem;
	}
	
	public Collection<TipoAplicacaoPergunta> getTipoAplicacaoPergunta() {
		return tipoAplicacaoPergunta;
	}

	public void setTipoAplicacaoPergunta(
			Collection<TipoAplicacaoPergunta> tipoAplicacaoPergunta) {
		this.tipoAplicacaoPergunta = tipoAplicacaoPergunta;
	}
	
	@Override
	public String toString(){
		return dscTipoObjetoAplicacao;
	}
	

}
