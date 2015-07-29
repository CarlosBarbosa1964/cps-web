package br.com.ibtechnology.cpsweb.facade;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.ibtechnology.cpsweb.model.entities.UserEntity;
import br.com.ibtechnology.cpsweb.model.repositories.IUserRepository;

@SuppressWarnings("deprecation")
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class UserDetailServiceImpl implements UserDetailsService {

	@Inject
	private IUserRepository userRepository;

    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException  {
        UserEntity user = null;
        try {
            user = userRepository.findByUsername(login);
            if (user == null){
                return null;
            }
        } catch (Exception e) {
            return null;
        }

        return buildUserFromUserEntity(user);
    }

    private User buildUserFromUserEntity(UserEntity user) {
        User springUser = null;

        try {
            // convert model user to spring security user
            String login = user.getUsername();
            String password = user.getPassword();
            boolean enabled = user.isActive();
            boolean accountNonExpired = true;
            boolean credentialsNonExpired = true;
            boolean accountNonLocked = true;

            Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority(user.getRole()));

            springUser = new User(login, password, enabled,
                    accountNonExpired, credentialsNonExpired, accountNonLocked,
                    authorities);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return springUser;
    }
}