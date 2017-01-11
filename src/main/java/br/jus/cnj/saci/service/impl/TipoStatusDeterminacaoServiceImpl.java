package br.jus.cnj.saci.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.cnj.saci.dao.TipoStatusDeterminacaoDAO;
import br.jus.cnj.saci.entity.TipoStatusDeterminacao;
import br.jus.cnj.saci.service.TipoStatusDeterminacaoService;
import br.jus.cnj.utils.exception.ServiceException;

@Service("tipoStatusDeterminacaoService")
public class TipoStatusDeterminacaoServiceImpl implements TipoStatusDeterminacaoService {
	
	@Autowired
	private TipoStatusDeterminacaoDAO tipoStatusDeterminacaoDao;

	@Transactional
	public List<TipoStatusDeterminacao> getAll() throws ServiceException {
		try {
			return tipoStatusDeterminacaoDao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

}
