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
public class RegisterDAOImpl implements RegisterDAO {

	@Autowired
	private SessionFactory factory;

	private static final Logger logger = Logger.getLogger(RegisterDAOImpl.class);

	public RegisterDAOImpl() {
		super();
		logger.info(this.getClass().getSimpleName() + "\t Object Created");
	}

	@Override
	public String save(RegisterEntity registerEntity) {
		logger.info(" register save method is invoked");
		Session session = null;
		try {
			session = factory.openSession();
			session.beginTransaction();
			session.save(registerEntity);
			session.getTransaction().commit();

		} catch (HibernateException e) {
			session.getTransaction().rollback();
			logger.error(e.getMessage(), e);
		} finally {
			if (Objects.nonNull(session)) {
				session.close();
				logger.info("Session is closed");
			} else {
				logger.info("session is not closed");
			}
		}
		return registerEntity.getRandomPassword();
	}

	@SuppressWarnings({ "rawtypes" })
	@Override
	public String checkUserId(RegisterEntity registerEntity) {
		Session session = null;
		logger.info("inside DAOIMPL checkUserId()" + registerEntity);
		try {
			session = factory.openSession();
			Query namedQuery = session.getNamedQuery("fetchUserId");
			namedQuery.setParameter("uID", registerEntity.getUserId());
			Object object = namedQuery.uniqueResult();
			registerEntity = (RegisterEntity) object;
			if (Objects.isNull(registerEntity)) {
				return "dataNotFound";

			} else {
				if (Objects.nonNull(registerEntity.getUserId())) {
					return registerEntity.getUserId();
				} else {
					return "dataNotFound";
				}
			}

		} catch (HibernateException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (Objects.nonNull(session)) {
				session.close();
				logger.info("Session is closed");
			} else {
				logger.info("Session is not closed");
			}
		}

		return registerEntity.getUserId();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String checkEmail(RegisterEntity registerEntity) {
		Session session = null;
		logger.info("inside DAOIMPL checkEmail()" + registerEntity);
		try {
			session = factory.openSession();
			Query namedQuery = session.getNamedQuery("fetchEmail");
			namedQuery.setParameter("email", registerEntity.getEmail());
			Object object = namedQuery.uniqueResult();
			registerEntity = (RegisterEntity) object;
			if (Objects.isNull(registerEntity)) {
				return "dataNotFound";

			} else {
				if (Objects.nonNull(registerEntity.getEmail())) {
					return registerEntity.getEmail();
				} else {
					return "dataNotFound";
				}
			}

		} catch (HibernateException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (Objects.nonNull(session)) {
				session.close();
				logger.info("Session is closed");
			} else {
				logger.info("Session is not closed");
			}
		}

		return registerEntity.getEmail();
	}

}
