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

import br.jus.cnj.corporativo.bean.CorporativoOrgao;
import br.jus.cnj.corregedoria.entity.Cartorio;
import br.jus.cnj.saci.entity.InspecaoOrgao;
import br.jus.cnj.saci.entity.Pergunta;
import br.jus.cnj.saci.entity.Questionario;
import br.jus.cnj.saci.entity.TipoAplicacaoPergunta;
import br.jus.cnj.saci.entity.TipoFormatoResposta;
import br.jus.cnj.saci.service.InspecaoOrgaoService;
import br.jus.cnj.saci.service.PerguntaService;
import br.jus.cnj.saci.service.QuestionarioService;
import br.jus.cnj.utils.exception.ServiceException;

@ManagedBean(name = "questionarioBean")
@Component
@Scope(value = "session") 
public class QuestionarioBean {
	@Autowired
	private QuestionarioService questionarioService;
	
	@Autowired
	private PerguntaService perguntaService;
	
	@Autowired
	private InspecaoOrgaoService inspecaoOrgaoService;
	
	private Pergunta pergunta = new Pergunta();
	private Pergunta novaPergunta = new Pergunta();
	
	private InspecaoOrgao inspecao = new InspecaoOrgao();
	
	
	public InspecaoOrgao getInspecao() {
		return inspecao;
	}

	public void setInspecao(InspecaoOrgao inspecao) {
		this.inspecao = inspecao;
	}

	private Questionario questionario = new Questionario();
	
	private List<Questionario> listQuestionario;

	private List<Pergunta> listPerguntas;
	
	private List<CorporativoOrgao> listOrgaos;
	
	private List<Cartorio> listCartorios;
	
	private TipoAplicacaoPergunta tap;
	
	public TipoAplicacaoPergunta getTap() {
		return tap;
	}

	public void setTap(TipoAplicacaoPergunta tap) {
		this.tap = tap;
	}

	public QuestionarioBean() {
		novaPergunta = new Pergunta(); 
		novaPergunta.setTipoAplicacaoPergunta(new TipoAplicacaoPergunta());
		novaPergunta.setTipoFormatoResposta(new TipoFormatoResposta());
	}
	
	public Questionario getQuestionario() {
		return questionario;
	}
	
	public String redirecionarPaginaAlterar() {
		novaPergunta = new Pergunta(); 
		novaPergunta.setTipoAplicacaoPergunta(new TipoAplicacaoPergunta());
		novaPergunta.setTipoFormatoResposta(new TipoFormatoResposta());
		return "/pages/questionario/editarAta.xhtml?faces-redirect=true";
	}
	
	public String redirecionarPaginaVisualizar() {
		novaPergunta = new Pergunta(); 
		novaPergunta.setTipoAplicacaoPergunta(new TipoAplicacaoPergunta());
		novaPergunta.setTipoFormatoResposta(new TipoFormatoResposta());
		return "/pages/questionario/visualizarAta.xhtml?faces-redirect=true";
	}

	public void setQuestionario(Questionario questionario) {
		this.questionario = questionario;
	}	
	
	public List<Questionario> getListQuestionario() {
		return listQuestionario;
	}

	public void setListQuestionario(List<Questionario> listQuestionario) {
		this.listQuestionario = listQuestionario;
	}

	
	public void reordenarLinha(ReorderEvent event) throws ServiceException {
		int ini = event.getFromIndex() + 1;
		int fim = event.getToIndex() + 1;
				
		Pergunta p = (Pergunta) ((DataTable) event.getSource()).getRowData();
		tap = p.getTipoAplicacaoPergunta();	
		
		perguntaService.mudarOrdem(ini, fim, tap, questionario);
				
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Linha alterada com sucesso! ", " ");
        FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	/*
	public void onRowReorder(ReorderEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Row Moved", "From: " + event.getFromIndex() + ", To:" + event.getToIndex());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	*/
	
	public List<Questionario> getAll(){
		try {
			listQuestionario = questionarioService.getAll();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listQuestionario;
	}
	
	public List<Pergunta> getListPerguntas() {
		return listPerguntas;
	}
	
	public List<TipoAplicacaoPergunta> getPerguntas() {

		List<TipoAplicacaoPergunta> tapList = new ArrayList<TipoAplicacaoPergunta>();
		try {		
			listPerguntas = perguntaService.pesquisarPorQuestionario(questionario);
		
			for (Pergunta p : listPerguntas) {
				TipoAplicacaoPergunta tap = p.getTipoAplicacaoPergunta(); 
				if (!tapList.contains(tap)) {
					tap.getPerguntas().clear();
					tapList.add(tap);
				}
				tap.getPerguntas().add(p);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		return tapList;
		
	}

	public Pergunta getPergunta() {
		return pergunta;
	}

	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}

	public String excluirPergunta() {
		try {
			perguntaService.excluirEntidade(pergunta);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/pages/questionario/editarAta.xhtml?faces-redirect=true";
	}

	public Pergunta getNovaPergunta() {
		return novaPergunta;
	}

	public void setNovaPergunta(Pergunta novaPergunta) {
		this.novaPergunta = novaPergunta;
	}
	
	public void marcaTipoAta(TipoAplicacaoPergunta tipoAplicacaoPergunta) {
		novaPergunta.setTipoAplicacaoPergunta(tipoAplicacaoPergunta);
	}
	
	public String salvaPergunta() {
		try {
			novaPergunta.setQuestionario(questionario);
			novaPergunta.setPerguntaModelo('0');
			perguntaService.persistirEntidade(novaPergunta);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		novaPergunta = new Pergunta(); 
		novaPergunta.setTipoAplicacaoPergunta(new TipoAplicacaoPergunta());
		novaPergunta.setTipoFormatoResposta(new TipoFormatoResposta());
		
		return "/pages/questionario/editarAta.xhtml?faces-redirect=true";
	}
	

	public String redirecionaRespostaAta(){

		return "/pages/resposta/respostaAta.xhtml?faces-redirect=true";
	}
	
	
	public String moverParaCima() {
		try {
			int numOrdemAtual = pergunta.getNumOrdem();
			TipoAplicacaoPergunta tipoAplicacaoPergunta = pergunta.getTipoAplicacaoPergunta();
			Pergunta perguntaAux = perguntaService.pesquisarPorNumOrdem(numOrdemAtual - 1, tipoAplicacaoPergunta, questionario);
			pergunta.setNumOrdem(numOrdemAtual - 1);
			perguntaAux.setNumOrdem(numOrdemAtual);
			perguntaService.updateEntidade(pergunta);
			perguntaService.updateEntidade(perguntaAux);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/pages/questionario/editarAta.xhtml?faces-redirect=true";
	}

	public String moverParaBaixo() {
		try {
			int numOrdemAtual = pergunta.getNumOrdem();
			TipoAplicacaoPergunta tipoAplicacaoPergunta = pergunta.getTipoAplicacaoPergunta();
			Pergunta perguntaAux = perguntaService.pesquisarPorNumOrdem(numOrdemAtual + 1, tipoAplicacaoPergunta, questionario);
			pergunta.setNumOrdem(numOrdemAtual + 1);
			perguntaAux.setNumOrdem(numOrdemAtual);
			perguntaService.updateEntidade(pergunta);
			perguntaService.updateEntidade(perguntaAux);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	
	public List<CorporativoOrgao> getOrgaos(){
		try {
			listOrgaos = inspecaoOrgaoService.pesquisarUnidadePorQuestionario(questionario);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listOrgaos;
	}
	
	public List<Cartorio> getCartorios(){
		try {
			listCartorios = inspecaoOrgaoService.pesquisarCartorioPorQuestionario(questionario);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listCartorios;
	}
	
//	public void setListPerguntas(List<Pergunta> listPerguntas) {
//		try {
//			questionario = questionarioService.pesquisarPorId(idPergunta);
//		} catch (ServiceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		this.listPerguntas = questionario.getPerguntas();
//		
//		this.listPerguntas = listPerguntas;
//	}
	
	
	
	
}