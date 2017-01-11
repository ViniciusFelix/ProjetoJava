package br.jus.cnj.corporativo.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author fabio.pereira
 */
@Entity
@Table(name = "tipo_cargo", schema = "corporativo")
public class CorporativoTipoCargo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3758324666267486663L;

	@Id
	@Column(name = "SIG_TIPO_CARGO")
	private String id;

	@Column(name = "DSC_TIPO_CARGO")
	private String descricao;

	// @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoCargo", fetch =
	// FetchType.LAZY)
	// private List<CorporativoUsuario> usuarios;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	// public List<CorporativoUsuario> getUsuarios() {
	// return usuarios;
	// }
	//
	// public void setUsuarios(List<CorporativoUsuario> usuarios) {
	// this.usuarios = usuarios;
	// }

}