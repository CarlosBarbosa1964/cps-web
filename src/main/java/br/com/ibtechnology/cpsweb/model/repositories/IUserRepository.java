package br.com.ibtechnology.cpsweb.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.ibtechnology.cpsweb.model.entities.UserEntity;

@Service
public interface IUserRepository extends JpaRepository<UserEntity, Long> {

	public UserEntity findByUsernameAndPassword(String username, String password);
	
	public UserEntity findByUsername(String username);

}
