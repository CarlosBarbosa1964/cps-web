package br.com.ibtechnology.cpsweb.business;

import java.util.ResourceBundle;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

import br.com.ibtechnology.cpsweb.model.business.ILogController;
import br.com.ibtechnology.cpsweb.model.entities.EventEntity;
import br.com.ibtechnology.cpsweb.model.entities.UserEntity;
import br.com.ibtechnology.cpsweb.model.enums.KeywordLog;
import br.com.ibtechnology.cpsweb.model.enums.LevelLog;
import br.com.ibtechnology.cpsweb.model.repositories.IEventRepository;

@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Named(value = "logsController")
public class LogController implements ILogController {

	@Inject
	private IEventRepository eventRepository;

	@Inject
	private FacesContext context;

	private EventEntity event;

	private String message;
	
	private String messageResource;

	@Override
	public boolean salvarEvtUser(UserEntity user, LevelLog level, KeywordLog msg) {
		
		this.event = new EventEntity();
		
		this.message = createMessage(msg);
			return false;
	}

	private String createMessage(KeywordLog msg) {
		String _return = null;
		
		return _return;
	}


	private String getResourceProperty(String resource, String label) {
		context = FacesContext.getCurrentInstance();
		Application application = this.context.getApplication();
		ResourceBundle bundle = application.getResourceBundle(this.context, resource);

		return bundle.getString(label);
	}

}
