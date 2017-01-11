package br.jus.cnj.saci.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.ReorderEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.jus.cnj.saci.entity.ModeloPergunta;
import br.jus.cnj.saci.entity.Pergunta;
import br.jus.cnj.saci.entity.TipoAplicacaoPergunta;
import br.jus.cnj.saci.entity.TipoFormatoResposta;
import br.jus.cnj.saci.service.ModeloPerguntaService;
import br.jus.cnj.saci.service.TipoAplicacaoPerguntaService;
import br.jus.cnj.utils.exception.ServiceException;

@ManagedBean(name = "modeloPerguntaBean")
@Component
@Scope(value = "session")
public class ModeloPerguntaBean {


	private ModeloPergunta modeloPergunta = new ModeloPergunta();

	private List<ModeloPergunta> modeloPerguntaList = new ArrayList<ModeloPergunta>();

	@Autowired
	private ModeloPerguntaService modeloPerguntaService;

	@Autowired
	TipoAplicacaoPerguntaService tipoAplicacaoPerguntaService;
	
	TipoAplicacaoPergunta tap = new TipoAplicacaoPergunta();

	public TipoAplicacaoPergunta getTap() {
		return tap;
	}

	public void setTap(TipoAplicacaoPergunta tap) {
		this.tap = tap;
	}

	public ModeloPerguntaBean() {
		modeloPergunta.setTipoAplicacaoPergunta(new TipoAplicacaoPergunta());
		modeloPergunta.setTipoFormatoResposta(new TipoFormatoResposta());
	}

	public String redirecionarPaginaCadastro() {
		modeloPergunta = new ModeloPergunta();
		modeloPergunta.setTipoAplicacaoPergunta(new TipoAplicacaoPergunta());
		modeloPergunta.setTipoFormatoResposta(new TipoFormatoResposta());
		return "/pages/modeloPergunta/cadastrar.xhtml?faces-redirect=true";
	}

	public String redirecionarPaginaListar() {
		modeloPergunta = new ModeloPergunta();
		modeloPergunta.setTipoAplicacaoPergunta(new TipoAplicacaoPergunta());
		modeloPergunta.setTipoFormatoResposta(new TipoFormatoResposta());
		return "/pages/modeloPergunta/listar.xhtml?faces-redirect=true";
	}

	public String redirecionarPaginaAlterar() {
		return "/pages/modeloPergunta/alterar.xhtml?faces-redirect=true";
	}

	public List<ModeloPergunta> getAll() {
		try {
			modeloPerguntaList = modeloPerguntaService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modeloPerguntaList;
	}

	public List<ModeloPergunta> getAllByTipoAplicacaoPergunta() {
		try {
			modeloPerguntaList = null;
			if (modeloPergunta.getTipoAplicacaoPergunta() != null) {
				//int idTipoAplicacaoPergunta = modeloPergunta.getTipoAplicacaoPergunta().getId();
				//TipoAplicacaoPergunta tipoAplicacaoPergunta = tipoAplicacaoPerguntaService.pesquisarPorId(idTipoAplicacaoPergunta);
				modeloPerguntaList = modeloPerguntaService.pesquisarPorTipoAplicacaoPergunta(modeloPergunta.getTipoAplicacaoPergunta());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modeloPerguntaList;
	}

	public String cadastarModeloPergunta() {
		try {
			modeloPerguntaService.persistirEntidade(modeloPergunta);
		} catch (Exception e) {
			e.printStackTrace();
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Pergunta cadastrada com sucesso.", "SUCESSO"));
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		modeloPergunta.setDescricao("");
		return "/pages/modeloPergunta/cadastrar.xhtml?faces-redirect=true";
	}

	public String alterarModeloPergunta() {
		try {
			modeloPerguntaService.updateEntidade(modeloPergunta);
		} catch (Exception e) {
			e.printStackTrace();
		}
		modeloPergunta = new ModeloPergunta();
		modeloPergunta.setTipoAplicacaoPergunta(new TipoAplicacaoPergunta());
		modeloPergunta.setTipoFormatoResposta(new TipoFormatoResposta());
		return "/pages/modeloPergunta/listar.xhtml?faces-redirect=true";
	}

	public String excluirModeloPergunta() {
		try {
			modeloPerguntaService.excluirEntidade(modeloPergunta);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/pages/modeloPergunta/listar.xhtml?faces-redirect=true";
	}

	public String moverParaCima() {
		try {
			int numOrdemAtual = modeloPergunta.getNumOrdem();
			TipoAplicacaoPergunta tipoAplicacaoPergunta = modeloPergunta.getTipoAplicacaoPergunta();
			ModeloPergunta modeloPerguntaAux = modeloPerguntaService.pesquisarPorNumOrdem(numOrdemAtual - 1, tipoAplicacaoPergunta);
			modeloPergunta.setNumOrdem(numOrdemAtual - 1);
			modeloPerguntaAux.setNumOrdem(numOrdemAtual);
			modeloPerguntaService.updateEntidade(modeloPergunta);
			modeloPerguntaService.updateEntidade(modeloPerguntaAux);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public String moverParaBaixo() {
		try {
			int numOrdemAtual = modeloPergunta.getNumOrdem();
			TipoAplicacaoPergunta tipoAplicacaoPergunta = modeloPergunta.getTipoAplicacaoPergunta();
			ModeloPergunta modeloPerguntaAux = modeloPerguntaService.pesquisarPorNumOrdem(numOrdemAtual + 1, tipoAplicacaoPergunta);
			modeloPergunta.setNumOrdem(numOrdemAtual + 1);
			modeloPerguntaAux.setNumOrdem(numOrdemAtual);
			modeloPerguntaService.updateEntidade(modeloPergunta);
			modeloPerguntaService.updateEntidade(modeloPerguntaAux);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public ModeloPergunta getModeloPergunta() {
		return modeloPergunta;
	}

	public void setModeloPergunta(ModeloPergunta modeloPergunta) {
		this.modeloPergunta = modeloPergunta;
	}

	public List<ModeloPergunta> getModeloPerguntaList() {
		return modeloPerguntaList;
	}

	public void setModeloPerguntaList(List<ModeloPergunta> tipoAplicacaoPergundaList) {
		this.modeloPerguntaList = tipoAplicacaoPergundaList;
	}
	

	
	public void reordenarLinha(ReorderEvent event) throws ServiceException {
		int ini = event.getFromIndex() + 1;
		int fim = event.getToIndex() + 1;
				
		ModeloPergunta p = (ModeloPergunta) ((DataTable) event.getSource()).getRowData();
		tap = p.getTipoAplicacaoPergunta();	
		
		modeloPerguntaService.mudarOrdem(ini, fim, tap);
				
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Linha alterada com sucesso! ", " ");
        FacesContext.getCurrentInstance().addMessage(null, msg);
	}


}
