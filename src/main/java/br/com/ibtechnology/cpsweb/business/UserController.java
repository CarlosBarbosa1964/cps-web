package br.com.ibtechnology.cpsweb.business;

import java.util.List;

import org.hibernate.Session;

import br.com.ibtechnology.cpsweb.model.business.IUserController;
import br.com.ibtechnology.cpsweb.model.entities.UserEntity;
import br.com.ibtechnology.cpsweb.util.HibernateUtil;

public class UserController implements IUserController {

	@Override
	public UserEntity findByUser(UserEntity user) {
		UserEntity model = null;
		Session session = HibernateUtil.getSession().getSessionFactory().getCurrentSession();
		return null;
	}

	@Override
	public List<UserEntity> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserEntity login(UserEntity user) {
		// TODO Auto-generated method stub
		return null;
	}


}
