package br.com.ibtechnology.cpsweb.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ibtechnology.cpsweb.dao.UserDAO;
import br.com.ibtechnology.cpsweb.model.entities.UserEntity;
import br.com.ibtechnology.cpsweb.service.LoginService;
import br.com.ibtechnology.cpsweb.support.util.MyUtil;
 
@Service
@Transactional
public class LoginServiceImpl implements LoginService {
	
    @Autowired
    private UserDAO dao;
    
	@Autowired
	private MyUtil myUtil;

	@Autowired
	private FacesContext context;

    @Override
    public UserEntity login(String username, String password)
            throws IllegalArgumentException {
        if (isEmptyOrNull(username) || isEmptyOrNull(password)) {
            throw new IllegalArgumentException(
            		this.getResourceProperty("labels", "login_usernameorpasswordempty"));
        }
        
        try {
			password = myUtil.CriptoPass(password);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        UserEntity u = dao.login(username, password);
         
        if (u == null) {
            throw new IllegalArgumentException(
            		this.getResourceProperty("labels", "login_invalid"));
        }
        return u;
    }
 
    private boolean isEmptyOrNull(String s) {
        return s == null || s.equals("");
    }
    
	private String getResourceProperty(String resource, String label) {
		this.context = FacesContext.getCurrentInstance();
		Application application = this.context.getApplication();
		ResourceBundle bundle = application.getResourceBundle(this.context, resource);

		return bundle.getString(label);
	}

}