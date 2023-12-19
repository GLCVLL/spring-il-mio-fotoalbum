package org.java.spring.controller;

import org.java.spring.auth.conf.AuthConf;
import org.java.spring.auth.db.pojo.Role;
import org.java.spring.auth.db.pojo.User;
import org.java.spring.auth.db.service.RoleService;
import org.java.spring.auth.db.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired 
	private RoleService roleService;
	
	@GetMapping("/register")
	
	public String routeCreate(Model model) {
		
		User user = new User();
		
		model.addAttribute("user",user);

		
		return "user/register-form";
	}
	
	@PostMapping("/register")
	public String storeUser(
			Model model,
			@Valid @ModelAttribute User user, 
			BindingResult bindingResult) {
			
			Role admin= roleService.findById(3);
		
			user.setPassword(AuthConf.passwordEncoder().encode(user.getPassword()));
			user.setRoles(admin);

			model.addAttribute("user", user);
		
			System.out.println("user:\n" + user);
			System.out.println("\n---------------\n");
			System.out.println("Error:\n" + bindingResult);
			
			if (bindingResult.hasErrors()) {
				
				model.addAttribute("user", user);;
				return "user/register-form";
			}
			
			try {
				
				userService.save(user);
			} catch(Exception e) {
				
				model.addAttribute("user", user);
				return "user/register-form";
			}
			
			return "redirect:/login";
			
		}

}
