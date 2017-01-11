package br.jus.cnj.saci.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.jus.cnj.corporativo.autenticacao.Credencial;
import br.jus.cnj.corporativo.bean.CorporativoOrgao;
import br.jus.cnj.corregedoria.entity.Cartorio;
import br.jus.cnj.saci.entity.BoasPraticas;
import br.jus.cnj.saci.entity.ComplementoResposta;
import br.jus.cnj.saci.entity.Deliberacao;
import br.jus.cnj.saci.entity.InspecaoOrgao;
import br.jus.cnj.saci.entity.Pergunta;
import br.jus.cnj.saci.entity.Questionario;
import br.jus.cnj.saci.entity.Resposta;
import br.jus.cnj.saci.entity.TipoAplicacaoPergunta;
import br.jus.cnj.saci.model.PerguntaModel;
import br.jus.cnj.saci.service.BoasPraticasService;
import br.jus.cnj.saci.service.ComplementoRespostaService;
import br.jus.cnj.saci.service.DeliberacaoService;
import br.jus.cnj.saci.service.InspecaoOrgaoService;
import br.jus.cnj.saci.service.PerguntaAutorizadaOrgaoService;
import br.jus.cnj.saci.service.PerguntaService;
import br.jus.cnj.saci.service.QuestionarioService;
import br.jus.cnj.saci.service.RespostaService;
import br.jus.cnj.utils.exception.ServiceException;


@ManagedBean(name = "respostaTribunalBean")
@Component
@Scope(value = "session") 
public class RespostaTribunalBean {

	@Autowired
	private QuestionarioService questionarioService;
	
	@Autowired
	private InspecaoOrgaoService inspecaoOrgaoService;

	@Autowired
	private PerguntaService perguntaService;
	
	@Autowired
	private RespostaService respostaService;
	
	@Autowired
	private ComplementoRespostaService complementoRespostaService;

	@Autowired
	private DeliberacaoService deliberacaoService;

	@Autowired
	private BoasPraticasService boasPraticasService;
	
	@Autowired
	private PerguntaAutorizadaOrgaoService perguntaAutorizadaOrgaoService;
	
	
	private ComplementoResposta complementoResposta;
	private Deliberacao deliberacao;
	private CorporativoOrgao orgao;
	private Cartorio cartorio;
	private Object unidade;
	private Questionario questionario;
	private InspecaoOrgao ata;
	private Resposta respostaSelecionada;
	private BoasPraticas boaPratica;
	private List<ComplementoResposta> complementoRespostaList = new ArrayList<ComplementoResposta>();
	private List<Deliberacao> deliberacaoList = new ArrayList<Deliberacao>();
	private List<Pergunta> listPerguntas;
	private List<Resposta> listRespostas;
	private List<BoasPraticas> boasPraticasListagem = new ArrayList<BoasPraticas>();
	private List<Pergunta> perguntaAutorizadaOrgao;
	private List<PerguntaModel> perguntaModel;
	
	HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	Credencial credencialSession = (Credencial) session.getAttribute("credencial");

	public Credencial getCredencialSession() {
		return credencialSession;
	}

	public void setCredencialSession(Credencial credencialSession) {
		this.credencialSession = credencialSession;
	}

	List<TipoAplicacaoPergunta> tapList;	
	
	public List<TipoAplicacaoPergunta> getTapList() {
		return tapList;
	}

	public void setTapList(List<TipoAplicacaoPergunta> tapList) {
		this.tapList = tapList;
	}

	public List<Resposta> getListRespostas() {
		return listRespostas;
	}

	public void setListRespostas(List<Resposta> listRespostas) {
		this.listRespostas = listRespostas;
	}

	public List<TipoAplicacaoPergunta> getPerguntas() {
		
		return tapList;
		
	}
	
	public String salvarRespostas() {
		listRespostas = new ArrayList<Resposta>();
		for (TipoAplicacaoPergunta tap : tapList) {
			for (Pergunta p : tap.getPerguntas()) {
				if (p.getRespostas().size() > 0) {
					listRespostas.add(p.getRespostas().get(0));
				}
			}
		}
		
		try{
			for (Resposta r : listRespostas) {
				if (r.getId() != null) {
					respostaService.updateEntidade(r);
				} else {
					respostaService.persistirEntidade(r);
				}
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		
		return "/pages/resposta/respostaTribunal.xhtml?faces-redirect=true";
	}
	
		public String redirecionaRespostaTribunal(){
			
		complementoResposta = new ComplementoResposta();
		deliberacao = new Deliberacao();
		boaPratica = new BoasPraticas();
		
		
				try {
					ata = inspecaoOrgaoService.pesquisarPorId(getCredencialSession().getUsuario().getSeqOrgao());
					ata.getId();
					
				}  catch (ServiceException e) {
					
					e.printStackTrace();
					
				}
				
		//listRespostas = new ArrayList<Resposta>();
		
		tapList = new ArrayList<TipoAplicacaoPergunta>();
		try {		
			listPerguntas = perguntaService.pesquisarPorQuestionario(questionario);

			listPerguntas = (List<Pergunta>) perguntaAutorizadaOrgaoService.pesquisaAtaTribunal(ata);
			
			
			for (Pergunta p : listPerguntas) {
				TipoAplicacaoPergunta tap = p.getTipoAplicacaoPergunta(); 
				if (!tapList.contains(tap)) {
					tap.getPerguntas().clear();
					tapList.add(tap);
				}
				tap.getPerguntas().add(p);
				
				Resposta resposta = new Resposta();
				for (Resposta r : p.getRespostas()) {
					if (r.getInspecaoOrgao().equals(ata)) {
						resposta = r;
					}
				}				
				
				
				resposta.setPergunta(p);
				resposta.setInspecaoOrgao(ata);
				//listRespostas.add(resposta);
				
				listRespostas = new ArrayList<Resposta>();
				listRespostas.add(resposta);
				p.setRespostas(listRespostas);
				
				
			}
			perguntaModel = new ArrayList<PerguntaModel>();
			for(TipoAplicacaoPergunta tap : tapList){
				perguntaModel.add(new PerguntaModel(tap.getPerguntas()));
				tap.setPerguntaModel(perguntaModel.get(perguntaModel.size()-1));
			}
			
		} catch (ServiceException e) {
			e.printStackTrace();
		}

		try {
			boasPraticasListagem = boasPraticasService.pesquisaPorInspecaoOrgao(ata);	
			
			if(boasPraticasListagem == null){
				boasPraticasListagem = new ArrayList<BoasPraticas>();
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		return "/pages/resposta/respostaTribunal.xhtml?faces-redirect=true";
	}
	
	
	
	public ComplementoResposta getComplementoResposta() {
		return complementoResposta;
	}

	public void setComplementoResposta(ComplementoResposta complementoResposta) {
		this.complementoResposta = complementoResposta;
	}

	public Deliberacao getDeliberacao() {
		return deliberacao;
	}

	public void setDeliberacao(Deliberacao deliberacao) {
		this.deliberacao = deliberacao;
	}

	public CorporativoOrgao getOrgao() {
		return orgao;
	}

	public void setOrgao(CorporativoOrgao orgao) {
		this.orgao = orgao;
	}

	public Cartorio getCartorio() {
		return cartorio;
	}

	public void setCartorio(Cartorio cartorio) {
		this.cartorio = cartorio;
	}

	public Object getUnidade() {
		return unidade;
	}

	public void setUnidade(Object unidade) {
		this.unidade = unidade;
	}

	public Questionario getQuestionario() {
		return questionario;
	}

	public void setQuestionario(Questionario questionario) {
		this.questionario = questionario;
	}

	public InspecaoOrgao getAta() {
		return ata;
	}

	public void setAta(InspecaoOrgao ata) {
		this.ata = ata;
	}

	public Resposta getRespostaSelecionada() {
		return respostaSelecionada;
	}

	public void setRespostaSelecionada(Resposta respostaSelecionada) {
		this.respostaSelecionada = respostaSelecionada;
	}

	public List<ComplementoResposta> getComplementoRespostaList() {
		return complementoRespostaList;
	}

	public void setComplementoRespostaList(
			List<ComplementoResposta> complementoRespostaList) {
		this.complementoRespostaList = complementoRespostaList;
	}

	public List<Deliberacao> getDeliberacaoList() {
		return deliberacaoList;
	}

	public void setDeliberacaoList(List<Deliberacao> deliberacaoList) {
		this.deliberacaoList = deliberacaoList;
	}

	public List<Pergunta> getListPerguntas() {
		return listPerguntas;
	}

	public void setListPerguntas(List<Pergunta> listPerguntas) {
		this.listPerguntas = listPerguntas;
	}

	public List<BoasPraticas> getBoasPraticasListagem() {
		return boasPraticasListagem;
	}

	public void setBoasPraticasListagem(List<BoasPraticas> boasPraticasListagem) {
		this.boasPraticasListagem = boasPraticasListagem;
	}

	public BoasPraticas getBoaPratica() {
		return boaPratica;
	}

	public void setBoaPratica(BoasPraticas boaPratica) {
		this.boaPratica = boaPratica;
	}

	
	public List<Pergunta> getPerguntaAutorizadaOrgao() {
		return perguntaAutorizadaOrgao;
	}

	public void setPerguntaAutorizadaOrgao(
			List<Pergunta> perguntaAutorizadaOrgao) {
		this.perguntaAutorizadaOrgao = perguntaAutorizadaOrgao;
	}

	public List<PerguntaModel> getPerguntaModel() {
		return perguntaModel;
	}

	public void setPerguntaModel(List<PerguntaModel> perguntaModel) {
		this.perguntaModel = perguntaModel;
	}
}
