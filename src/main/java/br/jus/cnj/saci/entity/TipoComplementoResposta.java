package br.jus.cnj.saci.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tipo_complemento_resposta", schema="saci")
public class TipoComplementoResposta extends BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="seq_tipo_complemento_resposta")
	private Integer id;

	@Column(name="dsc_tipo_complemento_resposta")
	private String dscTipoComplementoResposta;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDscTipoComplementoResposta() {
		return dscTipoComplementoResposta;
	}

	public void setDscTipoComplementoResposta(String dscTipoComplementoResposta) {
		this.dscTipoComplementoResposta = dscTipoComplementoResposta;
	}

}
