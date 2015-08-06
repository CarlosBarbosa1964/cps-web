package br.com.ibtechnology.cpsweb.support.logs;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

import br.com.ibtechnology.cpsweb.model.repositories.IEventRepository;

@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Named(value = "logsMB")
public class LogsMB implements Serializable {

	private static final long serialVersionUID = 6901809111543160018L;
	
	@Inject
	private IEventRepository eventRepository;
	
	

}
