package br.jus.cnj.saci.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import br.jus.cnj.corporativo.autenticacao.Credencial;
import br.jus.cnj.corporativo.bean.CorporativoOrgao;
import br.jus.cnj.corregedoria.entity.Cartorio;
//import br.jus.cnj.mail.MailAttach;
import br.jus.cnj.saci.entity.BoasPraticas;
import br.jus.cnj.saci.entity.ComplementoResposta;
import br.jus.cnj.saci.entity.Deliberacao;
import br.jus.cnj.saci.entity.InspecaoOrgao;
import br.jus.cnj.saci.entity.Pergunta;
import br.jus.cnj.saci.entity.PerguntaAutorizadaOrgao;
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
//import br.jus.cnj.enums.utils.TipoAnexoEmail;


@ManagedBean(name = "respostaBean")
@Component
@Scope(value = "session") 
public class RespostaBean {

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
	private Resposta respostaTexto;
	private BoasPraticas boaPratica;
	private List<ComplementoResposta> complementoRespostaList = new ArrayList<ComplementoResposta>();
	private List<Deliberacao> deliberacaoList = new ArrayList<Deliberacao>();
	private List<Pergunta> listPerguntas;
	private List<Resposta> listRespostas;
	private List<BoasPraticas> boasPraticasListagem = new ArrayList<BoasPraticas>();
	private List<Pergunta> perguntaAutorizadaOrgao;
	private List<PerguntaAutorizadaOrgao> perguntaAutorizada;
	private List<PerguntaModel> perguntaModel;
	private boolean autorizada;
	List<TipoAplicacaoPergunta> tapList;
	String email;
	String data;
	boolean enviaEmail;
	private int verificaAlgumMarcado;
	
	

	public int getVerificaAlgumMarcado() {
		return verificaAlgumMarcado;
	}

	public void setVerificaAlgumMarcado(int verificaAlgumMarcado) {
		this.verificaAlgumMarcado = verificaAlgumMarcado;
	}

	public boolean isAutorizada() {
		return autorizada;
	}

	public void setAutorizada(boolean autorizada) {
		this.autorizada = autorizada;
	}
	
	
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

	public List<PerguntaAutorizadaOrgao> getPerguntaAutorizada() {
		return perguntaAutorizada;
	}

	public void setPerguntaAutorizada(
			List<PerguntaAutorizadaOrgao> perguntaAutorizada) {
		this.perguntaAutorizada = perguntaAutorizada;
	}

	public String salvarRespostas() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		Credencial credencial_session = (Credencial) session.getAttribute("credencial");
		int idUsuario = credencial_session.getUsuario().getSeqUsuario();	
		listRespostas = new ArrayList<Resposta>();
				
		try {
			perguntaAutorizadaOrgaoService.removerPerguntasAutorizadas(ata);
		} catch (ServiceException e1) {
			e1.printStackTrace();
		}
		for (TipoAplicacaoPergunta tap : tapList) {
			for (Pergunta p : tap.getPerguntas()) {
				if (p.getRespostas().size() > 0) {
					listRespostas.add(p.getRespostas().get(0));
				}
				
				if(p.isAutorizada()){
					PerguntaAutorizadaOrgao pao = new PerguntaAutorizadaOrgao();
					pao.setPergunta(p);
					pao.setInspecaoOrgao(ata);
					pao.setUsuInclusao(idUsuario);
					pao.setDatInclusao(new Date(System.currentTimeMillis()));
					perguntaAutorizada = new ArrayList<PerguntaAutorizadaOrgao>();
					perguntaAutorizada.add(pao);
					p.setPerguntaAutorizada(perguntaAutorizada);
				}
			}
		}
		
		if(getEmail() != ""){
			enviaEmail(getEmail(), "", getData());
		}
		
		try{
			for (Resposta r : listRespostas) {

				if(perguntaAutorizadaOrgaoService.pesquisaPerguntaAutorizada(r.getPergunta(), ata) == true){
					setVerificaAlgumMarcado(1);
				}
				
				if(r.getDscResposta() == null){
					r.setDscResposta("");
				}
				
				if (r.getId() != null) {
					respostaService.updateEntidade(r);
				} else {
					respostaService.persistirEntidade(r);
				}
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}

		
		return "/pages/resposta/respostaAta.xhtml?faces-redirect=true";
	}
	
	public void enviaEmail(String email, String emailServidor, String prazo){
		
		try {
//			Mail mail = new Mail();
//			mail.setEmailDestinatario(email);
//			mail.setEmailRemetente("marcos@cnj.jus.br");
//			mail.setConteudo("Caro, existe perguntas inseridas para você no sistema SACI, favor acessar com suas credenciais. Prazo para responder quetionario: "+prazo);
//			mail.setTitulo("Ítens remetidos ao Tribunal");
//			MailUtil.enviarEmail(mail);
//
//			MailAttach attach = new MailAttach();
//			attach.setEmailDestinatario("joao.elderbsb@gmail.com");
//			attach.setEmailRemetente("joao.santos@cnj.jus.br");
//			attach.setConteudo("Teste de email sistema SACI");
//			attach.setTitulo("Teste de email SACI");
//			attach.setAnexoEmail(TipoAnexoEmail.PDF);
//			attach.setArquivoAnexo(new byte[1024]);
//			attach.setNomeArquivo("nome_arquivo");
//			attach.setDescricaoArquivo("Descricao arquivo");
//			MailUtil.enviarEmailAnexo(attach);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public String salvaRespostaText() {
		try{
			if (respostaTexto.getId() != null) {
				respostaService.updateEntidade(respostaTexto);
			} else {
				respostaService.persistirEntidade(respostaTexto);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		return "/pages/resposta/respostaAta.xhtml?faces-redirect=true";
	}
	
	public String redirecionaRespostaAta(){
		
		complementoResposta = new ComplementoResposta();
		deliberacao = new Deliberacao();
		boaPratica = new BoasPraticas();
		respostaTexto = new Resposta();
		enviaEmail = false;
		
		try{
			cartorio = (Cartorio)unidade;
			orgao = null;
		} catch(ClassCastException e){
			orgao = (CorporativoOrgao)unidade;
			cartorio = null;
		}
		try {
			if(cartorio==null){
				ata = inspecaoOrgaoService.pesquisarPorOrgao(orgao, questionario);				
			} else {
				ata = inspecaoOrgaoService.pesquisarPorCartorio(cartorio, questionario);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		ata.getId();
		//listRespostas = new ArrayList<Resposta>();
		
		tapList = new ArrayList<TipoAplicacaoPergunta>();
		try {	
			
			setVerificaAlgumMarcado(0);
			listPerguntas = perguntaService.pesquisarPorQuestionario(questionario);

			perguntaAutorizadaOrgao = perguntaAutorizadaOrgaoService.pesquisaPorPerguntaAta(ata);
			
			
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
				p.setAutorizada(perguntaAutorizadaOrgaoService.pesquisaPerguntaAutorizada(p, ata));
				if(perguntaAutorizadaOrgaoService.pesquisaPerguntaAutorizada(p, ata) == true){
					setVerificaAlgumMarcado(1);
				}
				
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
		
		return "/pages/resposta/respostaAta.xhtml?faces-redirect=true";
	}
	
	public String redirecionaVisualizarRespostaAta(){
		redirecionaRespostaAta();
		
		return "/pages/resposta/visualizarRespostaAta.xhtml?faces-redirect=true";
	}
	
	public List<ComplementoResposta> listComplementoResposta() {
		try {			
			if (respostaSelecionada.getId() != null && respostaSelecionada.getComplementoResposta() != null && respostaSelecionada.getComplementoResposta().size() > 0) { 
				complementoRespostaList = complementoRespostaService.listaComplementoResposta(respostaSelecionada);
			} else {
				complementoRespostaList = new ArrayList<ComplementoResposta>();
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return complementoRespostaList; 
	}
	
	public String salvaComplementoResposta() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		Credencial credencial_session = (Credencial) session.getAttribute("credencial");
		int idUsuario = credencial_session.getUsuario().getSeqUsuario();
		try {
			if(respostaSelecionada == null){
				respostaSelecionada = new Resposta();
				respostaSelecionada.setUsuInclusao(idUsuario);
				respostaSelecionada.setDatInclusao(new Date(System.currentTimeMillis()));
			} else if (respostaSelecionada.getDeliberacao() == null) {
				respostaSelecionada.setDeliberacao(deliberacaoList);
			}
			
			complementoResposta.setUsuInclusao(idUsuario);
			complementoResposta.setDatInclusao(new Date(System.currentTimeMillis()));
			complementoResposta.setResposta(respostaSelecionada);

			complementoRespostaList.add(complementoResposta);
			respostaSelecionada.setComplementoResposta(complementoRespostaList);
			
			if(respostaSelecionada.getId() != null) {
				respostaService.updateEntidade(respostaSelecionada);
				listComplementoResposta();
			}else{
				respostaService.persistirEntidade(respostaSelecionada);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		complementoResposta = new ComplementoResposta(); 
		
		return "/pages/questionario/respostaAta.xhtml?faces-redirect=true";
	}

	public String removerComplemento() {
		try {
			complementoRespostaService.excluirEntidade(complementoResposta);
		} catch (DataIntegrityViolationException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"NÃ£o foi possivel excluir o complemento da pergunta", "ERRO"));
			return "";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		getComplementoRespostaList().remove(complementoResposta);

		return "/pages/questionario/respostaAta.xhtml?faces-redirect=true";
	}
	
	public List<Deliberacao> listDeliberacao() {
		try {	
			if (respostaSelecionada.getId() != null && respostaSelecionada.getDeliberacao() != null && respostaSelecionada.getDeliberacao().size() > 0) { 
				deliberacaoList = deliberacaoService.listaDeliberacao(respostaSelecionada);
			} else {
				deliberacaoList = new ArrayList<Deliberacao>();
			}
			
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return deliberacaoList; 
	}
	
	public String salvaDeliberacao() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		Credencial credencial_session = (Credencial) session.getAttribute("credencial");
		int idUsuario = credencial_session.getUsuario().getSeqUsuario();
		try {
			if(respostaSelecionada == null) {
				respostaSelecionada = new Resposta();
				respostaSelecionada.setUsuInclusao(idUsuario);
				respostaSelecionada.setDatInclusao(new Date(System.currentTimeMillis()));
			} else if (respostaSelecionada.getComplementoResposta() == null) {
				respostaSelecionada.setComplementoResposta(complementoRespostaList);
			}
			
			deliberacao.setUsuInclusao(idUsuario);
			deliberacao.setDatInclusao(new Date(System.currentTimeMillis()));
			deliberacao.setResposta(respostaSelecionada);

			deliberacaoList.add(deliberacao);
			respostaSelecionada.setDeliberacao(deliberacaoList);
			
			if(respostaSelecionada.getId() != null) {
				respostaService.updateEntidade(respostaSelecionada);
				listDeliberacao();
			}else{
				respostaService.persistirEntidade(respostaSelecionada);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		deliberacao = new Deliberacao(); 
		
		return "/pages/questionario/respostaAta.xhtml?faces-redirect=true";
	}
	
	public String removerDeliberacao() {
		try {
			deliberacaoService.excluirEntidade(deliberacao);
		} catch (DataIntegrityViolationException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"NÃ£o foi possivel excluir o complemento da pergunta", "ERRO"));
			return "";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		getDeliberacaoList().remove(deliberacao);

		return "/pages/questionario/respostaAta.xhtml?faces-redirect=true";
	}
	

	public String salvarBoasPraticas() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		Credencial credencial_session = (Credencial) session.getAttribute("credencial");
		int idUsuario = credencial_session.getUsuario().getSeqUsuario();
		try {

			if(boaPratica == null){
				boaPratica = new BoasPraticas();
			}
			
			boaPratica.setUsuInclusao(idUsuario);
			boaPratica.setDatInclusao(new Date(System.currentTimeMillis()));
			boaPratica.setInspecaoOrgao(ata);

			boasPraticasListagem.add(boaPratica);
			
			if(boaPratica.getId() != null) {
				boasPraticasService.updateEntidade(boaPratica);
				getBoasPraticasListagem();
			}else{
				boasPraticasService.persistirEntidade(boaPratica);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		boaPratica = new BoasPraticas(); 
			
		return "/pages/questionario/respostaAta.xhtml?faces-redirect=true";
	}
	
	public String removerBoaPratica() {
		try {
			boasPraticasService.excluirEntidade(boaPratica);
		} catch (DataIntegrityViolationException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"NÃ£o foi possivel excluir", "ERRO"));
			return "";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		getBoasPraticasListagem().remove(boaPratica);

		return "/pages/questionario/respostaAta.xhtml?faces-redirect=true";
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
	
	public Resposta getRespostaTexto() {
		return respostaTexto;
	}

	public void setRespostaTexto(Resposta respostaTexto) {
		this.respostaTexto = respostaTexto;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public boolean isEnviaEmail() {
		return enviaEmail;
	}

	public void setEnviaEmail(boolean enviaEmail) {
		this.enviaEmail = enviaEmail;
	}
}
