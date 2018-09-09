package net.slipp.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.slipp.domain.User;
import net.slipp.domain.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {
	

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/index")
	public String index() {
		return "/index";
	}
	
	@GetMapping("/loginForm")
	public String loginForm() {
		return "/user/login";
	}

	@PostMapping("/login")
	public String login(String userId, String password,HttpSession session) {
		//userId에 해당하는 회원이 데이터베이스에 존재해야한다
		User user = userRepository.findByUserId(userId);
		if(user == null) {
			System.out.println("로그인실패");
			return "redirect:/users/loginForm";
		}
		if (!password.equals(user.getPassword())) {
			System.out.println("로그인실패");
			return "redirect:/users/loginForm";
		}
		
		System.out.println("로그인성공");
		session.setAttribute("user", user);
		
		
		return "redirect:/";
	}
	
	@GetMapping("/form")
	public String form() {
		return "/user/form";
	}
	
	@PostMapping("")
	public String create(User user) {
		System.out.println("User = "+ user);
		userRepository.save(user);
		return "redirect:/users";
	}
	
	@GetMapping("")
	public String list(Model model) {
		model.addAttribute("users",userRepository.findAll());
		return "/user/list";
	}
	
	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable Long id, Model model) {   //id 맞춰줘야됨 ex) {userid} 이면 Long userid
		User user = userRepository.findById(id).get();
		model.addAttribute("user", user);
		return "/user/updateform";
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable Long id, User newUser) {
		User user = userRepository.findById(id).get();
		user.update(newUser);
		userRepository.save(user);
		return "redirect:/users";
	}
	
}



