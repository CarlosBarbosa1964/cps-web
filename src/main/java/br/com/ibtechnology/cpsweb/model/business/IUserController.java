package br.com.ibtechnology.cpsweb.model.business;

import java.util.List;

import br.com.ibtechnology.cpsweb.model.entities.UserEntity;

public interface IUserController {

	public UserEntity findByUser(UserEntity user);
	public List<UserEntity> listAll();
	public UserEntity login(UserEntity user);

}
