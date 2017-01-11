package br.jus.cnj.saci.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import br.jus.cnj.saci.entity.TipoAplicacaoPergunta;
import br.jus.cnj.saci.entity.TipoFormatoResposta;
import br.jus.cnj.saci.service.TipoFormatoRespostaService;

@ManagedBean(name = "tipoFormatoRespostaBean")
@Component
@Scope(value = "session")
public class TipoFormatoRespostaBean {

	private TipoFormatoResposta tipoFormatoResposta = new TipoFormatoResposta();
	private List<TipoFormatoResposta> tipoFormatoRespostaList = new ArrayList<TipoFormatoResposta>();

	@Autowired
	private TipoFormatoRespostaService tipoFormatoRespostaService;

	public String redirecionarPaginaCadastro() {
		tipoFormatoResposta = new TipoFormatoResposta();
		return "/pages/tipoFormatoResposta/cadastrar.xhtml?faces-redirect=true";
	}
	
	public String redirecionarPaginaListar() {
		tipoFormatoResposta = new TipoFormatoResposta();
		return "/pages/tipoFormatoResposta/listar.xhtml?faces-redirect=true";
	}

	public String redirecionarPaginaAlterar() {
		return "/pages/tipoFormatoResposta/alterar.xhtml?faces-redirect=true";
	}

	public List<TipoFormatoResposta> getAll() {
		try {
			tipoFormatoRespostaList = tipoFormatoRespostaService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoFormatoRespostaList;
	}

	public String cadastarTipoFormatoResposta() {
		try {
			if (!tipoFormatoRespostaService.persistirEntidade(tipoFormatoResposta)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"O Tipo de Resposta informado \""+tipoFormatoResposta.getDscTipoResposta()+"\" já está cadastrado.", "ERRO"));
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/pages/tipoFormatoResposta/listar.xhtml?faces-redirect=true";
		
	}

	public String alterarTipoFormatoResposta() {
		try {
			if (!tipoFormatoRespostaService.updateEntidade(tipoFormatoResposta)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"O Tipo de Resposta informado \""+tipoFormatoResposta.getDscTipoResposta()+"\" já está cadastrado.", "ERRO"));
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/pages/tipoFormatoResposta/listar.xhtml?faces-redirect=true";
	}

	public String excluirTipoFormatoResposta() {
		try {
			tipoFormatoRespostaService.excluirEntidade(tipoFormatoResposta);
		} catch (DataIntegrityViolationException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Não foi possível efetuar a exclusão. Existem perguntas associadas a este Tipo de Resposta.", "ERRO"));
			return "";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/pages/tipoFormatoResposta/listar.xhtml?faces-redirect=true";
	}

	public TipoFormatoResposta getTipoFormatoResposta() {
		return tipoFormatoResposta;
	}

	public void setTipoFormatoResposta(TipoFormatoResposta tipoFormatoResposta) {
		this.tipoFormatoResposta = tipoFormatoResposta;
	}

	public List<TipoFormatoResposta> getTipoFormatoRespostaList() {
		return tipoFormatoRespostaList;
	}

	public void setTipoFormatoRespostaList(List<TipoFormatoResposta> tipoFormatoRespostaList) {
		this.tipoFormatoRespostaList = tipoFormatoRespostaList;
	}


}
