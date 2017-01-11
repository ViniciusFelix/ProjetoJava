package br.jus.cnj.saci.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import br.jus.cnj.saci.entity.TipoAplicacaoPergunta;
import br.jus.cnj.saci.entity.TipoObjetoAplicacao;
import br.jus.cnj.saci.service.TipoAplicacaoPerguntaService;
import br.jus.cnj.saci.service.TipoObjetoAplicacaoService;
import br.jus.cnj.utils.exception.ServiceException;

@ManagedBean(name = "tipoAplicacaoPerguntaBean")
@Component
@Scope(value = "session")
public class TipoAplicacaoPerguntaBean {

	private TipoAplicacaoPergunta tipoAplicacaoPergunta = new TipoAplicacaoPergunta();
	private List<TipoAplicacaoPergunta> tipoAplicacaoPerguntaList = new ArrayList<TipoAplicacaoPergunta>();
	
	private Collection<String> listTipoObjetoAplicacao;
	
	private Collection<TipoObjetoAplicacao> listTipoObjetoAplicacaoObject;

	@Autowired
	private TipoAplicacaoPerguntaService tipoAplicacaoPerguntaService;
	
	@Autowired
	private TipoObjetoAplicacaoService tipoObjetoAplicacaoService;

	public TipoAplicacaoPerguntaBean()  {
		tipoAplicacaoPergunta = new TipoAplicacaoPergunta();
		listTipoObjetoAplicacaoObject = new ArrayList<TipoObjetoAplicacao>();
		tipoAplicacaoPergunta.setTipoObjetoAplicacao(listTipoObjetoAplicacaoObject);
	}
	
	public String redirecionarPaginaCadastro() {
		tipoAplicacaoPergunta = new TipoAplicacaoPergunta();
		listTipoObjetoAplicacaoObject = new ArrayList<TipoObjetoAplicacao>();
		tipoAplicacaoPergunta.setTipoObjetoAplicacao(listTipoObjetoAplicacaoObject);
		return "/pages/tipoAplicacaoPergunta/cadastrar.xhtml?faces-redirect=true";
	}
	
	public String redirecionarPaginaListar() {
		tipoAplicacaoPergunta = new TipoAplicacaoPergunta();
		listTipoObjetoAplicacaoObject = new ArrayList<TipoObjetoAplicacao>();
		tipoAplicacaoPergunta.setTipoObjetoAplicacao(listTipoObjetoAplicacaoObject);
		return "/pages/tipoAplicacaoPergunta/listar.xhtml?faces-redirect=true";
	}

	public String redirecionarPaginaAlterar() {
		listTipoObjetoAplicacao = new ArrayList<String>();
		listTipoObjetoAplicacaoObject = tipoAplicacaoPergunta.getTipoObjetoAplicacao();
		for (TipoObjetoAplicacao taaa : listTipoObjetoAplicacaoObject) {
			listTipoObjetoAplicacao.add(Integer.toString(taaa.getId()));
		}		
		return "/pages/tipoAplicacaoPergunta/alterar.xhtml?faces-redirect=true";
	}

	public List<TipoAplicacaoPergunta> getAll() {
		try {
			tipoAplicacaoPerguntaList = tipoAplicacaoPerguntaService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoAplicacaoPerguntaList;
	}
	
	public String cadastarTipoAplicacaoPergunta() {
		try {			
			tipoAplicacaoPergunta.setTipoObjetoAplicacao(tipoObjetoAplicacaoService.convertIdListToObjectList(listTipoObjetoAplicacao));
			if (!tipoAplicacaoPerguntaService.persistirEntidade(tipoAplicacaoPergunta)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"O Tipo de Ata informado \""+tipoAplicacaoPergunta.getDscTipoAplicacao()+"\" já está cadastrado.", "ERRO"));
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/pages/tipoAplicacaoPergunta/listar.xhtml?faces-redirect=true";
	}

	public String alterarTipoAplicacaoPergunta() {
		try {
			tipoAplicacaoPergunta.setTipoObjetoAplicacao(tipoObjetoAplicacaoService.convertIdListToObjectList(listTipoObjetoAplicacao));
			if (!tipoAplicacaoPerguntaService.updateEntidade(tipoAplicacaoPergunta)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"O Tipo de Ata informado \""+tipoAplicacaoPergunta.getDscTipoAplicacao()+"\" já está cadastrado.", "ERRO"));
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/pages/tipoAplicacaoPergunta/listar.xhtml?faces-redirect=true";
	}

	public String excluirTipoAplicacaoPergunta() {
		try {
			tipoAplicacaoPerguntaService.excluirEntidade(tipoAplicacaoPergunta);
		} catch (DataIntegrityViolationException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Não foi possível efetuar a exclusão. Existem perguntas associadas a este Tipo de Ata.", "ERRO"));
			return "";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/pages/tipoAplicacaoPergunta/listar.xhtml?faces-redirect=true";
	}

	public TipoAplicacaoPergunta getTipoAplicacaoPergunta() {
		return tipoAplicacaoPergunta;
	}

	public void setTipoAplicacaoPergunta(TipoAplicacaoPergunta tipoAplicacaoPergunta) {
		this.tipoAplicacaoPergunta = tipoAplicacaoPergunta;
	}

	public List<TipoAplicacaoPergunta> getTipoAplicacaoPerguntaList() {
		return tipoAplicacaoPerguntaList;
	}

	public void setTipoAplicacaoPerguntaList(List<TipoAplicacaoPergunta> tipoModeloBasicoList) {
		this.tipoAplicacaoPerguntaList = tipoModeloBasicoList;
	}

	public Collection<String> getListTipoObjetoAplicacao() {
		return listTipoObjetoAplicacao;
	}

	public void setListTipoObjetoAplicacao(Collection<String> listTipoObjetoAplicacao) {
		this.listTipoObjetoAplicacao = listTipoObjetoAplicacao;
	}

	public Collection<TipoObjetoAplicacao> getListTipoObjetoAplicacaoObject() {
		return listTipoObjetoAplicacaoObject;
	}

	public void setListTipoObjetoAplicacaoObject(
			Collection<TipoObjetoAplicacao> listTipoObjetoAplicacaoObject) {
		this.listTipoObjetoAplicacaoObject = listTipoObjetoAplicacaoObject;
	}
}
