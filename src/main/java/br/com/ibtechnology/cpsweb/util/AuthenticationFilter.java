package br.com.ibtechnology.cpsweb.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.ibtechnology.cpsweb.model.entities.UserEntity;
import br.com.ibtechnology.cpsweb.model.repositories.IUserRepository;
import br.com.ibtechnology.cpsweb.support.util.MyUtil;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private MyUtil myUtil;

	@Autowired
	private FacesContext context;

	private UserEntity user;

	private String mensagem;

	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, BadCredentialsException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String _password = "";
		boolean loggedIn;

		try {
			_password = myUtil.CriptoPass(password);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e1) {
			mensagem = e1.getMessage();
			throw new BadCredentialsException(mensagem);
		}
		System.out.println("-------------------------------");
		System.out.println("AuthenticationFilter");
		System.out.println(username);
		System.out.println(_password);
		System.out.println("-------------------------------");

		try {
			this.user = new UserEntity();
			this.user = userRepository.findByUsernameAndPassword(username, _password);

			if (this.user != null) {

				Collection<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
				roles.add(new SimpleGrantedAuthority(this.user.getRole()));

				loggedIn = true;
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("currentUser",
						this.user.getUsername());
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("currentUserID",
						this.user.getId());
				request.getSession().setAttribute("userLogged", this.user);
				mensagem = this.getResourceProperty("labels", "login_wellcome") + ": " + this.user.getUsername();
				return new UsernamePasswordAuthenticationToken(this.user.getUsername(), this.user.getPassword(), roles);

			} else {
				mensagem = this.getResourceProperty("labels", "login_invalid");
				throw new BadCredentialsException(mensagem);
			}

		} catch (Exception e) {
			throw new BadCredentialsException(e.getMessage());
		}
	}

	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			Authentication authResult) throws IOException, ServletException {
		SecurityContextHolder.getContext().setAuthentication(authResult);
		request.getSession().setAttribute("msg", mensagem);
		response.sendRedirect("/pages/main.cps");
	}

	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		request.getSession().setAttribute("msg", mensagem);
		response.sendRedirect("/login.cps");
	}

	private String getResourceProperty(String resource, String label) {
		this.context = FacesContext.getCurrentInstance();
		Application application = this.context.getApplication();
		ResourceBundle bundle = application.getResourceBundle(this.context, resource);

		return bundle.getString(label);
	}
}