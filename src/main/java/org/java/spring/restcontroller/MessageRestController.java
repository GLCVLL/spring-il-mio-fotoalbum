package org.java.spring.restcontroller;

import org.java.spring.auth.db.service.UserService;
import org.java.spring.db.pojo.ContactMessage;
import org.java.spring.db.service.ContactMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/contact")
public class MessageRestController {
	
	@Autowired
    private ContactMessageService contactMessageService;
	
	@Autowired
	private UserService userService;

	@PostMapping("create")
	public ResponseEntity<ContactMessage> create(@RequestBody ContactMessage contactMessage) {
		
		contactMessage.setUser(userService.findById(1));
		
		contactMessageService.save(contactMessage);
		
		return new ResponseEntity<>(contactMessage, HttpStatus.OK);
	}

}
