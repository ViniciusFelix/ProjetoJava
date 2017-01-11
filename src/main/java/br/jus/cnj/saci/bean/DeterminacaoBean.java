package br.jus.cnj.saci.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.jus.cnj.corporativo.autenticacao.Credencial;
import br.jus.cnj.corporativo.bean.CorporativoOrgao;
import br.jus.cnj.corporativo.utils.ArquivoUtils;
import br.jus.cnj.corregedoria.entity.Cartorio;
import br.jus.cnj.saci.entity.AcompanhamentoDeliberacao;
import br.jus.cnj.saci.entity.Deliberacao;
import br.jus.cnj.saci.entity.DocumentoAcompanhamento;
import br.jus.cnj.saci.entity.Inspecao;
import br.jus.cnj.saci.entity.Questionario;
import br.jus.cnj.saci.entity.TipoAplicacaoPergunta;
import br.jus.cnj.saci.entity.TipoStatusDeterminacao;
import br.jus.cnj.saci.service.AcompanhamentoDeliberacaoService;
import br.jus.cnj.saci.service.DeliberacaoService;
import br.jus.cnj.saci.service.DocumentoAcompanhamentoService;
import br.jus.cnj.saci.service.InspecaoOrgaoService;
import br.jus.cnj.saci.service.RespostaService;
import br.jus.cnj.saci.service.TipoObjetoAplicacaoService;
import br.jus.cnj.saci.service.TipoStatusDeterminacaoService;
import br.jus.cnj.utils.exception.CamposNaoPreenchidosRuntimeException;
import br.jus.cnj.utils.exception.ServiceException;

@ManagedBean(name = "determinacaoBean")
@Component
@Scope(value = "session")
public class DeterminacaoBean {
	
	@Autowired
	private DocumentoAcompanhamentoService documentoAcompanhamentoService;

	@Autowired
	private InspecaoOrgaoService inspecaoOrgaoService;

	@Autowired
	private RespostaService respostaService; 

	@Autowired
	private AcompanhamentoDeliberacaoService acompanhamentoDeliberacaoService;
	
	@Autowired
	private DeliberacaoService deliberacaoService;
	
	@Autowired
	private TipoStatusDeterminacaoService tipoStatusDeterminacaoService;
	
	private List<CorporativoOrgao> listOrgaos;

	private Inspecao inspecao;
	
	private List<TipoStatusDeterminacao> tipoStatusDeterminacaoList = new ArrayList<TipoStatusDeterminacao>();

	private List<Deliberacao> listDeliberacao;
	
	private List<AcompanhamentoDeliberacao> listAcompanhamentoDeliberacao;
	
	private AcompanhamentoDeliberacao acompanhamentoDeliberacaoSelecionado;

	private DocumentoAcompanhamento documentoSelecionado;

	private Deliberacao deliberacaoSelecionada;
	
	private String arquivo;
	
	private UploadedFile file;
	
	
	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	private AcompanhamentoDeliberacao novoAcompanhamentoDeliberacao = new AcompanhamentoDeliberacao();

	public String getArquivo() {
		return arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

	public Deliberacao getDeliberacaoSelecionada() {
		return deliberacaoSelecionada;
	}

	public void setDeliberacaoSelecionada(
			Deliberacao deliberacaoSelecionada) {
		this.deliberacaoSelecionada = deliberacaoSelecionada;
	}

	public List<AcompanhamentoDeliberacao> getListAcompanhamentoDeliberacao() {
		return listAcompanhamentoDeliberacao;
	}

	public void setListAcompanhamentoDeliberacao(List<AcompanhamentoDeliberacao> listAcompanhamentoDeliberacao) {
		this.listAcompanhamentoDeliberacao = listAcompanhamentoDeliberacao;
	}

	public List<Deliberacao> getListDeliberacao() {
		return listDeliberacao;
	}

	public void setListDeliberacao(List<Deliberacao> listDeliberacao) {
		this.listDeliberacao = listDeliberacao;
	}
	
	private Questionario questionario;

	public Questionario getQuestionario() {
		return questionario;
	}

	public void setQuestionario(Questionario questionario) {
		this.questionario = questionario;
	}

	private List<Cartorio> listCartorios;

	public List<Cartorio> getListCartorios() {
		return listCartorios;
	}

	public void setListCartorios(List<Cartorio> listCartorios) {
		this.listCartorios = listCartorios;
	}

	public Inspecao getInspecao() {
		return inspecao;
	}

	public void setInspecao(Inspecao inspecao) {
		this.inspecao = inspecao;
	}

	public List<CorporativoOrgao> getListOrgaos() {
		return listOrgaos;
	}

	public void setListOrgaos(List<CorporativoOrgao> listOrgaos) {
		this.listOrgaos = listOrgaos;
	}
	
	public AcompanhamentoDeliberacao getNovoAcompanhamentoDeliberacao() {
		return novoAcompanhamentoDeliberacao;
	}

	public void setNovoAcompanhamentoDeliberacao(
			AcompanhamentoDeliberacao novoAcompanhamentoDeliberacao) {
		this.novoAcompanhamentoDeliberacao = novoAcompanhamentoDeliberacao;
	}
	
	public AcompanhamentoDeliberacao getAcompanhamentoDeliberacaoSelecionado() {
		return acompanhamentoDeliberacaoSelecionado;
	}

	public void setAcompanhamentoDeliberacaoSelecionado(
			AcompanhamentoDeliberacao acompanhamentoDeliberacaoSelecionado) {
		this.acompanhamentoDeliberacaoSelecionado = acompanhamentoDeliberacaoSelecionado;
	}
	
	public DocumentoAcompanhamento getDocumentoSelecionado() {
		return documentoSelecionado;
	}

	public void setDocumentoSelecionado(DocumentoAcompanhamento documentoSelecionado) {
		this.documentoSelecionado = documentoSelecionado;
	}
	
	public void handleFileUpload(FileUploadEvent event) throws ServiceException, CamposNaoPreenchidosRuntimeException, IOException {

		String caminhoAbsoluto = "\\documentos";
		UploadedFile file = event.getFile();
		DocumentoAcompanhamento da = new DocumentoAcompanhamento();
		da.setAcompanhamentoDeliberacao(acompanhamentoDeliberacaoSelecionado);
		da.setDscUrlArquivo(file.getFileName());
		ArquivoUtils.salvarArquivo(file.getInputstream(), caminhoAbsoluto, file.getFileName());
		if (da.getId() != null) {
			documentoAcompanhamentoService.updateEntidade(da);
		} else {
			documentoAcompanhamentoService.persistirEntidade(da);
		}
		listarAcompanhamentoDeliberacoes();
		
	}

	public String getMensagem(char flg){
		if(flg == 'N'){
			return "Não resolvido";
		}
		
		return "Resolvido";
	}
	
	public void recuperarArquivo(){
		
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpServletResponse resposta = (HttpServletResponse) fc
				.getExternalContext().getResponse();

		String caminhoAbsoluto = "\\documentos";
		String filename = documentoSelecionado.getDscUrlArquivo();
		
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
	
	public String finalizarDeterminacao() throws ServiceException {

		deliberacaoSelecionada.setFlagResolvida('S');
		deliberacaoService.updateEntidade(deliberacaoSelecionada);

		return "/pages/determinacao/determinacaoTribunal.xhtml?faces-redirect=true";

	}
	
	public String fecharDeterminacao() throws ServiceException {

		deliberacaoService.updateEntidade(deliberacaoSelecionada);

		return "/pages/determinacao/determinacaoCnj.xhtml?faces-redirect=true";

	}

	HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	Credencial credencialSession = (Credencial) session.getAttribute("credencial");

	public Credencial getCredencialSession() {
		return credencialSession;
	}

	public void setCredencialSession(Credencial credencial_session) {
		this.credencialSession = credencial_session;
	}
	
	public String redirecionaDeterminacaoBean() throws ServiceException {

		return "/pages/determinacao/determinacaoCnj.xhtml?faces-redirect=true";

	}
	
	public String redirecionaDeterminacaoTribunal() throws ServiceException {

		Inspecao insp = new Inspecao();
		insp.setId(99);
		setInspecao(insp);

		return "/pages/determinacao/determinacaoTribunal.xhtml?faces-redirect=true";

	}
	
	public String listarAcompanhamentoDeliberacoes() throws ServiceException{
		
		try {	
			if (listAcompanhamentoDeliberacao != null) { 
				listAcompanhamentoDeliberacao = deliberacaoService.pesquisaAcompanhamentoPorDeliberacao(deliberacaoSelecionada);
			} else {
				listAcompanhamentoDeliberacao = new ArrayList<AcompanhamentoDeliberacao>();
				listAcompanhamentoDeliberacao = deliberacaoService.pesquisaAcompanhamentoPorDeliberacao(deliberacaoSelecionada);
			}
			
			for(AcompanhamentoDeliberacao ad : listAcompanhamentoDeliberacao){
				ad.setDocumentoAcompanhamento(deliberacaoService.pesquisaDocumentosPorDeliberacao(ad));
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		return "";
		
	}
	
	public String salvarAcompanhamentoDeliberacao() throws ServiceException{

		
		novoAcompanhamentoDeliberacao.setDeliberacao(deliberacaoSelecionada);
		
		if(getCredencialSession().getPerfil().contains("Tribunal")){
			novoAcompanhamentoDeliberacao.setTipoEnvioAcompanhamento("TRIB");
		} else {
			novoAcompanhamentoDeliberacao.setTipoEnvioAcompanhamento("CNJ");
		}
		
		if (novoAcompanhamentoDeliberacao.getId() != null) {
			acompanhamentoDeliberacaoService.updateEntidade(novoAcompanhamentoDeliberacao);
		} else {
			acompanhamentoDeliberacaoService.persistirEntidade(novoAcompanhamentoDeliberacao);
		}

		novoAcompanhamentoDeliberacao = new AcompanhamentoDeliberacao();
		
		listAcompanhamentoDeliberacao = deliberacaoService.pesquisaAcompanhamentoPorDeliberacao(deliberacaoSelecionada);
		return "";
	}
	
	public String somaDiasData(Date date, int days){
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String novaData;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, days);  // number of days to add
		novaData = sdf.format(c.getTime());
		return novaData;
	}
	
	public List<CorporativoOrgao> getOrgaos() {
		try {
			listOrgaos = (List<CorporativoOrgao>) inspecaoOrgaoService.pesquisarUnidadePorInspecao(inspecao);
			for(CorporativoOrgao co : listOrgaos){
				listDeliberacao = deliberacaoService.pesquisaPorInspecaoOrgao(co.getOrgao(), inspecao, getCredencialSession());
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return listOrgaos;
	}

	public List<Cartorio> getCartorios() {
		try {

			listCartorios = inspecaoOrgaoService.pesquisarCartorioPorInspecao(inspecao);
			for(Cartorio ca : listCartorios){
				listDeliberacao = deliberacaoService.pesquisaPorInspecaoCartorio(ca, inspecao, getCredencialSession());
				ca.setDeliberacoes(listDeliberacao);
			}

		} catch (ServiceException e) {

			e.printStackTrace();
			
		}
		return listCartorios;
	}
	
	public List<TipoStatusDeterminacao> getTipoStatusDeterminacaoAll() {
		try {
			tipoStatusDeterminacaoList = tipoStatusDeterminacaoService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoStatusDeterminacaoList;
	}
	
}
