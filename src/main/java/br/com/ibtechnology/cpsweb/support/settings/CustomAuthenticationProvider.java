package br.com.ibtechnology.cpsweb.support.settings;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;

import br.com.ibtechnology.cpsweb.model.entities.UserEntity;
import br.com.ibtechnology.cpsweb.model.repositories.IUserRepository;

@Controller
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Inject
	private IUserRepository userRepository;

	public CustomAuthenticationProvider() {
		super();
	}

	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		String _password = "";
		try {
			_password = criptPass(password);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("-------------------------------");
		System.out.println(username);
		System.out.println(_password);
		System.out.println("-------------------------------");

		UserEntity user = this.userRepository.findByUsernameAndPassword(username, _password);

		if (user != null) {
			if (user.isActive() && !user.isDeleted()) {
				List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

				grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));

				UserDetails userDetails = new User(username, password, grantedAuthorities);
				return new UsernamePasswordAuthenticationToken(userDetails, password, grantedAuthorities);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public boolean supports(final Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	private String criptPass(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
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
