package br.jus.cnj.saci.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.jus.cnj.saci.entity.ComplementoResposta;
import br.jus.cnj.saci.entity.Resposta;
import br.jus.cnj.saci.service.ComplementoRespostaService;

@ManagedBean(name = "complementoRespostaBean")
@Component
@Scope(value = "session") 
public class ComplementoRespostaBean {

	@Autowired
	private ComplementoRespostaService complementoRespostaService;
	
	private ComplementoResposta complementoResposta;
	private Resposta resposta;
	
	public String salvaComplementoResposta() {
		try {
			complementoResposta.setResposta(resposta);
			complementoRespostaService.persistirEntidade(complementoResposta);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		complementoResposta = new ComplementoResposta(); 
		
		return "/pages/questionario/editarAta.xhtml?faces-redirect=true";
	}

	public ComplementoResposta getComplementoResposta() {
		return complementoResposta;
	}

	public void setComplementoResposta(ComplementoResposta complementoResposta) {
		this.complementoResposta = complementoResposta;
	}

	public Resposta getResposta() {
		return resposta;
	}

	public void setResposta(Resposta resposta) {
		this.resposta = resposta;
	}
	
}
