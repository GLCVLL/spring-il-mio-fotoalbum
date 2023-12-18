package org.java.spring.restcontroller;

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

    @PostMapping
    public ResponseEntity<ContactMessage> submitContactMessage(@RequestBody ContactMessage contactMessage) {
        return new ResponseEntity<>(contactMessageService.saveMessage(contactMessage), HttpStatus.CREATED);
    }

}
