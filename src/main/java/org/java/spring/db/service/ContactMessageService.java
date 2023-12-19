package org.java.spring.db.service;

import java.util.List;

import org.java.spring.db.pojo.ContactMessage;
import org.java.spring.db.repo.ContactMessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactMessageService {
	
	@Autowired
	private ContactMessageRepo cmRepo;
	
	public List<ContactMessage> findAll() {
		return cmRepo.findAll();
	}
	public ContactMessage findById(int id) {
		return cmRepo.findById(id).get();
	}
	public void save(ContactMessage contactMessage) {
		cmRepo.save(contactMessage);
	}
	
    public void delete(ContactMessage contactMessage) {
		
    	cmRepo.delete(contactMessage);
	}

	public void deleteById(int id) {
		cmRepo.deleteById(id);
	}

}
