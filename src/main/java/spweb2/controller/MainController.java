package spweb2.controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spit.Spittle;

@Controller
@RequestMapping("/user")
public class MainController {

	@RequestMapping(method=RequestMethod.GET)
	public String mainCon(ModelMap mm){
		mm.addAttribute("userinfo", new Spittle(121, 3, "testuser", 0, "female"));
		System.out.println("main Controller");
		return "userInfo";
	}
	
}
