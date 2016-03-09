package com.vion.core.hibernate;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.engine.spi.EntityKey;
import org.hibernate.engine.spi.PersistenceContext;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.vion.core.dao.FinderResult;
import com.vion.core.dao.IGeneralDAO;
import com.vion.core.dao.finder.IFinder;
import com.vion.core.domain.entity.IEntity;
import com.vion.core.util.BeanUtils;

/**
 * <b>功能描述</b> <br>
 * 
 * @author YUJB
 * @date 2014年6月16日 上午9:14:21
 */
public class HibernateGeneralDAO implements IGeneralDAO {

	private SessionFactory sessionFactory;

	public HibernateGeneralDAO() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vion.core.dao.IGeneralDAO#batchRemove(T[])
	 */
	public <T extends IEntity> void batchRemove(T[] entitys) {
		for (T t : entitys) {
			remove(t);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vion.core.dao.IGeneralDAO#find(com.vion.core.dao.finder.IFinder)
	 */
	public FinderResult find(IFinder finder) {

		return new HibernateFinderResult(getSession(), finder);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vion.core.dao.IGeneralDAO#findAll(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public <T extends IEntity> List<T> findAll(Class<T> classType) {
		Criteria criteria = getSession().createCriteria(classType);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vion.core.dao.IGeneralDAO#getConnection()
	 */
	public Connection getConnection() throws SQLException {
		return SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vion.core.dao.IGeneralDAO#remove(java.lang.Object)
	 */
	public <T extends IEntity> void remove(T entity) {
		getSession().delete(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vion.core.dao.IGeneralDAO#removeById(java.io.Serializable,
	 * java.lang.Class)
	 */
	public <T extends IEntity> void removeById(Serializable id, Class<T> classType) {
		Object object = findById(classType, id);
		getSession().delete(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vion.core.dao.IGeneralDAO#save(java.lang.Object)
	 */
	public <T extends IEntity> Serializable save(T entity) {
		Serializable identifier = getEntityIdentifier(entity);
		if (identifier != null) {
			Object cacheEntity = getCacheEntity(entity.getClass(), identifier);
			if (cacheEntity != null) {
				org.springframework.beans.BeanUtils.copyProperties(entity, cacheEntity);
				getSession().merge(cacheEntity);
				return identifier;
			}
		}
		return getSession().save(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vion.core.dao.IGeneralDAO#batchSave(com.vion.core.domain.entity.IEntity
	 * [])
	 */
	@Override
	public <T extends IEntity> void batchSave(T[] entitys) {
		if (entitys.length > 20) {
			Session session = getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			for (int i = 0; i < entitys.length; i++) {
				session.save(entitys[i]);
				if (i % 20 == 0 || i == entitys.length - 1) {
					session.flush();
					session.clear();
				}
			}
			tx.commit();
			session.close();
		}
		else {
			for (T entity : entitys) {
				save(entity);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vion.core.dao.IGeneralDAO#saveOrUpdate(java.lang.Object)
	 */
	public <T extends IEntity> void saveOrUpdate(T entity) {
		getSession().saveOrUpdate(entity);
		getSession().update(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vion.core.hibernate.HibernateGeneralDAO#update(com.vion.core.domain
	 * .entity.IEntity)
	 */
	@Override
	public <T extends IEntity> void update(T entity) {
		Serializable identifier = getEntityIdentifier(entity);
		if (containsCacheEntity(entity.getClass(), identifier)) {
			getSession().merge(entity);
		}
		else {
			getSession().update(entity);
		}
	}

	protected Session getSession() {
		boolean isConfigCurrentSession = TransactionSynchronizationManager.hasResource(sessionFactory);
		if (!isConfigCurrentSession) {
			// 如果不是http请求的话,当前上下文中没有session 手动绑定session
			Session session = sessionFactory.openSession();
			SessionHolder sessionHolder = new SessionHolder(session);
			TransactionSynchronizationManager.bindResource(sessionFactory, sessionHolder);
		}
		return sessionFactory.getCurrentSession();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vion.core.dao.IGeneralDAO#findBySQL(java.lang.String)
	 */
	public FinderResult findBySQL(String sql) {
		return findBySQL(sql, null, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vion.core.dao.IGeneralDAO#findByQL(java.lang.String)
	 */
	public FinderResult findByJPQL(String ql) {

		return new HibernateHQLFinderResult(getSession(), ql);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vion.core.dao.IGeneralDAO#evict(com.vion.core.domain.entity.IEntity)
	 */
	public <T extends IEntity> void evict(T entity) {
		getSession().evict(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vion.core.dao.IGeneralDAO#findById(java.lang.Class,
	 * java.io.Serializable)
	 */
	@SuppressWarnings("unchecked")
	public <T extends IEntity> T findById(Class<T> classType, Serializable id) {
		return (T) getSession().get(classType, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vion.core.dao.IGeneralDAO#findByNamed(java.lang.String)
	 */
	public FinderResult findByNamedJPQL(String namedjpql) {
		return findByNamedJPQL(namedjpql, null, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vion.core.dao.IGeneralDAO#findByNamed(java.lang.String,
	 * java.lang.String[], java.lang.Object[])
	 */
	@Override
	public FinderResult findByNamedJPQL(String namedjpql, String[] parameterNames, Object[] parameterValues) {
		HibernateHQLFinderResult finderResult = new HibernateHQLFinderResult(getSession(), namedjpql, true);
		if (parameterNames != null) {
			finderResult.setParameterNames(parameterNames);
		}
		finderResult.setParameterValues(parameterValues);
		return finderResult;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vion.core.dao.IGeneralDAO#findByNamed(java.lang.String,
	 * java.lang.Object[])
	 */
	@Override
	public FinderResult findByNamedJPQL(String namedjpql, Object[] parameterValues) {
		return findByNamedJPQL(namedjpql, null, parameterValues);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vion.core.dao.IGeneralDAO#findBySQL(java.lang.String,
	 * java.lang.String[], java.lang.Object[])
	 */
	@Override
	public FinderResult findBySQL(String sql, String[] parameterNames, Object[] parameterVlaues) {
		HibernateSQLFinderResult finderResult = new HibernateSQLFinderResult(getSession(), sql);
		finderResult.setParameterNames(parameterNames);
		finderResult.setParameterValues(parameterVlaues);
		return finderResult;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vion.core.dao.IGeneralDAO#findBySQL(java.lang.String,
	 * java.lang.Object[])
	 */
	@Override
	public FinderResult findBySQL(String sql, Object[] parameterVlaues) {
		return findBySQL(sql, null, parameterVlaues);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vion.core.dao.IGeneralDAO#findByJPQL(java.lang.String,
	 * java.lang.String[], java.lang.Object[])
	 */
	@Override
	public FinderResult findByJPQL(String jpql, String[] parameterNames, Object[] parameterValues) {
		HibernateHQLFinderResult finderResult = new HibernateHQLFinderResult(getSession(), jpql);
		finderResult.setParameterNames(parameterNames);
		finderResult.setParameterValues(parameterValues);
		return finderResult;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vion.core.dao.IGeneralDAO#findByJPQL(java.lang.String,
	 * java.lang.Object[])
	 */
	@Override
	public FinderResult findByJPQL(String jpql, Object[] parameterVlaues) {
		return findByJPQL(jpql, null, parameterVlaues);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void executeHQL(String hql) {
		Query query = getSession().createQuery(hql);
		HibernateQueryHelper.setParams(query, null, null);
		query.executeUpdate();
	}

	public void executeHQL(String hql, Object[] parameterVlaues) {
		Query query = HibernateQueryHelper.getHQLQuery(getSession(), hql, null, parameterVlaues);
		HibernateQueryHelper.setParams(query, null, parameterVlaues);
		query.executeUpdate();		
	}

	@Override
	public void executeSQL(String sql) {
		Query query = HibernateQueryHelper.getSQLQuery(getSession(), sql, null, null);
		query.executeUpdate();
	}

	@Override
	public void executeSQL(String sql,Object[] parameterVlaues) {
		Query query = HibernateQueryHelper.getSQLQuery(getSession(), sql, null, parameterVlaues);
		HibernateQueryHelper.setParams(query, null, parameterVlaues);
		query.executeUpdate();
	}
	
	@Override
	public void excuteNamedHQL(String HQL) {
		Query query = getSession().getNamedQuery(HQL);
		query.executeUpdate();
	}

	public <T extends IEntity> void merge(T entity) {
		getSession().merge(entity);
	}

	public <T extends IEntity> boolean containsCacheEntity(Class<T> clazz, Serializable identifier) {
		Object entity = getCacheEntity(clazz, identifier);
		return entity != null;
	}

	public <T extends IEntity> Object getCacheEntity(Class<T> clazz, Serializable identifier) {
		SessionImplementor sessionImpl = (SessionImplementor) getSession();
		EntityPersister entityPersister = sessionImpl.getFactory().getEntityPersister(clazz.getName());
		PersistenceContext persistenceContext = sessionImpl.getPersistenceContext();
		EntityKey entityKey = new EntityKey(identifier, entityPersister);
		return persistenceContext.getEntity(entityKey);
	}

	public <T extends IEntity> Serializable getEntityIdentifier(T entity) {
		ClassMetadata meta = getSessionFactory().getClassMetadata(entity.getClass());
		String pkName = meta.getIdentifierPropertyName();
		return (Serializable) BeanUtils.getFieldValue(entity, pkName);
	}

	/* (non-Javadoc)
	 * @see com.vion.core.dao.IGeneralDAO#findByVQL(java.lang.String, java.lang.String)*/
	@Override
	public FinderResult findByVQL(String qlType, String vql) {
		return null;
	}
	

	/* (non-Javadoc)
	 * @see com.vion.core.dao.IGeneralDAO#batchMerge(com.vion.core.domain.entity.IEntity[])
	 */
	@Override
	public <T extends IEntity> void batchMerge(T[] entitys) {
		// TODO Auto-generated method stub
		if (entitys.length > 20) {
			Session session = getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			for (int i = 0; i < entitys.length; i++) {
				session.merge(entitys[i]);
				if (i % 20 == 0 || i == entitys.length - 1) {
					session.flush();
					session.clear();
				}
			}
			tx.commit();
			session.close();
		}
		else {
			for (T entity : entitys) {
				merge(entity);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.vion.core.dao.IGeneralDAO#batchUpdate(com.vion.core.domain.entity.IEntity[])
	 */
	@Override
	public <T extends IEntity> void batchUpdate(T[] entitys) {
		// TODO Auto-generated method stub
		if (entitys.length > 20) {
			Session session = getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			for (int i = 0; i < entitys.length; i++) {
				session.update(entitys[i]);
				if (i % 20 == 0 || i == entitys.length - 1) {
					session.flush();
					session.clear();
				}
			}
			tx.commit();
			session.close();
		}
		else {
			for (T entity : entitys) {
				update(entity);
			}
		}
	}
}
