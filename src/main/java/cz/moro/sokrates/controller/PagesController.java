package cz.moro.sokrates.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller ser
 * @author Juraj Vlk
 *
 */
@Controller
public class PagesController {

	@RequestMapping(value = "/admin/about", method = RequestMethod.GET)
	public String about() {
		return "pages/about";
	}
}
