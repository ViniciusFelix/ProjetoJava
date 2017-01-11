package br.jus.cnj.saci.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.cnj.saci.dao.DocumentoDAO;
import br.jus.cnj.saci.dao.ModeloPerguntaDAO;
import br.jus.cnj.saci.entity.Documento;
import br.jus.cnj.saci.entity.ModeloPergunta;
import br.jus.cnj.saci.entity.TipoAplicacaoPergunta;
import br.jus.cnj.saci.service.DocumentoService;
import br.jus.cnj.utils.exception.ServiceException;

@Service("documentoService")
public class DocumentoServiceImpl implements DocumentoService {
	
}
