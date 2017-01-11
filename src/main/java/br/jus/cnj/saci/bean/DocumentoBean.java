package br.jus.cnj.saci.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.jus.cnj.saci.entity.TipoAplicacaoPergunta;
import br.jus.cnj.saci.entity.TipoObjetoAplicacao;
import br.jus.cnj.saci.service.TipoAplicacaoPerguntaService;
import br.jus.cnj.saci.service.TipoObjetoAplicacaoService;

@ManagedBean(name = "documentoBean")
@Component
@Scope(value = "session")
public class DocumentoBean {
	
}
