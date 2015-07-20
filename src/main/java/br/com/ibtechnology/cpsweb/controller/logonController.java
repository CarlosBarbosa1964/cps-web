package br.com.ibtechnology.cpsweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = { "/logon" })
public class logonController {
	
	@RequestMapping("/logon")
	public ModelAndView hello() {
		String message = "Welcome to Spring MVC 4.0!";

		return new ModelAndView("/logon/logon", "message", message);
	}

}
