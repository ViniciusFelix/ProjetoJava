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

import br.jus.cnj.saci.entity.TipoComplementoResposta;
import br.jus.cnj.saci.service.TipoComplementoRespostaService;

@ManagedBean(name = "tipoComplementoRespostaBean")
@Component
@Scope(value = "session")
public class TipoComplementoRespostaBean {

	private List<TipoComplementoResposta> tipoComplementoRespostaList = new ArrayList<TipoComplementoResposta>();
	
	private TipoComplementoResposta tipoComplementoResposta = new TipoComplementoResposta();
	
	@Autowired
	private TipoComplementoRespostaService tipoComplementoRespostaService;
	
	public String redirecionarPaginaListar() {
		return "/pages/tipoComplementoResposta/listar.xhtml?faces-redirect=true";
	}
	
	public String redirecionarPaginaCadastro() {
		tipoComplementoResposta = new TipoComplementoResposta();
		return "/pages/tipoComplementoResposta/cadastrar.xhtml?faces-redirect=true";
	}
	
	public String redirecionarPaginaAlterar() {
		return "/pages/tipoComplementoResposta/alterar.xhtml?faces-redirect=true";
	}
	
	public List<TipoComplementoResposta> getAll() {
		try {
			tipoComplementoRespostaList = tipoComplementoRespostaService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoComplementoRespostaList;
	}

	public String cadastarTipoComplementoResposta() {
		try {
			if (!tipoComplementoRespostaService.persistirEntidade(tipoComplementoResposta)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"O Tipo de complemento de resposta informado \""+tipoComplementoResposta.getDscTipoComplementoResposta()+"\" j� est� cadastrado.", "ERRO"));
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		tipoComplementoResposta = new TipoComplementoResposta(); 

		return "/pages/tipoComplementoResposta/listar.xhtml?faces-redirect=true";
	}
	
	public String alterarTipoObjetoAplicacao() {
		try {
			if (!tipoComplementoRespostaService.updateEntidade(tipoComplementoResposta)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"O Tipo de complemento de resposta informado \""+tipoComplementoResposta.getDscTipoComplementoResposta()+"\" j� est� cadastrado.", "ERRO"));
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		tipoComplementoResposta = new TipoComplementoResposta();
		return "/pages/tipoComplementoResposta/listar.xhtml?faces-redirect=true";
	}
	
	public String excluirTipoComplementoResposta() {
		try {
			tipoComplementoRespostaService.excluirEntidade(tipoComplementoResposta);
		} catch (DataIntegrityViolationException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"N�o foi poss�vel efetuar a exclus�o.", "ERRO"));
			return "";
		} catch (Exception e) {
			e.printStackTrace();
		}


		return "/pages/tipoComplementoResposta/listar.xhtml?faces-redirect=true";
	}

	public void setTipoComplementoRespostaList(List<TipoComplementoResposta> tipoComplementoRespostaList) {
		this.tipoComplementoRespostaList = tipoComplementoRespostaList;
	}
	
	public List<TipoComplementoResposta> getTipoComplementoRespostaList() {
		return tipoComplementoRespostaList;
	}

	public TipoComplementoResposta getTipoComplementoResposta() {
		return tipoComplementoResposta;
	}

	public void setTipoComplementoResposta(TipoComplementoResposta tipoComplementoResposta) {
		this.tipoComplementoResposta = tipoComplementoResposta;
	}

}
