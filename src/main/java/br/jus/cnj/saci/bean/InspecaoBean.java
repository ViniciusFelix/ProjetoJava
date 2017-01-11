package br.jus.cnj.saci.bean;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.DualListModel;
import org.primefaces.model.TreeNode;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.jus.cnj.corporativo.autenticacao.Credencial;
import br.jus.cnj.corporativo.bean.CorporativoEstado;
import br.jus.cnj.corporativo.bean.CorporativoOrgao;
import br.jus.cnj.corporativo.bean.CorporativoTipoOrgao;
import br.jus.cnj.corporativo.business.CorporativoEstadoBC;
import br.jus.cnj.corporativo.business.CorporativoOrgaoBC;
import br.jus.cnj.corporativo.entity.Cidade;
import br.jus.cnj.corporativo.model.CorporativoOrgaoModel;
import br.jus.cnj.corporativo.utils.ArquivoUtils;
import br.jus.cnj.corregedoria.entity.Cartorio;
import br.jus.cnj.corregedoria.model.CartorioModel;
import br.jus.cnj.corregedoria.service.CartorioService;
import br.jus.cnj.corregedoria.service.CidadeService;
import br.jus.cnj.saci.entity.AchadoInspecao;
import br.jus.cnj.saci.entity.DeterminacaoInspecao;
import br.jus.cnj.saci.entity.Documento;
import br.jus.cnj.saci.entity.Inspecao;
import br.jus.cnj.saci.entity.InspecaoObjetoAplicacao;
import br.jus.cnj.saci.entity.InspecaoOrgao;
import br.jus.cnj.saci.entity.SubUnidadeOrgao;
import br.jus.cnj.saci.entity.TipoAplicacaoPergunta;
import br.jus.cnj.saci.entity.TipoObjetoAplicacao;
import br.jus.cnj.saci.entity.TipoStatusAchado;
import br.jus.cnj.saci.entity.TipoStatusDeterminacao;
import br.jus.cnj.saci.entity.UnidadesAtas;
import br.jus.cnj.saci.model.TipoAplicacaoPerguntaModel;
import br.jus.cnj.saci.service.AchadoInspecaoService;
import br.jus.cnj.saci.service.DeterminacaoInspecaoService;
import br.jus.cnj.saci.service.InspecaoObjetoAplicacaoService;
import br.jus.cnj.saci.service.InspecaoOrgaoService;
import br.jus.cnj.saci.service.InspecaoService;
import br.jus.cnj.saci.service.ModeloPerguntaService;
import br.jus.cnj.saci.service.PerguntaService;
import br.jus.cnj.saci.service.TipoAplicacaoPerguntaService;
import br.jus.cnj.saci.service.TipoObjetoAplicacaoService;
import br.jus.cnj.utils.exception.CamposNaoPreenchidosRuntimeException;
import br.jus.cnj.utils.exception.ServiceException;

@ManagedBean(name = "inspecaoBean")
@Component
@Scope(value = "session")
public class InspecaoBean {

	private static List<Inspecao> inspecaoList = new ArrayList<Inspecao>();

	private Inspecao inspecao = new Inspecao();
	
	private List<AchadoInspecao> achadoInspecaoList = new ArrayList<AchadoInspecao>();

	private String nomeTipoInspecao;
	
	private List<Inspecao> filteredInspecao;
	
	private List<Cidade> cidadeList = new ArrayList<Cidade>();
	    	
	private List<TipoObjetoAplicacao> tipoObjetoAplicacaoList = new ArrayList<TipoObjetoAplicacao>();

	@Autowired
	private InspecaoService inspecaoService;

	@Autowired
	private DeterminacaoInspecaoService determinacaoInspecaoService;
	
	@Autowired
	private InspecaoOrgaoService inspecaoOrgaoService;

	@Autowired
	private TipoObjetoAplicacaoService tipoObjetoAplicacaoService;
	
	@Autowired
	private InspecaoObjetoAplicacaoService inspecaoObjetoAplicacaoService;
//	
//	@Autowired
//	private QuestionarioService questionarioService;
	
	@Autowired
	private CorporativoEstadoBC corporativoEstadoBC;

	@Autowired
	private CorporativoOrgaoBC corporativoOrgaoBC;
	
	@Autowired
	private AchadoInspecaoService achadoInspecaoService;
	
	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private CartorioService cartorioService;
	
	@Autowired
	private TipoAplicacaoPerguntaService tipoAplicacaoPerguntaService;

	@Autowired
	private ModeloPerguntaService modeloPerguntaService;
	
	@Autowired
	private PerguntaService perguntaService;

	List<TipoObjetoAplicacao> tipoObjeto;
	
	List<CorporativoEstado> estados;
	
	List<CorporativoOrgao> tribunais;
	
	private List<UploadedFile> arquivos = new ArrayList<UploadedFile>();
	
	private List<CorporativoOrgao> circunscricoes = new ArrayList<CorporativoOrgao>();
	private List<CorporativoOrgao> circunscricoesTarget = new ArrayList<CorporativoOrgao>();
	
	private List<CorporativoOrgao> unidadesList = new ArrayList<CorporativoOrgao>();
	private List<CorporativoOrgao> unidadesListTarget = new ArrayList<CorporativoOrgao>();
	
	private List<CorporativoOrgao> unidadesSelectedList = new ArrayList<CorporativoOrgao>();
	private AchadoInspecao achado = new AchadoInspecao();
	private List<Cartorio> cartorioList = new ArrayList<Cartorio>();
	private List<Cartorio> cartorioListTarget = new ArrayList<Cartorio>();
	
	private List<Cartorio> cartoriosSelectedList = new ArrayList<Cartorio>();
	
	private DualListModel<CorporativoOrgao> circunscricoesDual;
	
	private DualListModel<CorporativoOrgao> unidadesDual;
	private CorporativoEstado uf;
	
	private DeterminacaoInspecao detProvisoario = new DeterminacaoInspecao();
	private Cidade cidade;
	
	private CorporativoTipoOrgao esfera;
	
	private AchadoInspecao achadoSelected = new AchadoInspecao();
	
	private DeterminacaoInspecao determinacaoSelected = new DeterminacaoInspecao();

	private AchadoInspecao novoAchado = new AchadoInspecao();

	private CorporativoOrgao tribunal;
	
	private CorporativoOrgao circunscricao;
	
	private SubUnidadeOrgao subUnidadeOrgao = new SubUnidadeOrgao();
	
	private List<InspecaoOrgao> inspecaoOrgaoList;
	
	InspecaoOrgao inspecaoOrgao;
	
	private Documento documento = new Documento();
	
	private List<Documento> documentos = new ArrayList<Documento>();
	
	int activeTabIndex = 0;
	
	private DeterminacaoInspecao novaDeterminacao = new DeterminacaoInspecao();
	
	private TipoStatusDeterminacao tipoStatusDeterminacao = new TipoStatusDeterminacao();

	private SelectItem[] tiposDeInspecao; 
	
	String caminhoAbsoluto = "\\documentos";
	
	private List<TipoAplicacaoPergunta> tipoAplicacaoPerguntaSelected = new ArrayList<TipoAplicacaoPergunta>();
	
	private List<TipoObjetoAplicacao> tipoObjetoAplicacaoListSelected = new ArrayList<TipoObjetoAplicacao>();

	private List<List<TipoAplicacaoPergunta>> atas = new ArrayList<List<TipoAplicacaoPergunta>>();

	private List<TipoAplicacaoPergunta> tipoAplicacaoPerguntaList = new ArrayList<TipoAplicacaoPergunta>();

	private Collection<TipoAplicacaoPergunta> tipoAplicacaoPerguntaListSelected = new ArrayList<TipoAplicacaoPergunta>();

	private TipoAplicacaoPergunta tipoAplicacaoPergunta = new TipoAplicacaoPergunta();
	
	private UnidadesAtas unidadesAtas = new UnidadesAtas(); 
	private List<UnidadesAtas> unidadesAtasList = new ArrayList<UnidadesAtas>();
	
	private TipoAplicacaoPerguntaModel tipoAplicacaoPerguntaModel;
		
	private CorporativoOrgaoModel corporativoOrgaoModel;
	
	private CartorioModel cartorioModel;
	
	private TreeNode rootJudicial;
	private TreeNode rootExtrajudicial;
    
    private TreeNode[] selectedNodes;

    private PieChartModel pieModel1;
    
    private List<PieChartModel> graficoDinamico;
    
    private int justicaEstadualSelected;

	private List<PieChartModel> pcmList;

	
	public DeterminacaoInspecao getDeterminacaoSelected() {
		return determinacaoSelected;
	}

	public void setDeterminacaoSelected(DeterminacaoInspecao determinacaoSelected) {
		this.determinacaoSelected = determinacaoSelected;
	}
	
	public AchadoInspecao getNovoAchado() {
		return novoAchado;
	}

	public void setNovoAchado(AchadoInspecao novoAchado) {
		this.novoAchado = novoAchado;
	}
	
	public DeterminacaoInspecao getDetProvisoario() {
		return detProvisoario;
	}

	public void setDetProvisoario(DeterminacaoInspecao detProvisoario) {
		this.detProvisoario = detProvisoario;
	}
	
	public DeterminacaoInspecao getNovaDeterminacao() {
		return novaDeterminacao;
	}

	public void setNovaDeterminacao(DeterminacaoInspecao novaDeterminacao) {
		this.novaDeterminacao = novaDeterminacao;
	}
	
	public AchadoInspecao getAchadoSelected() {
		return achadoSelected;
	}

	public void setAchadoSelected(AchadoInspecao achadoSelected) {
		this.achadoSelected = achadoSelected;
	}
	
	public List<AchadoInspecao> getAchadoInspecaoList() {
		return achadoInspecaoList;
	}

	public void setAchadoInspecaoList(List<AchadoInspecao> achadoInspecaoList) {
		this.achadoInspecaoList = achadoInspecaoList;
	}
	
    public int getJusticaEstadualSelected() {
		return justicaEstadualSelected;
	}

	public void setJusticaEstadualSelected(int justicaEstadualSelected) {
		this.justicaEstadualSelected = justicaEstadualSelected;
	}

	public PieChartModel getPieModel1() {
        return pieModel1;
    }
     
	public SelectItem[] getTiposDeInspecao() {  
        return tiposDeInspecao;  
    } 


	public List<PieChartModel> getGraficoDinamico() {
        return graficoDinamico;
    }

	public void setGraficoDinamico(List<PieChartModel> graficoDinamico) {
		this.graficoDinamico = graficoDinamico;
	}

	private void createPieModel1(String titulo) {
        pieModel1 = new PieChartModel();
         
        pieModel1.set("Atendidas", 540);
        pieModel1.set("Não Atendidas", 325);
        pieModel1.set("Parcialmente Atendidas", 702);
        pieModel1.set("Prejudicada", 421);
        pieModel1.set("Recomendação", 421);

        pieModel1.setSeriesColors("0000FF, FF0000, FFFF00, 808080, 008000");
        pieModel1.setTitle(titulo);
        pieModel1.setLegendPosition("w");
    }

	private PieChartModel criaGraficoDinamico(String titulo, int a, int na, int pa, int p, int r) {
		PieChartModel grafico = new PieChartModel();
         
		grafico.set("Atendidas", a);
		grafico.set("Não Atendidas", na);
		grafico.set("Parcialmente Atendidas", pa);
		grafico.set("Prejudicada", p);
		grafico.set("Recomendação", r);

		grafico.setSeriesColors("0000FF, FF0000, FFFF00, 808080, 008000");
		grafico.setTitle(titulo);
		grafico.setLegendPosition("w");
		return grafico;
    }
	
	private SelectItem[] createFilterOptions()  {  
        SelectItem[] options = new SelectItem[3];  
  
        options[0] = new SelectItem("", "Selecione");  
        options[1] = new SelectItem("C", "Correição");
        options[2] = new SelectItem("I", "Inspeção");
  
        return options;  
    }  
	
	public InspecaoBean(){
		inspecao = new Inspecao();
		uf = new CorporativoEstado();
		esfera = new CorporativoTipoOrgao();
		tribunal = new CorporativoOrgao();
		circunscricoes = new ArrayList<CorporativoOrgao>();
		circunscricoesTarget = new ArrayList<CorporativoOrgao>();
		unidadesList = new ArrayList<CorporativoOrgao>();
		unidadesListTarget = new ArrayList<CorporativoOrgao>();
		tribunais = new ArrayList<CorporativoOrgao>();
		cidadeList = new ArrayList<Cidade>();
		cartorioList = new ArrayList<Cartorio>();
		subUnidadeOrgao = new SubUnidadeOrgao();

		TipoStatusAchado tea = new TipoStatusAchado();
		tea.setId(1);
		
		novoAchado = new AchadoInspecao();
		novoAchado.setDeterminacoes(new ArrayList<DeterminacaoInspecao>());
//		novoAchado.setTipoStatusAchado(tea);
		novoAchado.setFlgAchadoBloqueado('0');
		
		
		tiposDeInspecao = createFilterOptions();
		
		
		
		
		documento = new Documento();
		documentos = new ArrayList<Documento>();
		inspecao.setDocumentos(documentos);
		
		atas = new ArrayList<List<TipoAplicacaoPergunta>>();

		
		setUnidadesAtas(new UnidadesAtas());
		setUnidadesAtasList(new ArrayList<UnidadesAtas>());
		unidadesSelectedList = new ArrayList<CorporativoOrgao>();
		cartoriosSelectedList = new ArrayList<Cartorio>();
		
	}
	

	public List<AchadoInspecao> getAchados() {
		try {
			achadoInspecaoList = achadoInspecaoService.pesquisarPorInspecao(inspecao);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return achadoInspecaoList;
	}
	
	
	public String redirecionarPaginaAchados() throws ServiceException {
		setAchadoInspecaoList(achadoInspecaoService.pesquisarPorInspecao(inspecao));
		return "/pages/inspecao/achadosDeterminacoes.xhtml?faces-redirect=true";
	}
	
	
	
	public String redirecionarPaginaAlterar() throws ServiceException {
		uf = new CorporativoEstado();
		esfera = new CorporativoTipoOrgao();
		tribunal = new CorporativoOrgao();
		circunscricoes = new ArrayList<CorporativoOrgao>();
		circunscricoesTarget = new ArrayList<CorporativoOrgao>();
		unidadesList = new ArrayList<CorporativoOrgao>();
		unidadesListTarget = new ArrayList<CorporativoOrgao>();
		tribunais = new ArrayList<CorporativoOrgao>();
		cidadeList = new ArrayList<Cidade>();
		cartorioList = new ArrayList<Cartorio>();
		cartorioListTarget = new ArrayList<Cartorio>();
		arquivos = new ArrayList<UploadedFile>();
		tipoObjetoAplicacaoList = new ArrayList<TipoObjetoAplicacao>();
		
		unidadesSelectedList = new ArrayList<CorporativoOrgao>();
		cartoriosSelectedList = new ArrayList<Cartorio>();
		
		List<CorporativoOrgao> orgaos = new ArrayList<CorporativoOrgao>();		
		List<Cartorio> cartorios = new ArrayList<Cartorio>();
		
		rootJudicial = new CheckboxTreeNode("Root", null);
		rootExtrajudicial = new CheckboxTreeNode("Root", null);
		
		tipoObjetoAplicacaoList.addAll(inspecaoObjetoAplicacaoService.pesquisaPorInspecao(inspecao));
		
		setTipoObjetoAplicacaoListSelected(tipoObjetoAplicacaoListSelected);
		
		try {
			if (inspecao.getOrgaos().size() > 0) {

				if (inspecao.getOrgaos().get(0).getCartorio() != null) {
					setActiveTabIndex(1);
					
					Cidade cidade = inspecao.getOrgaos().get(0).getCartorio().getCidade();
					uf = corporativoEstadoBC.pesquisarPorUf(cidade.getUf());
					
					cidadeList = cidadeService.pesquisarPorUf(uf.getUf());
					
					for(Cidade cid : cidadeList){
						if(cid.getCartorios().size() > 0){
							TreeNode node = new CheckboxTreeNode(cid, rootExtrajudicial);
							for(Cartorio cart : cid.getCartorios()){
								TreeNode node0 = new CheckboxTreeNode(cart, node);
								if(cartoriosSelectedList.contains(cart)){
									node0.getParent().setExpanded(true);
									node0.setSelected(true);
								}
							}
						}
					}

				} else {
					setActiveTabIndex(0);
					
					CorporativoOrgao orgao = inspecao.getOrgaos().get(0).getOrgao();
					tribunal = orgao;
					while(!tribunal.getTipoOrgao().getId().substring(0, 4).equals("TRIB")) {
						tribunal = tribunal.getOrgao();	
					} 
					esfera = tribunal.getTipoOrgao();
					
					setUnidadesSelectedList(inspecaoOrgaoService.getOrgaosArvore(inspecao));
					allByEsfera();
				}
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		if(inspecao.getOrgaos().size() != 0 && inspecao.getOrgaos().get(0).getOrgao() != null){
			createPieModel1(inspecao.getOrgaos().get(0).getOrgao().getDescrica());
		} else {
			createPieModel1(inspecao.getOrgaos().get(0).getCartorio().getCidade().getUf());
		}
		return "/pages/inspecao/alterar.xhtml?faces-redirect=true";
	}
	
	public String redirecionarPaginaVisualizar() throws ServiceException {
		redirecionarPaginaAlterar();
		
		return "/pages/inspecao/visualizar.xhtml?faces-redirect=true";
	}
	
	public String mapaJusticaEstadual() throws ServiceException {
		return "/pages/mapas/estadual.xhtml?faces-redirect=true";
	}
	
	public String listagemEsfera() throws ServiceException {
		return "/pages/principal.xhtml?faces-redirect=tr1ue";
	}

	
	
	public String redirecionarPaginaCadastro() {
		inspecao = new Inspecao();
		uf = new CorporativoEstado();
		esfera = new CorporativoTipoOrgao();
		tribunal = new CorporativoOrgao();
		circunscricoes = new ArrayList<CorporativoOrgao>();
		circunscricoesTarget = new ArrayList<CorporativoOrgao>();
		unidadesList = new ArrayList<CorporativoOrgao>();
		unidadesListTarget = new ArrayList<CorporativoOrgao>();
		tribunais = new ArrayList<CorporativoOrgao>();
		cidadeList = new ArrayList<Cidade>();
		cartorioList = new ArrayList<Cartorio>();
		
		cartoriosSelectedList = new ArrayList<Cartorio>();
		
		subUnidadeOrgao = new SubUnidadeOrgao();
		
		documento = new Documento();
		documentos = new ArrayList<Documento>();
		arquivos = new ArrayList<UploadedFile>();
		
		inspecao.setDocumentos(documentos);
		
		tipoObjetoAplicacaoListSelected = new ArrayList<TipoObjetoAplicacao>();
		
		atas = new ArrayList<List<TipoAplicacaoPergunta>>();
		tipoAplicacaoPerguntaSelected = new ArrayList<TipoAplicacaoPergunta>();
		tipoAplicacaoPerguntaList = new ArrayList<TipoAplicacaoPergunta>();
		tipoAplicacaoPerguntaListSelected = new ArrayList<TipoAplicacaoPergunta>();
		unidadesSelectedList = new ArrayList<CorporativoOrgao>();
		
		setUnidadesAtas(new UnidadesAtas());
		setUnidadesAtasList(new ArrayList<UnidadesAtas>());
		
		rootJudicial = new CheckboxTreeNode("Root", null);
		rootExtrajudicial = new CheckboxTreeNode("Root", null);
		
		tipoAplicacaoPerguntaModel = new TipoAplicacaoPerguntaModel(new ArrayList<TipoAplicacaoPergunta>());
		corporativoOrgaoModel = new CorporativoOrgaoModel(new ArrayList<CorporativoOrgao>());
		cartorioModel = new CartorioModel(new ArrayList<Cartorio>());
		
		//selectedNodes = null;
		
		return "/pages/inspecao/cadastrar.xhtml?faces-redirect=true";
	}
	
	public String redirecionarListagemEstadual() {

		createPieModel1("teste");
		
		pcmList = new ArrayList<PieChartModel>();
		PieChartModel pcm = null;
		for (Inspecao insp : getAllJusticaEstadual()) {
			pcm = criaGraficoDinamico(insp.getNumInspecao()+"/"+insp.getNumAnoInspecao(), 10,20,30,40,50);
			pcmList.add(pcm);
		}
		setGraficoDinamico(pcmList);
		return "/pages/inspecao/listarEstadual.xhtml?faces-redirect=true";
	}

	
	
	public String redirecionarPaginaListar() {
		inspecao = new Inspecao();
		uf = new CorporativoEstado();
		esfera = new CorporativoTipoOrgao();
		tribunal = new CorporativoOrgao();
		circunscricoes = new ArrayList<CorporativoOrgao>();
		circunscricoesTarget = new ArrayList<CorporativoOrgao>();
		unidadesList = new ArrayList<CorporativoOrgao>();
		unidadesListTarget = new ArrayList<CorporativoOrgao>();
		tribunais = new ArrayList<CorporativoOrgao>();
		cidadeList = new ArrayList<Cidade>();
		cartorioList = new ArrayList<Cartorio>();
		subUnidadeOrgao = new SubUnidadeOrgao();
		
		tiposDeInspecao = createFilterOptions();
		
		return "/pages/inspecao/listar.xhtml?faces-redirect=true";
	}
	
	public CorporativoOrgao selecionaTribunal(CorporativoOrgao orgao) {
		while(!orgao.getTipoOrgao().getId().substring(0, 4).equals("TRIB")) {
			orgao = orgao.getOrgao();	
		} 
		return orgao;
	}
	
	public void abreFilhos(TreeNode no) {
		no.setExpanded(true);
		aoExpandir(no);
		if (no.getChildCount() > 0) {
			for (TreeNode node : no.getChildren()) {
				abreFilhos(node);
			}
		} else {
			//for (TreeNode node : no.getChildren()) {
				no.setSelected(true);
				if (!unidadesSelectedList.contains((CorporativoOrgao) no.getData())) {
					unidadesSelectedList.add((CorporativoOrgao) no.getData());
				}
			//}
		}
	}
	
	public void preencheUnidadesSelected(NodeSelectEvent event) {
		//TODO SÃ³ pode um tribunal por inspeÃ§Ã£o
		//selecionaTribunal((CorporativoOrgao) event.getTreeNode().getData());
		if (unidadesSelectedList.size() > 0 && selecionaTribunal(unidadesSelectedList.get(0)) != selecionaTribunal((CorporativoOrgao) event.getTreeNode().getData())) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Selecione unidades somente de um tribunal.", "WARINIG"));
			event.getTreeNode().setSelected(false);
		} else {
			if (event.getTreeNode().getChildCount() > 0) {
				abreFilhos(event.getTreeNode());
			} else {
				if (!unidadesSelectedList.contains((CorporativoOrgao) event.getTreeNode().getData())) {
					unidadesSelectedList.add((CorporativoOrgao) event.getTreeNode().getData());
				}
			}
			
			corporativoOrgaoModel = new CorporativoOrgaoModel(unidadesSelectedList);
		}
	}
	
	public void fechaFilhos(TreeNode no) {
		//no.setExpanded(true);
		//aoExpandir(no);
		if (no.getChildCount() > 0) {
			for (TreeNode node : no.getChildren()) {
				fechaFilhos(node);
			}
		} else {
			//for (TreeNode node : no.getChildren()) {
				no.setSelected(false);
				if (unidadesSelectedList.contains((CorporativoOrgao) no.getData())) {
					unidadesSelectedList.remove((CorporativoOrgao) no.getData());
				}
			//}
		}
	}
	
	public void despreencheUnidadesSelected(NodeUnselectEvent event) {
		if (event.getTreeNode().getChildCount() > 0) {
			fechaFilhos(event.getTreeNode());
		} else {
			if (unidadesSelectedList.contains((CorporativoOrgao) event.getTreeNode().getData())) {
				unidadesSelectedList.remove((CorporativoOrgao) event.getTreeNode().getData());
			}
		}
		
		corporativoOrgaoModel = new CorporativoOrgaoModel(unidadesSelectedList);
		
		//TESTAR DESPREENCHER PAI E FILHOS
		
	}
	
	public void aoExpandir(TreeNode tree) {
		if (tree.getChildren().size() == 1 && tree.getChildren().get(0).getData().equals("000")) {
			tree.getChildren().clear();
			CorporativoOrgao pai = (CorporativoOrgao) tree.getData();
			try {
				for(CorporativoOrgao org : corporativoOrgaoBC.pesquisaPorOrgaoPai(pai)){
					TreeNode node0 = new CheckboxTreeNode(org, tree);
//					if (org.getTipoOrgao().getId().substring(0, 4).equals("GRA1") || org.getTipoOrgao().getId().substring(0, 4).equals("GRA2")) {
//						node0.setSelectable(false);
//					}
					if(org.getOrgaosFilhos().size() > 0){
						TreeNode node1 = new CheckboxTreeNode("000", node0);
					}
				}
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void onNodeExpand(NodeExpandEvent event){
		aoExpandir(event.getTreeNode());
	}
	
	public void carregaArvore(List<CorporativoOrgao> filhos, TreeNode node){
		for(CorporativoOrgao circ : filhos){
			TreeNode node0 = new CheckboxTreeNode(circ, node);
			
			if(circ.getOrgaosFilhos().size() > 0){
				carregaArvore(circ.getOrgaosFilhos(),node0);
			}
		}
	}

	public void allByEsfera() {         
		try {
			unidadesSelectedList = new ArrayList<CorporativoOrgao>();
			cartoriosSelectedList = new ArrayList<Cartorio>();
			

			if(getInspecao().getId() != null){
				setUnidadesSelectedList(inspecaoOrgaoService.getOrgaosArvore(inspecao));
				unidadesSelectedList = getUnidadesSelectedList();
			}
			
			corporativoOrgaoModel = new CorporativoOrgaoModel(unidadesSelectedList);
			cartorioModel = new CartorioModel(cartoriosSelectedList);
			
			
			rootJudicial = new CheckboxTreeNode("Root", null);
			
			tribunais = corporativoOrgaoBC.pesquisaPorEsfera(esfera);
			
			for(CorporativoOrgao org : tribunais){
				TreeNode node = new CheckboxTreeNode(org, rootJudicial);
				//TODO trazer Ã¡rvore de tribunal aberta atÃ© a seleÃ§Ã£o
//				node.setSelectable(false);
				if(org.getOrgaosFilhos().size() > 0){
					TreeNode node0 = new CheckboxTreeNode("000", node);
				}
				if(unidadesSelectedList.contains(org)){
					node.setSelected(true);
				}
			}
			
			List<CorporativoOrgao> unidadesCaminho = new ArrayList<CorporativoOrgao>();
			for (CorporativoOrgao unidade : unidadesSelectedList) {
				if (!unidade.getTipoOrgao().getId().substring(0, 4).equals("TRIB")) {
					unidadesCaminho.add(unidade);
					do {
						unidade = unidade.getOrgao();
						unidadesCaminho.add(unidade);
					} while(!unidade.getTipoOrgao().getId().substring(0, 4).equals("TRIB"));
					
					TreeNode node1 = new CheckboxTreeNode();
					for (TreeNode org : rootJudicial.getChildren()) {
						if (unidade.getId().equals(((CorporativoOrgao) org.getData()).getId())) {
							node1 = org;
						}
					}
					preencheTree(node1, unidade, unidadesCaminho);
					
				}
			}
			
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	public void preencheTree(TreeNode node1, CorporativoOrgao unidade, List<CorporativoOrgao> unidadesCaminho) {
		try {
			node1.getChildren().clear();
			for (CorporativoOrgao org : corporativoOrgaoBC.pesquisaPorOrgaoPai(unidade)) {
				TreeNode node2 = new CheckboxTreeNode(org, node1);
				if(org.getOrgaosFilhos().size() > 0){
					TreeNode node0 = new CheckboxTreeNode("000", node2);
				}
				if(unidadesSelectedList.contains(org)){
					node2.setSelected(true);
				} else {
					if (unidadesCaminho.contains(org)) {
						preencheTree(node2, org, unidadesCaminho);
					}
				}
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	public List<CorporativoOrgao> allByTribunal() {
		try {			
			circunscricoes = corporativoOrgaoBC.pesquisaSegundoNivel(tribunal, esfera);
			unidadesList = new ArrayList<CorporativoOrgao>();
			
			circunscricoesTarget = new ArrayList<CorporativoOrgao>();
			unidadesListTarget = new ArrayList<CorporativoOrgao>();
			
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return circunscricoes;
	}

	public void allByUF() {
		try {		
			unidadesSelectedList = new ArrayList<CorporativoOrgao>();
			cartoriosSelectedList = new ArrayList<Cartorio>();
			corporativoOrgaoModel = new CorporativoOrgaoModel(unidadesSelectedList);
			cartorioModel = new CartorioModel(cartoriosSelectedList);
			
			cidadeList = cidadeService.pesquisarPorUf(uf.getUf());
			
			rootExtrajudicial = new CheckboxTreeNode("Root", null); 
			
			for(Cidade cid : cidadeList){
				if(cid.getCartorios().size() > 0){
					TreeNode node = new CheckboxTreeNode(cid, rootExtrajudicial);
					for(Cartorio cart : cid.getCartorios()){
						TreeNode node0 = new CheckboxTreeNode(cart, node);
					}
				}
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}		
	}
	
	public static int diferencaEmDias(Calendar c1, Calendar c2)
	{
	    long m1 = c1.getTimeInMillis();
	    long m2 = c2.getTimeInMillis();
	    return (int) ((m2 - m1) / (24*60*60*1000));
	}
	
	public int diasRestantes(Date data, int dias){
		int diasr = 0;
			Calendar c = Calendar.getInstance();
			Calendar b = Calendar.getInstance();
			c.setTime(data);
			c.add(Calendar.DATE, +dias+1);
			b.setTime(new Date());
			diasr = diferencaEmDias(b, c);
		return diasr;
	}
	public Date getDataHoje(){
		Date d = new Date();
		return d;
	}
	
	public String alterarAchado() throws ServiceException{
		achado = achadoSelected;
		
		try {
			if (achado.getId() == null) {
				achadoInspecaoService.persistirEntidade(achado);
			} else {
				achadoInspecaoService.updateEntidade(achado);
			}
			getAchados();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setAchadoInspecaoList(achadoInspecaoService.pesquisarPorInspecao(inspecao));
		return "/pages/inspecao/achadosDeterminacoes.xhtml?faces-redirect=true";
	}

	
	public String alterarDeterminacao() throws ServiceException{
		
		try {
			if (determinacaoSelected.getId() == null) {
				determinacaoInspecaoService.persistirEntidade(determinacaoSelected);
			} else {
				determinacaoInspecaoService.updateEntidade(determinacaoSelected);
			}
			getAchados();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setAchadoInspecaoList(achadoInspecaoService.pesquisarPorInspecao(inspecao));
		return "/pages/inspecao/achadosDeterminacoes.xhtml?faces-redirect=true";
	}
	
	public String cadastrarAchado() throws ServiceException{
		novoAchado.setInspecao(inspecao);
		try {
			if (novoAchado.getId() == null) {
				
				achadoInspecaoService.persistirEntidade(novoAchado);
			} else {
				achadoInspecaoService.updateEntidade(novoAchado);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		TipoStatusAchado tea = new TipoStatusAchado();
		tea.setId(1);
		novoAchado = new AchadoInspecao();
		novoAchado.setDeterminacoes(new ArrayList<DeterminacaoInspecao>());
//		novoAchado.setTipoStatusAchado(tea);
		novoAchado.setFlgAchadoBloqueado('0');
		getAchados();
		
		setAchadoInspecaoList(achadoInspecaoService.pesquisarPorInspecao(inspecao));
		return "/pages/inspecao/achadosDeterminacoes.xhtml?faces-redirect=true";
	}
	
	public void cadastrarDeterminacao(){
		novaDeterminacao.setDscRespostaTribunal(null);
		novaDeterminacao.setStatusDeterminacao(null);
		
		try {
			if (novaDeterminacao.getId() == null) {
				determinacaoInspecaoService.persistirEntidade(novaDeterminacao);
			} else {
				determinacaoInspecaoService.updateEntidade(novaDeterminacao);
			}
			novaDeterminacao = new DeterminacaoInspecao();
			getAchados();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String alterarInspecao() {
		
		List<InspecaoOrgao> inspecaoOrgaoList = new ArrayList<InspecaoOrgao>();

		List listaAPercorrer;

		for (Object unidade : unidadesSelectedList){
			inspecaoOrgao = new InspecaoOrgao();
			inspecaoOrgao.setOrgao((CorporativoOrgao) unidade);				
			inspecaoOrgao.setFlgAtivo("S");				
			inspecaoOrgao.setInspecao(inspecao);
			inspecaoOrgaoList.add(inspecaoOrgao);
		}

		for (Object cartorio : cartoriosSelectedList){
			inspecaoOrgao = new InspecaoOrgao();
			inspecaoOrgao.setCartorio((Cartorio) cartorio);				
			inspecaoOrgao.setFlgAtivo("S");				
			inspecaoOrgao.setInspecao(inspecao);
			inspecaoOrgaoList.add(inspecaoOrgao);
		}
		
		List<InspecaoObjetoAplicacao> ioaList =  new ArrayList<InspecaoObjetoAplicacao>();
		
		for (Object tipoObjeto : tipoObjetoAplicacaoListSelected){
			InspecaoObjetoAplicacao ioa = new InspecaoObjetoAplicacao();
			ioa.setInspecao(inspecao);
			ioa.setTipoObjetoAplicacao((TipoObjetoAplicacao) tipoObjeto);
			ioaList.add(ioa);
		}
		
		inspecao.setInspecaoObjetoAplicacao(ioaList);
		inspecao.setOrgaos(inspecaoOrgaoList);
		
		try {
			for (UploadedFile arquivo : arquivos) {
				ArquivoUtils.salvarArquivo(arquivo.getInputstream(), caminhoAbsoluto, arquivo.getFileName());
			}
		} catch (CamposNaoPreenchidosRuntimeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			if (!inspecaoService.updateEntidade(inspecao)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"A data inicial deve ser anterior Ã  data final da inspeÃ§Ã£o.", "ERRO"));
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		inspecao = new Inspecao();
		return "/pages/inspecao/listar.xhtml?faces-redirect=true";
	}
	
	public String publicarPje(){
		try {
			inspecao.setFlgInspecaoPublicada('1');
			
			inspecaoService.updateEntidade(inspecao);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "/pages/inspecao/listar.xhtml?faces-redirect=true";
	}


	@SuppressWarnings("unused")
	private void enviaEmail(String email2, String dias2) {

		try {
			/*			Mail mail = new Mail();
			mail.setEmailDestinatario(email2);
			mail.setEmailRemetente("marcos@cnj.jus.br");
			mail.setConteudo("Caros, existem determinações para o tribunal para serem cumpridas em "+dias2+" a partir da data de hoje.");
			mail.setTitulo("Determinações da Corregedoria Nacional de Justiça");
			MailUtil.enviarEmail(mail);*/
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

	public String cadastarInspecao() {	
		List<InspecaoOrgao> inspecaoOrgaoListFull = new ArrayList<InspecaoOrgao>();
//		try{
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			Credencial credencial_session = (Credencial) session.getAttribute("credencial");
			int idUsuario = credencial_session.getUsuario().getSeqUsuario();
			inspecao.setFlgInspecaoPublicada('0');
				InspecaoOrgao inspecaoOrgao = new InspecaoOrgao();
				inspecaoOrgaoList = new ArrayList<InspecaoOrgao>();
				
				List listaAPercorrer;

				for (Object unidade : unidadesSelectedList){
					inspecaoOrgao = new InspecaoOrgao();
					inspecaoOrgao.setOrgao((CorporativoOrgao) unidade);				
					inspecaoOrgao.setFlgAtivo("S");				
					inspecaoOrgao.setInspecao(inspecao);
					inspecaoOrgaoList.add(inspecaoOrgao);
				}

				for (Object cartorio : cartoriosSelectedList){
					inspecaoOrgao = new InspecaoOrgao();
					inspecaoOrgao.setCartorio((Cartorio) cartorio);				
					inspecaoOrgao.setFlgAtivo("S");				
					inspecaoOrgao.setInspecao(inspecao);
					inspecaoOrgaoList.add(inspecaoOrgao);
				}
				
		List<InspecaoObjetoAplicacao> ioaList =  new ArrayList<InspecaoObjetoAplicacao>();

		for (Object tipoObjeto : tipoObjetoAplicacaoListSelected){
			InspecaoObjetoAplicacao ioa = new InspecaoObjetoAplicacao();
			ioa.setInspecao(inspecao);
			ioa.setTipoObjetoAplicacao((TipoObjetoAplicacao) tipoObjeto);
			ioaList.add(ioa);
		}
		
		inspecao.setInspecaoObjetoAplicacao(ioaList);
		inspecao.setOrgaos(inspecaoOrgaoList);
		
		try {
			for (UploadedFile arquivo : arquivos) {
				ArquivoUtils.salvarArquivo(arquivo.getInputstream(), caminhoAbsoluto, arquivo.getFileName());
			}
		} catch (CamposNaoPreenchidosRuntimeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			if (!inspecaoService.persistirEntidade(inspecao)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"A data inicial deve ser anterior Ã  data final da inspeÃ§Ã£o.", "ERRO"));
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		inspecao = new Inspecao();
		return "/pages/inspecao/listar.xhtml?faces-redirect=true";
	}

	public String excluirInspecao() {
		try {
			inspecaoService.excluirEntidade(inspecao);
			
			for(Documento documento : inspecao.getDocumentos()){
				ArquivoUtils.excluirArquivo(caminhoAbsoluto, documento.getUrlArquivo());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/pages/inspecao/listar.xhtml?faces-redirect=true";
	}

	public String excluirAchado() throws ServiceException {
		try {
			achadoInspecaoService.excluirEntidade(achadoSelected);
		} catch (Exception e) {
			e.printStackTrace();
		}

		setAchadoInspecaoList(achadoInspecaoService.pesquisarPorInspecao(inspecao));
		return "/pages/inspecao/achadosDeterminacoes.xhtml?faces-redirect=true";
	}
	
	public String salvarStatusDeterminacao() throws ServiceException {
		try {
			determinacaoSelected.setStatusDeterminacao(tipoStatusDeterminacao);
			determinacaoInspecaoService.updateEntidade(determinacaoSelected);
		} catch (Exception e) {
			e.printStackTrace();
		}

		setAchadoInspecaoList(achadoInspecaoService.pesquisarPorInspecao(inspecao));
		return "/pages/inspecao/achadosDeterminacoes.xhtml?faces-redirect=true";
	}


	public List<Inspecao> getAll() {
		try {
			inspecaoList = inspecaoService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return inspecaoList;
	}


	public String excluirDeterminacao() throws ServiceException {
		try {
			determinacaoInspecaoService.excluirEntidade(determinacaoSelected);
		} catch (Exception e) {
			e.printStackTrace();
		}

		setAchadoInspecaoList(achadoInspecaoService.pesquisarPorInspecao(inspecao));
		return "/pages/inspecao/achadosDeterminacoes.xhtml?faces-redirect=true";
	}


	public List<Inspecao> getAllJusticaEstadual() {
		try {
			List<Inspecao> inspList = new ArrayList<Inspecao>();
			inspList = inspecaoService.getAll();
			inspecaoList.clear();
			CorporativoOrgao org = new CorporativoOrgao();
			for (Inspecao insp : inspList){
				if(insp.getOrgaos().size() > 0){
						if(insp.getOrgaos().get(0).getOrgao() != null){
							org = selecionaTribunal(insp.getOrgaos().get(0).getOrgao());
							if(org.getId() == getJusticaEstadualSelected()){
								inspecaoList.add(insp);
							}
						}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return inspecaoList;
	}


	public List<CorporativoEstado> getAllEstados() {
		try {
			estados = corporativoEstadoBC.getAll();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return estados;
	}

	public Cidade getCidade() {  
        return cidade;  
    }
	
	public CorporativoOrgao getCircunscricao() {
		return circunscricao;
	}
	
	public DualListModel<CorporativoOrgao> getCircunscricoes() {
		List<CorporativoOrgao> circunscricoesSource = new ArrayList<CorporativoOrgao>();  
        List<CorporativoOrgao> circunscricoesTarget = this.circunscricoesTarget;

        this.getAll();
        
        for(int i=0; i < this.circunscricoes.size();i++){
        	circunscricoesSource.add(this.circunscricoes.get(i));
    	}
        circunscricoesDual = new DualListModel<CorporativoOrgao>(circunscricoesSource, circunscricoesTarget);
    	
        return circunscricoesDual; 
	}
  
    public CorporativoTipoOrgao getEsfera() {
		return esfera;
	}  
    
	public List<CorporativoEstado> getEstados() {
		return estados;
	}

	public List<Inspecao> getFilteredInspecao() {  
        return filteredInspecao;  
    }

	public Inspecao getInspecao() {
		return inspecao;
	}

	public List<Inspecao> getInspecaoList() {
		return inspecaoList;
	}
	
	public List<CorporativoOrgao> getTribunais() {
		return tribunais;
	}
	
	public CorporativoOrgao getTribunal() {
		return tribunal;
	}

	public CorporativoEstado getUf() {  
        return uf;  
    }

	public String nomeTipoInspecao(char tipo) {
		switch (tipo) {
			case 'C': nomeTipoInspecao = "Correição"; break;
			case 'I': nomeTipoInspecao = "Inspeção"; break;
			default: nomeTipoInspecao = "Indefinido"; break;
		}
		return nomeTipoInspecao;
	}

	public void setCidade(Cidade cidade) {  
        this.cidade = cidade;  
    }
	
	public void setCircunscricao(CorporativoOrgao circunscricao) {
		this.circunscricao = circunscricao;
	}
	
	public void setCircunscricoes(DualListModel<CorporativoOrgao> circunscricoesDual) {
		this.circunscricoesDual = circunscricoesDual;
	}

	public void setEsfera(CorporativoTipoOrgao esfera) {
		this.esfera = esfera;
	}

	public void setInspecaoOrgao(InspecaoOrgao inspecaoOrgao) {
		this.inspecaoOrgao = inspecaoOrgao;
	}

	public List<InspecaoOrgao> getInspecaoOrgao() {
		return inspecaoOrgaoList;
	}

	public void setInspecaoOrgao(List<InspecaoOrgao> inspecaoOrgaoList) {
		this.inspecaoOrgaoList = inspecaoOrgaoList;
	}

	public SubUnidadeOrgao getSubUnidadeOrgao() {
		return subUnidadeOrgao;
	}

	public void setSubUnidadeOrgao(SubUnidadeOrgao subUnidadeOrgao) {
		this.subUnidadeOrgao = subUnidadeOrgao;
	}

	public void setEstados(List<CorporativoEstado> estados) {
		this.estados = estados;
	}  
  
    public void setFilteredInspecao(List<Inspecao> filteredInspecao) throws ServiceException {  
        this.filteredInspecao = inspecaoService.getAll();  
    }  
    
    public void setInspecao(Inspecao inspecao) {
		this.inspecao = inspecao;
	}  
  
    public void setInspecaoList(List<Inspecao> tipoAplicacaoPergundaList) {
		this.inspecaoList = tipoAplicacaoPergundaList;
	}
    

    public void setTribunais(List<CorporativoOrgao> tribunais) {
		this.tribunais = tribunais;
	}  

    public void setTribunal(CorporativoOrgao tribunal) {
		this.tribunal = tribunal;
    }
    
	public void setUf(CorporativoEstado uf) {  
        this.uf = uf;  
    }
	
	public void allCartorioByCidade() throws ServiceException {
		
	}

	public List<CorporativoOrgao> getUnidadesList() {
		return unidadesList;
	}

	public void setUnidadesList(List<CorporativoOrgao> unidadesList) {
		this.unidadesList = unidadesList;
	}

	public DualListModel<CorporativoOrgao> getUnidades() {
		List<CorporativoOrgao> unidadesSource = new ArrayList<CorporativoOrgao>();  
        List<CorporativoOrgao> unidadesTarget = unidadesListTarget;

        this.getAll();
        
        for(int i=0; i < this.unidadesList.size();i++){
        	unidadesSource.add(this.unidadesList.get(i));
    	}

        unidadesDual = new DualListModel<CorporativoOrgao>(unidadesSource, unidadesTarget);
    	
        return unidadesDual; 
	}

	public void setUnidades(DualListModel<CorporativoOrgao> unidadesDual) {
		this.unidadesDual = unidadesDual;
	}
	
	public void preencheCartorio(TransferEvent event) {
		//StringBuilder builder = new StringBuilder();  
        for(Object item : event.getItems()) {  
            try {
            	String a = (String) item;
            	Cidade b = cidadeService.pesquisarPorId(Integer.parseInt(a));
            	List <Cartorio> c = cartorioService.pesquisarPorCidade(b); 
            	if (event.isRemove()) {
            		cartorioListTarget = new ArrayList<Cartorio>();
            		for (Cartorio d : c) {
            			if (d != null) {
            				for (int i = 0; i < cartorioList.size(); i++) {
            					if (cartorioList.get(i).getId() == (d.getId())) {
            						cartorioList.remove(i);
            					}
            				}
            				for (int i = 0; i < cartorioListTarget.size(); i++) {
            					if (cartorioListTarget.get(i).getId() == (d.getId())) {
            						cartorioListTarget.remove(i);
            					}
            				}
            			}            			
            		}
            	} else {
            		cartorioList.addAll(c);
            	}				
			} catch (ServiceException e) {
				e.printStackTrace();
			}
    		//builder.append(" ");  
        }  
        
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,builder.toString(), "ERRO"));
	}
	
	public void preencheCartorioSelected(NodeSelectEvent event) {
		try{
			cartoriosSelectedList.add((Cartorio) event.getTreeNode().getData());
		}catch(ClassCastException e){
			for(Cartorio cart : ((Cidade) event.getTreeNode().getData()).getCartorios()){
				if(!cartoriosSelectedList.contains(cart)){
					cartoriosSelectedList.add(cart);
				}
			}
		}
		
		cartorioModel = new CartorioModel(cartoriosSelectedList);
	}
	
	public void despreencheCartorioSelected(NodeUnselectEvent event) {
		try{
			cartoriosSelectedList.remove((Cartorio) event.getTreeNode().getData());
		}catch(ClassCastException e){
			for(Cartorio cart : ((Cidade) event.getTreeNode().getData()).getCartorios()){
				cartoriosSelectedList.remove(cart);
			}
		}
		cartorioModel = new CartorioModel(cartoriosSelectedList);
	}
	
	public int getActiveTabIndex() {
		return activeTabIndex;
	}

	public void setActiveTabIndex(int activeTabIndex) {
		this.activeTabIndex = activeTabIndex;
	}
	
	public String montaListaUnidade(){
		if(unidadesAtas.getUnidades().size() > 0 || unidadesAtas.getCartorios().size() > 0){
			unidadesAtasList.add(unidadesAtas);
			
			unidadesAtas = new UnidadesAtas();
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Favor selecione um tipo de ata e unidades.", "ERRO"));
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		}
		
		return null;
	}
	
	public String montaListaDocumento(){
		if(documento.getTipoDocumento() != null && documento.getNomeArquivo() != null && documento.getUrlArquivo() != null){
			inspecao.getDocumentos().add(documento);
			
			documento = new Documento();
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Favor selecione um tipo de documento, descriÃ§Ã£o e um arquivo.", "ERRO"));
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		}
		
		return null;
	}
	
	public String reinit(){
		documento = new Documento();
		
		return null;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public List<Documento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}
	
	public void handleFileUpload(FileUploadEvent event) {
		documento.setUrlArquivo(event.getFile().getFileName());
		
		//documento.setInspecao(inspecao);
		
		arquivos.add(event.getFile());
	}	

	public List<UploadedFile> getArquivos() {
		return arquivos;
	}

	public void setArquivos(List<UploadedFile> arquivos) {
		this.arquivos = arquivos;
	}
	
	public void limpaUrlArquivo() {
		documento.setUrlArquivo(null);
	}
	
	public void recuperarArquivo(){
		
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpServletResponse resposta = (HttpServletResponse) fc
				.getExternalContext().getResponse();
		
		String filename = documento.getUrlArquivo();
		
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();  
        String path = fc.getExternalContext().getRealPath(caminhoAbsoluto);
        String fullFileName = path + "\\" + filename;  
        File file = new File(fullFileName); 
  
        resposta.setHeader("Content-Disposition", "attachment;filename=\"" + filename + "\"");  
        resposta.setContentLength((int) file.length());
        resposta.setContentType(context.getMimeType(filename));
  
        try {  
            FileInputStream in = new FileInputStream(file);  
            ServletOutputStream out = resposta.getOutputStream();
  
            byte[] buf = new byte[(int)file.length()];  
            int count;  
            while ((count = in.read(buf)) >= 0) {  
                out.write(buf, 0, count);  
            }  
            in.close();  
            out.flush();  
            out.close();  
            FacesContext.getCurrentInstance().responseComplete();  
        } catch (IOException ex) {  
            System.out.println("Error in downloadFile: " + ex.getMessage());  
            ex.printStackTrace();  
        } 
	}
	
	public void removerArquivo() {
		inspecao.getDocumentos().remove(documento);
	}
	
	public void removerAta(){
		getUnidadesAtasList().remove(unidadesAtas);
	}
	
	public void buscaTipoAta() {

		tipoAplicacaoPerguntaList = new ArrayList<TipoAplicacaoPergunta>();
		for (TipoObjetoAplicacao toa : tipoObjetoAplicacaoListSelected) {
			for (TipoAplicacaoPergunta t : toa.getTipoAplicacaoPergunta()) {
				if (!tipoAplicacaoPerguntaList.contains(t)) {
					if(t.getModeloPerguntaBasicos().size() > 0){
						tipoAplicacaoPerguntaList.add(t);
					}
				}
			}
		}
		
		Collections.sort(tipoAplicacaoPerguntaList);
		 
		 tipoAplicacaoPerguntaModel = new TipoAplicacaoPerguntaModel(tipoAplicacaoPerguntaList);
	}

	public List<TipoAplicacaoPergunta> getTipoAplicacaoPerguntaList() {
		return tipoAplicacaoPerguntaList;
	}

	public void setTipoAplicacaoPerguntaList(
			List<TipoAplicacaoPergunta> tipoAplicacaoPerguntaList) {
		this.tipoAplicacaoPerguntaList = tipoAplicacaoPerguntaList;
	}

	public List<TipoObjetoAplicacao> getTipoObjetoAplicacaoListSelected() {
		return tipoObjetoAplicacaoListSelected;
	}

	public void setTipoObjetoAplicacaoListSelected(
			List<TipoObjetoAplicacao> tipoObjetoAplicacaoListSelected) {
		this.tipoObjetoAplicacaoListSelected = tipoObjetoAplicacaoListSelected;
	}

	public Collection<TipoAplicacaoPergunta> getTipoAplicacaoPerguntaListSelected() {
		return tipoAplicacaoPerguntaListSelected;
	}

	public void setTipoAplicacaoPerguntaListSelected(
			Collection<TipoAplicacaoPergunta> tipoAplicacaoPerguntaListSelected) {
		this.tipoAplicacaoPerguntaListSelected = tipoAplicacaoPerguntaListSelected;
	}

	public List<List<TipoAplicacaoPergunta>> getAtas() {
		return atas;
	}

	public void setAtas(List<List<TipoAplicacaoPergunta>> listaAtas) {
		atas = listaAtas;
	}

	public TipoAplicacaoPergunta getTipoAplicacaoPergunta() {
		return tipoAplicacaoPergunta;
	}

	public void setTipoAplicacaoPergunta(
			TipoAplicacaoPergunta tipoAplicacaoPergunta) {
		this.tipoAplicacaoPergunta = tipoAplicacaoPergunta;
	}
	
	public void validarCampos(){
		tipoAplicacaoPergunta.getId();
	}

	public List<TipoAplicacaoPergunta> getTipoAplicacaoPerguntaSelected() {
		return tipoAplicacaoPerguntaSelected;
	}

	public void setTipoAplicacaoPerguntaSelected(
			List<TipoAplicacaoPergunta> tipoAplicacaoPerguntaSelected) {
		this.tipoAplicacaoPerguntaSelected = tipoAplicacaoPerguntaSelected;
	}

	public List<CorporativoOrgao> getUnidadesSelectedList() {
		return unidadesSelectedList;
	}

	public void setUnidadesSelectedList(List<CorporativoOrgao> unidadesSelectedList) {
		this.unidadesSelectedList = unidadesSelectedList;
	}

	public UnidadesAtas getUnidadesAtas() {
		return unidadesAtas;
	}

	public void setUnidadesAtas(UnidadesAtas unidadesAtas) {
		this.unidadesAtas = unidadesAtas;
	}

	public List<UnidadesAtas> getUnidadesAtasList() {
		return unidadesAtasList;
	}

	public void setUnidadesAtasList(List<UnidadesAtas> unidadesAtasList) {
		this.unidadesAtasList = unidadesAtasList;
	}
	
	public List<Cartorio> getCartoriosSelectedList() {
		return cartoriosSelectedList;
	}

	public void setCartoriosSelectedList(List<Cartorio> cartoriosSelectedList) {
		this.cartoriosSelectedList = cartoriosSelectedList;
	}
	
    public TreeNode getRootJudicial() {
		return rootJudicial;
	}

	public void setRootJudicial(TreeNode rootJudicial) {
		this.rootJudicial = rootJudicial;
	}

	public TreeNode getRootExtrajudicial() {
		return rootExtrajudicial;
	}

	public void setRootExtrajudicial(TreeNode rootExtrajudicial) {
		this.rootExtrajudicial = rootExtrajudicial;
	}

	public TreeNode[] getSelectedNodes() {  
        return selectedNodes;  
    }  
  
    public void setSelectedNodes(TreeNode[] selectedNodes) {  
        this.selectedNodes = selectedNodes;  
    }  

	public List<TipoObjetoAplicacao> getAllTipoObjetoAplicacao() {
		try {
			tipoObjetoAplicacaoList = tipoObjetoAplicacaoService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoObjetoAplicacaoList;
	}


	public TipoStatusDeterminacao getTipoStatusDeterminacao() {
		return tipoStatusDeterminacao;
	}

	public void setTipoStatusDeterminacao(
			TipoStatusDeterminacao tipoStatusDeterminacao) {
		this.tipoStatusDeterminacao = tipoStatusDeterminacao;
	}
	
	public TipoAplicacaoPerguntaModel getTipoAplicacaoPerguntaModel() {
		return tipoAplicacaoPerguntaModel;
	}

	public void setTipoAplicacaoPerguntaModel(
			TipoAplicacaoPerguntaModel tipoAplicacaoPerguntaModel) {
		this.tipoAplicacaoPerguntaModel = tipoAplicacaoPerguntaModel;
	}

	public CorporativoOrgaoModel getCorporativoOrgaoModel() {
		return corporativoOrgaoModel;
	}

	public void setCorporativoOrgaoModel(CorporativoOrgaoModel corporativoOrgaoModel) {
		this.corporativoOrgaoModel = corporativoOrgaoModel;
	}

	public CartorioModel getCartorioModel() {
		return cartorioModel;
	}

	public void setCartorioModel(CartorioModel cartorioModel) {
		this.cartorioModel = cartorioModel;
	}

	public void gerarRelatorio(Inspecao inspecaoSelecionado) throws ServiceException {
		
		inspecaoSelecionado = inspecaoService.pesquisarPorId(inspecaoSelecionado.getId());
		
		Object inspecaoOrgaoSelecionado = null;
		
		if(inspecao.getOrgaos().size() != 0){ 
			if(inspecao.getOrgaos().get(0).getOrgao() != null){ 
				inspecaoOrgaoSelecionado = selecionaTribunal(inspecao.getOrgaos().get(0).getOrgao());
			}else{ 
				inspecaoOrgaoSelecionado = inspecao.getOrgaos().get(0).getCartorio().getCidade().getUf(); 
				}
		}
		

		List<Inspecao> inspecaoListNovo = new ArrayList<Inspecao>();
		inspecaoListNovo.add(inspecaoSelecionado);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgaoSelecionado", inspecaoOrgaoSelecionado);
		
		try {
			//GerarRelatorioUtilJSF gerarRelatorioUtilJSF = new GerarRelatorioUtilJSF();
	        //gerarRelatorioUtilJSF.gerarRelatorio(inspecaoListNovo, map, "Relatório Saci", "relatorio", "auto_cirsunstanciado", TipoRelatorio.DOCX);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
