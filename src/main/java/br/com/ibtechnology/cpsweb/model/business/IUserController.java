package br.com.ibtechnology.cpsweb.model.business;

import br.com.ibtechnology.cpsweb.model.entities.UserEntity;

public interface IUserController {

	public UserEntity getAuthenticatedUser();

}
