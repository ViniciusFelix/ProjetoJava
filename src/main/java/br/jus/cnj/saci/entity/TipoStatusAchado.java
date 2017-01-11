package br.jus.cnj.saci.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tipo_status_achado", schema="saci")
public class TipoStatusAchado extends BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="seq_tipo_status_achado")
	private Integer id;

	@Column(name="dsc_tipo_status_achado")
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
