package org.java.spring.db.service;

import org.java.spring.db.pojo.ContactMessage;
import org.java.spring.db.repo.ContactMessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactMessageService {
	
	@Autowired
	private ContactMessageRepo cmRepo;
	
	public ContactMessage saveMessage(ContactMessage contactMessage) {
		return cmRepo.save(contactMessage);
    }

}
