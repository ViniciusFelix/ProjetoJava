package br.jus.cnj.corporativo.basecrud;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.jus.cnj.saci.entity.TipoObjetoAplicacao;
import br.jus.cnj.utils.exception.DaoException;

public interface GenericDao<T> {

	public T pesquisarPorId(Serializable id) throws DaoException;

	public List<T> getAll() throws DaoException;
	
	public List<T> getAllOrdered() throws DaoException;

	public T persistirEntidade(T entity) throws DaoException;

	public void excluirEntidade(T entity) throws DaoException;

	public void updateEntidade(T entity) throws DaoException;

}
