package br.com.ibtechnology.cpsweb.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.ibtechnology.cpsweb.model.entities.UserEntity;

@Component
@Scope("session")
public class UserSession {
    private UserEntity user;
 
    public UserEntity getUser() {
        return user;
    }
 
    public void setUser(UserEntity user) {
        this.user = user;
    }
     
    public boolean isLoggedIn(){
        return user != null;
    }
}