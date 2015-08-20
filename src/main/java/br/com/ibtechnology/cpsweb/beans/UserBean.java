package br.com.ibtechnology.cpsweb.beans;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.ibtechnology.cpsweb.model.entities.UserEntity;

public class UserBean {
 
    private UserEntity userLogged;
 
    public UserEntity getUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        userLogged = (UserEntity) session.getAttribute("userLogged");
 
        return userLogged;
    }
 
    public void setUser(UserEntity user) {
        this.userLogged = user;
    }
}