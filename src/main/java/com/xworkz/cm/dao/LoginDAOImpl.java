package com.xworkz.cm.dao;

import java.util.Objects;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xworkz.cm.entity.RegisterEntity;

@Component
public class LoginDAOImpl implements LoginDAO {

	@Autowired
	private SessionFactory factory;

	private static final Logger logger = Logger.getLogger(LoginDAOImpl.class);

	public LoginDAOImpl() {
		super();
		logger.info(this.getClass().getSimpleName() + "\t Object Created");
	}

	@SuppressWarnings("rawtypes")
	@Override
	public RegisterEntity getRegisterEntity(String email) {
		logger.info("invoked LoginDAOImpl  getRegisterEntity()");
		Session session = null;
		RegisterEntity registerEntity = null;

		try {
			session = factory.openSession();
			Query namedQuery = session.getNamedQuery("fetchByEmail");
			namedQuery.setParameter("email", email);
			Object object = namedQuery.uniqueResult();
			registerEntity = (RegisterEntity) object;
			if (registerEntity != null) {
				System.out.println("data found");
				return registerEntity;

			}

		} catch (HibernateException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (Objects.nonNull(session)) {
				logger.info("Session is closed");
				session.close();
			} else {
				logger.info("Session is not closed");
			}
		}
		return registerEntity;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Integer updateLoginCount(Integer noOfLoginAttempt, Integer regID) {
		logger.info("Invoked updateLoginCount()");

		Session session = null;
		Integer update = 0;
		try {
			session = factory.openSession();
			session.beginTransaction();
			Query namedQuery = session.getNamedQuery("updateLoginCount");
			namedQuery.setParameter("count", noOfLoginAttempt);
			namedQuery.setParameter("rid", regID);
			update = namedQuery.executeUpdate();
			session.getTransaction().commit();
			logger.info("login count is updated");

		} catch (HibernateException e) {
			session.getTransaction().rollback();
			logger.info(e.getMessage(), e);
		} finally {
			if (Objects.nonNull(session)) {
				session.close();
				logger.info("Session is closed");
			} else {
				logger.info("Session is not closeds");
			}
		}
		return update;
	}

}
