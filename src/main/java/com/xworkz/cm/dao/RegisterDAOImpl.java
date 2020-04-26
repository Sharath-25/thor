package com.xworkz.cm.dao;

import java.util.Objects;

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

	public RegisterDAOImpl() {
		super();
		System.out.println("Created\t" + this.getClass().getSimpleName());
	}

	@Override
	public String save(RegisterEntity registerEntity) {
		Session session = null;
		try {
			session = factory.openSession();
			session.beginTransaction();
			session.save(registerEntity);
			session.getTransaction().commit();

		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			if (Objects.nonNull(session)) {
				session.close();
			} else {
				System.out.println("Session not closed");
			}
		}
		return registerEntity.getRandomPassword();
	}

	@SuppressWarnings({ "rawtypes" })
	@Override
	public String checkUserId(RegisterEntity registerEntity) {
		Session session = null;
		System.out.println("inside DAOIMPL" + registerEntity);
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
			e.printStackTrace();
		} finally {
			if (Objects.nonNull(session)) {
				session.close();
			} else {
				System.out.println("Session not closed");
			}
		}

		return registerEntity.getUserId();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String checkEmail(RegisterEntity registerEntity) {
		Session session = null;
		System.out.println("inside DAOIMPL" + registerEntity);
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
			e.printStackTrace();
		} finally {
			if (Objects.nonNull(session)) {
				session.close();
			} else {
				System.out.println("Session not closed");
			}
		}

		return registerEntity.getEmail();
	}

}
