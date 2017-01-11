package br.jus.cnj.saci.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tipo_documento", schema="saci")
public class TipoDocumento extends BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="seq_tipo_documento")
	private Integer id;

	@Column(name="dsc_tipo_documento")
	private String dscTipoDocumento;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDscTipoDocumento() {
		return dscTipoDocumento;
	}

	public void setDscTipoDocumento(String dscTipoDocumento) {
		this.dscTipoDocumento = dscTipoDocumento;
	}
	
}
