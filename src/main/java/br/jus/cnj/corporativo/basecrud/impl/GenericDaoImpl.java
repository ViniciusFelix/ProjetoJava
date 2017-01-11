package br.jus.cnj.corporativo.basecrud.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;

import br.jus.cnj.corporativo.basecrud.GenericDao;
import br.jus.cnj.utils.exception.DaoException;

public class GenericDaoImpl<T> implements GenericDao<T> {

	private Class<T> persistClass;

	@Autowired
	private SessionFactory sessionFactory;

	public GenericDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public GenericDaoImpl() {
		this.persistClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")	
	public T pesquisarPorId(Serializable id) throws DaoException {
		T a = (T) getSession().get(this.persistClass, id);
		return a;
	}

	@SuppressWarnings("unchecked")	
	public List<T> getAll() throws DaoException {
		List<T> a = getSession().createCriteria(this.persistClass).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return a;
	}
	
	@SuppressWarnings("unchecked")	
	public List<T> getAllOrdered() throws DaoException {
		List<T> a = getSession().createCriteria(this.persistClass).addOrder(Order.desc("id")).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return a;
	}

	@SuppressWarnings("unchecked")	
	public T persistirEntidade(T entity) throws DaoException {
		T a = (T) getSession().save(entity);
		getSession().flush();
		return a;
	}
	
	public void excluirEntidade(T entity) throws DaoException {
		getSession().getSessionFactory().getCurrentSession().delete(entity);
		getSession().flush();
	}

	public void updateEntidade(T entity) throws DaoException {
		getSession().getSessionFactory().getCurrentSession().merge(entity);
		getSession().flush();
	}
}
