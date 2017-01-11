package br.jus.cnj.saci.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.jus.cnj.saci.entity.TipoDeliberacao;
import br.jus.cnj.saci.entity.TipoDocumento;
import br.jus.cnj.saci.service.TipoDeliberacaoService;

@ManagedBean(name = "tipoDeliberacaoBean")
@Component
@Scope(value = "session")
public class TipoDeliberacaoBean {
	
	private List<TipoDeliberacao> tipoDeliberacaoList = new ArrayList<TipoDeliberacao>();
	
	private TipoDeliberacao tipoDeliberacao = new TipoDeliberacao();
	
	@Autowired
	private TipoDeliberacaoService tipoDeliberacaoService;
	
	public String redirecionarPaginaListar() {
		return "/pages/tipoDeliberacao/listar.xhtml?faces-redirect=true";
	}
	
	public String redirecionarPaginaCadastro() {
		tipoDeliberacao = new TipoDeliberacao();
		return "/pages/tipoDeliberacao/cadastrar.xhtml?faces-redirect=true";
	}
	
	public String redirecionarPaginaAlterar() {
		return "/pages/tipoDeliberacao/alterar.xhtml?faces-redirect=true";
	}
	
	public List<TipoDeliberacao> getAll() {
		try {
			tipoDeliberacaoList = tipoDeliberacaoService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoDeliberacaoList;
	}

	public String cadastarTipoDeliberacao() {
		try {
			if (!tipoDeliberacaoService.persistirEntidade(tipoDeliberacao)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"O Tipo de Documento informado \""+tipoDeliberacao.getDscTipoDeliberacao()+"\" já está cadastrado.", "ERRO"));
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		tipoDeliberacao = new TipoDeliberacao();
		return "/pages/tipoDeliberacao/listar.xhtml?faces-redirect=true";
	}
	
	public String alterarTipoObjetoAplicacao() {
		try {
			if (!tipoDeliberacaoService.updateEntidade(tipoDeliberacao)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"O Tipo de Documento informado \""+tipoDeliberacao.getDscTipoDeliberacao()+"\" já está cadastrado.", "ERRO"));
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		tipoDeliberacao = new TipoDeliberacao();
		return "/pages/tipoDeliberacao/listar.xhtml?faces-redirect=true";
	}
	
	public String excluirTipoDeliberacao() {
		try {
			tipoDeliberacaoService.excluirEntidade(tipoDeliberacao);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/pages/tipoDeliberacao/listar.xhtml?faces-redirect=true";
	}

	public List<TipoDeliberacao> getTipoDeliberacaoList() {
		return tipoDeliberacaoList;
	}

	public void setTipoDeliberacaoList(List<TipoDeliberacao> tipoDeliberacaoList) {
		this.tipoDeliberacaoList = tipoDeliberacaoList;
	}

	public TipoDeliberacao getTipoDeliberacao() {
		return tipoDeliberacao;
	}

	public void setTipoDeliberacao(TipoDeliberacao tipoDeliberacao) {
		this.tipoDeliberacao = tipoDeliberacao;
	}

}
