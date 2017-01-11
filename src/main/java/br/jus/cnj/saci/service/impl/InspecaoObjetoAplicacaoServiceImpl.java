package br.jus.cnj.saci.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.cnj.saci.dao.InspecaoObjetoAplicacaoDAO;
import br.jus.cnj.saci.entity.Inspecao;
import br.jus.cnj.saci.entity.InspecaoObjetoAplicacao;
import br.jus.cnj.saci.entity.TipoObjetoAplicacao;
import br.jus.cnj.saci.service.InspecaoObjetoAplicacaoService;
import br.jus.cnj.utils.exception.DaoException;
import br.jus.cnj.utils.exception.ServiceException;

@Service("inspecaoObjetoAplicacaoService")
public class InspecaoObjetoAplicacaoServiceImpl implements InspecaoObjetoAplicacaoService {
	@Autowired
	private InspecaoObjetoAplicacaoDAO inspecaoObjetoAplicacaoDao;

	@Transactional
	public boolean persistirEntidade(InspecaoObjetoAplicacao inspecaoObjetoAplicacao) throws ServiceException {
		try {
				inspecaoObjetoAplicacaoDao.persistirEntidade(inspecaoObjetoAplicacao);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return true;
	}

	@Transactional
	public void excluirEntidade(InspecaoObjetoAplicacao inspecaoObjetoAplicacao) throws ServiceException {
		try {
			inspecaoObjetoAplicacaoDao.excluirEntidade(inspecaoObjetoAplicacao);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	public List<InspecaoObjetoAplicacao> getAll() throws ServiceException {
		try {
			return inspecaoObjetoAplicacaoDao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	public InspecaoObjetoAplicacao pesquisarPorId(int id) throws ServiceException {
		try {
			return inspecaoObjetoAplicacaoDao.pesquisarPorId(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	@Transactional
	public boolean updateEntidade(InspecaoObjetoAplicacao inspecaoObjetoAplicacao) throws ServiceException {
		try {
			inspecaoObjetoAplicacaoDao.updateEntidade(inspecaoObjetoAplicacao);
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}
	
	@Transactional
	public Collection<InspecaoObjetoAplicacao> convertIdListToObjectList(Collection<String> tipoObjetoAplicacao) throws ServiceException {
		Collection<InspecaoObjetoAplicacao> listInspecaoObjetoAplicacao = new ArrayList<InspecaoObjetoAplicacao>();
		try {
			for (String idTipoObjetoAplicacao : tipoObjetoAplicacao) {
				listInspecaoObjetoAplicacao.add(pesquisarPorId(Integer.parseInt(idTipoObjetoAplicacao)));
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
			
		return listInspecaoObjetoAplicacao;
	}

	@Override
	public List<TipoObjetoAplicacao> pesquisaPorInspecao(Inspecao inspecao)
			throws ServiceException {
		try {
			return inspecaoObjetoAplicacaoDao.pesquisaPorInspecao(inspecao);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
