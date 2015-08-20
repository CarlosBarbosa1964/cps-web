package br.com.ibtechnology.cpsweb.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import br.com.ibtechnology.cpsweb.model.entities.UserEntity;
import br.com.ibtechnology.cpsweb.model.repositories.IUserRepository;

@Repository
@Service
public class UserDAOImpl implements UserDAO {

//	@PersistenceContext
//	private EntityManager entityManager;

	@Autowired
	private IUserRepository repositoryUser;
	
	@Override
	public UserEntity login(String username, String password) {

		UserEntity u;
		u=repositoryUser.findByUsernameAndPassword(username, password);

		if (u == null ) {
			return null;
		}
		return u;
	}

	@Override
	public List<UserEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(UserEntity user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(UserEntity user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(UserEntity user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserEntity getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
