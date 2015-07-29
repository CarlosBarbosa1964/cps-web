package br.com.ibtechnology.cpsweb.model.util;

import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

@Scope(value = WebApplicationContext.SCOPE_REQUEST)
@Named(value = "menuView")
public class MenuView {
	
	private MenuModel model;

	@Inject
	private FacesContext context;

    @PostConstruct
    public void init() {
        model = new DefaultMenuModel();
         
        //First submenu
        DefaultSubMenu firstSubmenu = new DefaultSubMenu(this.getResourceProperty("labels", "menu_dataset"));
         
        DefaultMenuItem item = new DefaultMenuItem(this.getResourceProperty("labels", "menu_user"));
        item.setUrl("/pages/user/userlist.cps");
        item.setIcon("ui-icon-person");
        firstSubmenu.addElement(item);
         
        item = new DefaultMenuItem(this.getResourceProperty("labels", "menu_group"));
        item.setUrl("/pages/group/grouplist.cps");
        item.setIcon("ui-icon-bullet");
        firstSubmenu.addElement(item);
         
        model.addElement(firstSubmenu);
         
        //Second submenu
        DefaultSubMenu secondSubmenu = new DefaultSubMenu(this.getResourceProperty("labels", "menu_settings"));
 
        item = new DefaultMenuItem(this.getResourceProperty("labels", "menu_language"));
        item.setIcon("ui-icon-wrench");
        item.setUrl("/pages/settings/language.cps");
        secondSubmenu.addElement(item);
        
        item = new DefaultMenuItem(this.getResourceProperty("labels", "logout"));
        item.setIcon("ui-icon-locked");
        item.setUrl("/logout");
        secondSubmenu.addElement(item);
 
        model.addElement(secondSubmenu);
    }
 
    public MenuModel getModel() {
        return model;
    }
    
	private String getResourceProperty(String resource, String label) {
		Application application = this.context.getApplication();
		ResourceBundle bundle = application.getResourceBundle(this.context, resource);

		return bundle.getString(label);
	}


}
