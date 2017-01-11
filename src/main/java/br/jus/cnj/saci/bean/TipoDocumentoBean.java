package br.jus.cnj.saci.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.jus.cnj.saci.entity.TipoDocumento;
import br.jus.cnj.saci.entity.TipoObjetoAplicacao;
import br.jus.cnj.saci.service.TipoDocumentoService;

@ManagedBean(name = "tipoDocumentoBean")
@Component
@Scope(value = "session")
public class TipoDocumentoBean {
	
	private List<TipoDocumento> tipoDocumentoList = new ArrayList<TipoDocumento>();
	
	private TipoDocumento tipoDocumento = new TipoDocumento();
	
	@Autowired
	private TipoDocumentoService tipoDocumentoService;
	
	public String redirecionarPaginaListar() {
		return "/pages/tipoDocumento/listar.xhtml?faces-redirect=true";
	}
	
	public String redirecionarPaginaCadastro() {
		tipoDocumento = new TipoDocumento();
		return "/pages/tipoDocumento/cadastrar.xhtml?faces-redirect=true";
	}
	
	public String redirecionarPaginaAlterar() {
		return "/pages/tipoDocumento/alterar.xhtml?faces-redirect=true";
	}
	
	public List<TipoDocumento> getAll() {
		try {
			tipoDocumentoList = tipoDocumentoService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoDocumentoList;
	}

	public String cadastarTipoDocumento() {
		try {
			if (!tipoDocumentoService.persistirEntidade(tipoDocumento)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"O Tipo de Documento informado \""+tipoDocumento.getDscTipoDocumento()+"\" já está cadastrado.", "ERRO"));
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		tipoDocumento = new TipoDocumento();
		return "/pages/tipoDocumento/listar.xhtml?faces-redirect=true";
	}
	
	public String alterarTipoObjetoAplicacao() {
		try {
			if (!tipoDocumentoService.updateEntidade(tipoDocumento)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"O Tipo de Documento informado \""+tipoDocumento.getDscTipoDocumento()+"\" já está cadastrado.", "ERRO"));
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		tipoDocumento = new TipoDocumento();
		return "/pages/tipoDocumento/listar.xhtml?faces-redirect=true";
	}
	
	public String excluirTipoDocumento() {
		try {
			tipoDocumentoService.excluirEntidade(tipoDocumento);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/pages/tipoDocumento/listar.xhtml?faces-redirect=true";
	}

	public void setTipoDocumentoList(List<TipoDocumento> tipoDocumentoList) {
		this.tipoDocumentoList = tipoDocumentoList;
	}
	
	public List<TipoDocumento> getTipoDocumentoList() {
		return tipoDocumentoList;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	
	
}
