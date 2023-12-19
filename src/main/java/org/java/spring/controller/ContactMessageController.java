package org.java.spring.controller;

import org.java.spring.db.service.ContactMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public class ContactMessageController {
	
	@Autowired
	private ContactMessageService messageService;
	
	@PostMapping("/messages/delete/{id}")
	public String deleteMessage(@PathVariable int id) {
		messageService.deleteById(id);
		return "redirect:/index-messages";
	}

}
