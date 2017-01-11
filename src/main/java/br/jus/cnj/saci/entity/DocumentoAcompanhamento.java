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
@Table(name="documento_acompanhamento", schema="saci")
public class DocumentoAcompanhamento extends BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="seq_documento_acompanhamento")
	private Integer id;

	@ManyToOne
	@JoinColumn(name="seq_acompanhamento_deliberacao")
	private AcompanhamentoDeliberacao acompanhamentoDeliberacao;
	
	@Column(name="dsc_url_arquivo")
	private String dscUrlArquivo;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AcompanhamentoDeliberacao getAcompanhamentoDeliberacao() {
		return acompanhamentoDeliberacao;
	}

	public void setAcompanhamentoDeliberacao(
			AcompanhamentoDeliberacao acompanhamentoDeliberacao) {
		this.acompanhamentoDeliberacao = acompanhamentoDeliberacao;
	}

	public String getDscUrlArquivo() {
		return dscUrlArquivo;
	}

	public void setDscUrlArquivo(String dscUrlArquivo) {
		this.dscUrlArquivo = dscUrlArquivo;
	}
	

}
