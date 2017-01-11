package br.jus.cnj.saci.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.jus.cnj.saci.entity.TipoAplicacaoPergunta;
import br.jus.cnj.saci.entity.TipoObjetoAplicacao;
import br.jus.cnj.saci.service.TipoAplicacaoPerguntaService;
import br.jus.cnj.saci.service.TipoObjetoAplicacaoService;

@ManagedBean(name = "tipoObjetoAplicacaoBean")
@Component
@Scope(value = "session")
public class TipoObjetoAplicacaoBean {
	
	private TipoObjetoAplicacao tipoObjetoAplicacao = new TipoObjetoAplicacao();
	
	private List<TipoObjetoAplicacao> tipoObjetoAplicacaoList = new ArrayList<TipoObjetoAplicacao>();

	@Autowired
	private TipoObjetoAplicacaoService tipoObjetoAplicacaoService;

	public TipoObjetoAplicacaoBean() {
		// tipoObjetoAplicacao.setTipoAplicacaoPergunta(new
		// TipoAplicacaoPergunta());
		// tipoObjetoAplicacao.setTipoFormatoResposta(new
		// TipoFormatoResposta());
	}

	public String redirecionarPaginaCadastro() {
		tipoObjetoAplicacao = new TipoObjetoAplicacao();
		// tipoObjetoAplicacao.setTipoAplicacaoPergunta(new
		// TipoAplicacaoPergunta());
		// tipoObjetoAplicacao.setTipoFormatoResposta(new
		// TipoFormatoResposta());
		return "/pages/tipoObjetoAplicacao/cadastrar.xhtml?faces-redirect=true";
	}

	public String redirecionarPaginaListar() {
		tipoObjetoAplicacao = new TipoObjetoAplicacao();
		// tipoObjetoAplicacao.setTipoAplicacaoPergunta(new
		// TipoAplicacaoPergunta());
		// tipoObjetoAplicacao.setTipoFormatoResposta(new
		// TipoFormatoResposta());
		return "/pages/tipoObjetoAplicacao/listar.xhtml?faces-redirect=true";
	}

	public String redirecionarPaginaAlterar() {
		return "/pages/tipoObjetoAplicacao/alterar.xhtml?faces-redirect=true";
	}

	public List<TipoObjetoAplicacao> getAll() {
		try {
			tipoObjetoAplicacaoList = tipoObjetoAplicacaoService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoObjetoAplicacaoList;
	}

	public String cadastarTipoObjetoAplicacao() {
		try {
			if (!tipoObjetoAplicacaoService
					.persistirEntidade(tipoObjetoAplicacao)) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"O Tipo de Objeto informado \""
										+ tipoObjetoAplicacao
												.getDscTipoObjetoAplicacao()
										+ "\" jï¿½ estï¿½ cadastrado.", "ERRO"));
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		tipoObjetoAplicacao = new TipoObjetoAplicacao();
		// tipoObjetoAplicacao.setTipoAplicacaoPergunta(new
		// TipoAplicacaoPergunta());
		// tipoObjetoAplicacao.setTipoFormatoResposta(new
		// TipoFormatoResposta());
		return "/pages/tipoObjetoAplicacao/listar.xhtml?faces-redirect=true";
	}

	public String alterarTipoObjetoAplicacao() {
		try {
			if (!tipoObjetoAplicacaoService.updateEntidade(tipoObjetoAplicacao)) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"O Tipo de Objeto informado \""
										+ tipoObjetoAplicacao
												.getDscTipoObjetoAplicacao()
										+ "\" jï¿½ estï¿½ cadastrado.", "ERRO"));
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		tipoObjetoAplicacao = new TipoObjetoAplicacao();
		// tipoObjetoAplicacao.setTipoAplicacaoPergunta(new
		// TipoAplicacaoPergunta());
		// tipoObjetoAplicacao.setTipoFormatoResposta(new
		// TipoFormatoResposta());
		return "/pages/tipoObjetoAplicacao/listar.xhtml?faces-redirect=true";
	}

	public String excluirTipoObjetoAplicacao() {
		try {
			tipoObjetoAplicacaoService.excluirEntidade(tipoObjetoAplicacao);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/pages/tipoObjetoAplicacao/listar.xhtml?faces-redirect=true";
	}

	public TipoObjetoAplicacao getTipoObjetoAplicacao() {
		return tipoObjetoAplicacao;
	}

	public void setTipoObjetoAplicacao(TipoObjetoAplicacao tipoObjetoAplicacao) {
		this.tipoObjetoAplicacao = tipoObjetoAplicacao;
	}

	public List<TipoObjetoAplicacao> getTipoObjetoAplicacaoList() {
		return tipoObjetoAplicacaoList;
	}

	public void setTipoObjetoAplicacaoList(
			List<TipoObjetoAplicacao> tipoAplicacaoPergundaList) {
		this.tipoObjetoAplicacaoList = tipoAplicacaoPergundaList;
	}

}
