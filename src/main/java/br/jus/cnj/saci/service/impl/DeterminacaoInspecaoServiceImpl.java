package br.jus.cnj.saci.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.cnj.saci.dao.DeterminacaoInspecaoDAO;
import br.jus.cnj.saci.entity.DeterminacaoInspecao;
import br.jus.cnj.saci.service.DeterminacaoInspecaoService;
import br.jus.cnj.utils.exception.ServiceException;

	@Service("determinacaoInspecaoService")
	public class DeterminacaoInspecaoServiceImpl implements DeterminacaoInspecaoService {

		
		@Autowired
		private DeterminacaoInspecaoDAO determinacaoInspecaoDao;

		@Transactional
		public boolean persistirEntidade(DeterminacaoInspecao determinacaoInspecao) throws ServiceException {
			try {
				determinacaoInspecaoDao.persistirEntidade(determinacaoInspecao);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServiceException(e.getMessage());
			}
		}

		@Transactional
		public void excluirEntidade(DeterminacaoInspecao determinacaoInspecao) throws ServiceException {
			try {
				determinacaoInspecaoDao.excluirEntidade(determinacaoInspecao);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServiceException(e.getMessage());
			}
		}


		@Transactional
		public boolean updateEntidade(DeterminacaoInspecao determinacaoInspecao) throws ServiceException {
			try {
				determinacaoInspecaoDao.updateEntidade(determinacaoInspecao);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServiceException(e.getMessage());
			}

		}
}
