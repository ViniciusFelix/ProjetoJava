package br.jus.cnj.saci.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="documento", schema="saci")
public class Documento extends BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="seq_documento")
	private Integer id;

	@Column(name="nom_arquivo")
	private String nomeArquivo;

	@Column(name="dsc_url_arquivo")
	private String urlArquivo;
	
	@ManyToMany(mappedBy = "documentos")
    private Collection<Inspecao> inspecao;
	
	@ManyToMany(mappedBy = "documentos")
    private Collection<AchadoInspecao> achados;
    
	@ManyToMany(mappedBy = "documentos")
    private Collection<DeterminacaoInspecao> determinacoes;
    
	public Collection<AchadoInspecao> getAchados() {
		return achados;
	}

	public void setAchados(Collection<AchadoInspecao> achados) {
		this.achados = achados;
	}

	public Collection<DeterminacaoInspecao> getDeterminacoes() {
		return determinacoes;
	}

	public void setDeterminacoes(Collection<DeterminacaoInspecao> determinacoes) {
		this.determinacoes = determinacoes;
	}

	@ManyToOne
	@JoinColumn(name="seq_tipo_documento")
	private TipoDocumento tipoDocumento;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public String getUrlArquivo() {
		return urlArquivo;
	}

	public void setUrlArquivo(String urlArquivo) {
		this.urlArquivo = urlArquivo;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	

}
