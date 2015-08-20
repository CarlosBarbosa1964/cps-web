package br.com.ibtechnology.cpsweb.beans;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import br.com.ibtechnology.cpsweb.model.entities.UserEntity;
import br.com.ibtechnology.cpsweb.model.repositories.IUserRepository;
 
@Controller
@Scope("request")
public class Authenticator implements AuthenticationProvider {
    
	@Autowired
	private IUserRepository userRepository;
 
    @Autowired
    private UserSession session;
    
    @Autowired
	private FacesContext context;
 
    private String username;
    private String password;
 
    public String login() throws NoSuchAlgorithmException, UnsupportedEncodingException {
    	
    	RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
        boolean loggedIn = false;
        
    	String _password="";
        try {
        	_password = encriptPass(password);
            UserEntity user = this.userRepository.findByUsernameAndPassword(username, _password);
            if (user != null){
        		System.out.println("-------------------------------");
        		System.out.println("Autenticado");
        		System.out.println(username);
        		System.out.println(_password);
        		System.out.println("-------------------------------");
            	loggedIn = true;
            	msg = new FacesMessage(FacesMessage.SEVERITY_INFO, this.getResourceProperty("labels", "login_wellcome"), user.getUsername());
            loginSpringSecurity(user);
            session.setUser(user);
            }
            else
            {
                loggedIn = false;
                msg = new FacesMessage(FacesMessage.SEVERITY_WARN, this.getResourceProperty("labels", "login_fail"), this.getResourceProperty("labels", "login_invalid"));
            }
            FacesContext.getCurrentInstance().addMessage(null, msg);
            context.addCallbackParam("loggedIn", loggedIn);
            context.addCallbackParam("route", "./pages/main.cps");
            return "index";
        } catch (IllegalArgumentException ex) {
            message(ex.getMessage());
        }
        return "";
    }
 
    private void loginSpringSecurity(UserEntity user) {
    	
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

		grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                user.getUsername(), null, grantedAuthorities);
        token.setDetails(user);
 
        SecurityContextHolder.createEmptyContext();
        SecurityContextHolder.getContext().setAuthentication(token);
    }
 
    public String logout() {
        SecurityContextHolder.clearContext();
        session.setUser(null);
        return "index";
    }
 
    private void message(String message) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(message));
    }
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    @Override
    public Authentication authenticate(Authentication arg0)
            throws AuthenticationException {
        // TODO Auto-generated method stub
        return null;
    }
 
    @Override
    public boolean supports(Class<?> arg0) {
        // TODO Auto-generated method stub
        return false;
    }
    
	private String getResourceProperty(String resource, String label) {
		Application application = this.context.getApplication();
		ResourceBundle bundle = application.getResourceBundle(this.context, resource);

		return bundle.getString(label);
	}

	private String encriptPass(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
		byte messageDigest[] = algorithm.digest(password.getBytes("UTF-8"));
		StringBuilder hexString = new StringBuilder();
		for (byte b : messageDigest) {
			hexString.append(String.format("%02X", 0xFF & b));
		}
		String pass = hexString.toString();
		return pass;
	}

}