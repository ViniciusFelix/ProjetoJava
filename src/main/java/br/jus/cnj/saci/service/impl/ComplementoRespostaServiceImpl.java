package br.jus.cnj.saci.service.impl;

import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.cnj.corporativo.autenticacao.Credencial;
import br.jus.cnj.saci.dao.ComplementoRespostaDAO;
import br.jus.cnj.saci.entity.ComplementoResposta;
import br.jus.cnj.saci.entity.Pergunta;
import br.jus.cnj.saci.entity.Resposta;
import br.jus.cnj.saci.service.ComplementoRespostaService;
import br.jus.cnj.utils.exception.ServiceException;

@Service("complementoRespostaService")
public class ComplementoRespostaServiceImpl implements ComplementoRespostaService {

	@Autowired
	private ComplementoRespostaDAO complementoRespostaDAO;
	
	@Transactional
	public void persistirEntidade(ComplementoResposta complementoResposta) throws ServiceException {
		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			Credencial credencial_session = (Credencial) session.getAttribute("credencial");
			int idUsuario = credencial_session.getUsuario().getSeqUsuario();
			complementoResposta.setUsuInclusao(idUsuario);
			complementoResposta.setDatInclusao(new Date(System.currentTimeMillis()));
			complementoRespostaDAO.persistirEntidade(complementoResposta);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	@Transactional
	public void excluirEntidade(ComplementoResposta complementoResposta) throws ServiceException {
		try {
			complementoRespostaDAO.excluirEntidade(complementoResposta);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	public List<ComplementoResposta> listaComplementoResposta(Resposta id) throws ServiceException {
		try {
			return complementoRespostaDAO.listaComplementoResposta(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	public void updateEntidade(ComplementoResposta complementoResposta) throws ServiceException {
		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			Credencial credencial_session = (Credencial) session.getAttribute("credencial");
			int idUsuario = credencial_session.getUsuario().getSeqUsuario();
			complementoResposta.setUsuAlteracao(idUsuario);
			complementoResposta.setDatAlteracao(new Date(System.currentTimeMillis()));
			complementoRespostaDAO.updateEntidade(complementoResposta);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

}
