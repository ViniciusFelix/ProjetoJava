package br.jus.cnj.saci.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tipo_deliberacao", schema="saci")
public class TipoDeliberacao extends BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="seq_tipo_deliberacao")
	private Integer id;

	@Column(name="dsc_tipo_deliberacao")
	private String dscTipoDeliberacao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDscTipoDeliberacao() {
		return dscTipoDeliberacao;
	}

	public void setDscTipoDeliberacao(String dscTipoDeliberacao) {
		this.dscTipoDeliberacao = dscTipoDeliberacao;
	}

}
