package org.java.spring.db.service;

import java.util.List;

import org.java.spring.db.pojo.Photo;
import org.java.spring.db.repo.PhotoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoService {
	
	@Autowired
	private PhotoRepo photoRepo;
	
	public List<Photo> findAll() {
		
		return photoRepo.findAll();
	}
	public Photo findById(int id) {
		
		return photoRepo.findById(id).get();
	}
	
    public List<Photo> findByTitle(String title) {
        return photoRepo.findByTitleContainingIgnoreCase(title);
    }
	
	public void save(Photo photo) {
		
		photoRepo.save(photo);
	}
	
	public void delete(Photo photo) {
		
		photoRepo.delete(photo);
	}
	public void deleteById(int id) {
		
		photoRepo.deleteById(id);
	}
	

}
