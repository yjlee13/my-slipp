package net.slipp.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	// testetstset
	private List<User> users = new ArrayList<User>();
	
	@RequestMapping("/create")
	public String create(User user) {
		System.out.println("User = "+ user);
		users.add(user);
		return "redirect:/list";
	}
	
	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("users",users);
		return "list";
	}
}
