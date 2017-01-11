package br.jus.cnj.corporativo.autenticacao;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.springframework.context.ApplicationContext;

import br.jus.cnj.corporativo.bean.CorporativoOrgao;
import br.jus.cnj.corporativo.bean.CorporativoPerfil;
import br.jus.cnj.corporativo.bean.CorporativoSistema;
import br.jus.cnj.corporativo.business.CorporativoOrgaoBC;
import br.jus.cnj.corporativo.business.CorporativoPerfilBC;
import br.jus.cnj.corporativo.business.CorporativoSistemaBC;

public class AutenticacaoListener implements PhaseListener {

	private static final long serialVersionUID = -7903941141663507183L;
	private final static String separadorDefault = "SEPARADORCREDENCIALCNJ";
	private final static String nomeSistema = "SACI";
	private final static String PARAMETER_NAME = "c";

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}
	
	@Override
	public void beforePhase(PhaseEvent event) {
		FacesContext facesContext = event.getFacesContext();
		HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
		HttpSession session = request.getSession();
		Credencial credencial_session = (Credencial) session.getAttribute("credencial");
		String c = (String) request.getParameter(PARAMETER_NAME);
		
		if(credencial_session != null && c == null){
			//USUARIO LOGADO
			return;
		}else{	
			if(credencial_session == null && c == null){
				//USUARIO NAO LOGADO
				try {
					facesContext.getExternalContext().redirect("http://wwwh.cnj.jus.br/corporativo");
				} catch (IOException e) {				
					e.printStackTrace();
				}
			}else{
				if(credencial_session == null && c != null){
					//URL COM CREDENCIAL
					PrintWriter out;
					try {
						HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();	
						out = response.getWriter();
								
						String credencialCodificada = c.substring(0, c.length() - 32);
						String hashCredencial = c.substring(c.length() - 32, c.length());
						String stringCredencial = new String(
								Base64.decodeBase64(credencialCodificada.getBytes()));
				
						if (!md5(stringCredencial.getBytes()).equals(hashCredencial)) {
							facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"A Credencial informada e invalida! Favor tente novamente.", "ERRO"));
							return;
						}
				
						String separador_linha1 = ";";
						String[] arrayCredencial = retornaArray(stringCredencial,
								separador_linha1);
						Credencial credencial = retornaCredencial(arrayCredencial);
				
						int inicioUsuario = stringCredencial.indexOf("\n");
						String stringUsuario = stringCredencial.substring(inicioUsuario);
				
						String separador_linha2 = "|";
						String[] arrayUsuario = retornaArray(stringUsuario, separador_linha2);
						UsuarioCorporativo usuario = retornaUsuario(arrayUsuario);
				
						credencial.setUsuario(usuario);
				
						// Validar se credencial é para este sistema
						if (!nomeSistema.equals(credencial.getSistema())) {
							facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"A Credencial informada nao e válida para este sistema! Favor tente novamente.", "ERRO"));
							return;
						}
				
						Date timeAtual = new Date();
						if (timeAtual.getTime() > credencial.getTimeTolive() * 1000) {
							facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Sua Credencial expirou!", "ERRO"));
							return;
						}
				
						//out.println(credencial.toString());
						session.setAttribute("credencial", credencial);
					} catch (IOException e1) {			
						e1.printStackTrace();
					}
				}	
			}
		}		
		
	}
	
	@Override
	public void afterPhase(PhaseEvent event) {
		
	}

	public String[] retornaArray(String string, String separadorString) {
		String d = separadorDefault + separadorString;
		String separador = md5(d.getBytes());
		if (separador.length() % 2 != 0)
			separador = "0" + separador;

		String[] atributos = string.split(separador);

		return atributos;
	}

	private Credencial retornaCredencial(String[] atributosCredencial) {		
		Credencial credencial = new Credencial();
		credencial.setId(new String(Base64.decodeBase64(atributosCredencial[1]
				.getBytes())));
		credencial.setSeqSistema(Integer.parseInt(new String(Base64
				.decodeBase64(atributosCredencial[3].getBytes()))));
		credencial.setSeqPerfil(Integer.parseInt(new String(Base64
				.decodeBase64(atributosCredencial[5].getBytes()))));
		credencial.setDatCriacao(Long.parseLong(new String(Base64
				.decodeBase64(atributosCredencial[7].getBytes()))));
		credencial.setTimeTolive(Long.parseLong(new String(Base64
				.decodeBase64(atributosCredencial[9].getBytes()))));

		CorporativoSistema sistema = new CorporativoSistema();
		sistema.setId(credencial.getSeqSistema());
		sistema = ((CorporativoSistemaBC) getBean("corporativoSistemaBC")).pesquisaPorId(sistema);
		
		CorporativoPerfil perfil = new CorporativoPerfil();
		perfil.setId(credencial.getSeqPerfil());
		perfil = ((CorporativoPerfilBC) getBean("corporativoPerfilBC")).pesquisaPorId(perfil);
		
		credencial.setSistema(sistema.getSigla());		
		credencial.setPerfil(perfil.getNome());
		
		return credencial;
	}

	private UsuarioCorporativo retornaUsuario(String[] atributosUsuario) {
		UsuarioCorporativo usuario = new UsuarioCorporativo();
		usuario.setSeqUsuario(Integer.parseInt(new String(Base64
				.decodeBase64(atributosUsuario[1].getBytes()))));
		usuario.setSigUsuario(new String(Base64
				.decodeBase64(atributosUsuario[3].getBytes())));
		usuario.setNomUsuario(new String(Base64
				.decodeBase64(atributosUsuario[5].getBytes())));
		usuario.setNumCpf(new String(Base64.decodeBase64(atributosUsuario[7]
				.getBytes())));
		usuario.setSeqOrgao(Integer.parseInt(new String(Base64
				.decodeBase64(atributosUsuario[9].getBytes()))));
		usuario.setDscEmail(new String(Base64.decodeBase64(atributosUsuario[11]
				.getBytes())));
		
		CorporativoOrgao orgao = new CorporativoOrgao();
		orgao.setId(usuario.getSeqOrgao());
		orgao = ((CorporativoOrgaoBC) getBean("corporativoOrgaoBC")).pesquisaPorId(orgao);
		usuario.setOrgao(orgao.getDescrica());
		
		return usuario;
	}

	private String md5(byte[] input) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		String hash = new BigInteger(1, md.digest(input)).toString(16);
		return hash;
	}
	
	private Object getBean(String nomeBean) {
		return getWebApplicationContext().getBean(nomeBean);
	}

	private ApplicationContext getWebApplicationContext() {
		return org.springframework.web.context.ContextLoader.getCurrentWebApplicationContext();
	}
}
