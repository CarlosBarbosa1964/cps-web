package br.com.ibtechnology.cpsweb.support.settings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import br.com.ibtechnology.cpsweb.facade.UserDetailServiceImpl;

@Controller
@Scope("request")
public class MyAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserDetailServiceImpl userDetailService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// Login
		String login = (String) authentication.getPrincipal();

		// Excecao de usuario nao autenticado
		if (login == null || login.trim().length() == 0) {
			throw new AuthenticationServiceException("Erro na localização do LOGIN!!!");
		}

		// Aqui eu pego as informações do usuário pelo próprio spring security
		UserDetails user = userDetailService.loadUserByUsername(login);

		try {
			// Se o usuário não for null, eu o autentico no sistema
			if (user != null) {
				return new MyAuthenticationToken(user.getUsername(), user.getPassword(),
						user.getAuthorities());

			} else {
				throw new AuthenticationServiceException("Usuário não autenticado.");
			}

		} catch (AuthenticationServiceException e) {
			throw e;
		} catch (Throwable e) {
			throw new AuthenticationServiceException("Ocorreu um erro no ato da autenticação.", e);
		}
	}

	@Override
	public boolean supports(Class<? extends Object> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication)
				&& authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}