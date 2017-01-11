package br.jus.cnj.saci.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tipo_status_determinacao", schema="saci")
public class TipoStatusDeterminacao extends BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="seq_tipo_status_determinacao")
	private Integer id;

	@Column(name="dsc_tipo_status_determinacao")
	private String dscTipoStatusAchado;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDscTipoStatusAchado() {
		return dscTipoStatusAchado;
	}

	public void setDscTipoStatusAchado(String dscTipoStatusAchado) {
		this.dscTipoStatusAchado = dscTipoStatusAchado;
	}
	
	@Override
	public String toString(){
		return dscTipoStatusAchado;
	}

}
