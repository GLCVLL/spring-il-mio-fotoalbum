package org.java.spring.restcontroller;

import java.util.List;

import org.java.spring.db.pojo.Photo;
import org.java.spring.db.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/photos")
public class PhotoRestController {

    @Autowired
    private PhotoService photoService;

    @GetMapping
    public ResponseEntity<List<Photo>> getAllPhotos() {
        List<Photo> photos = photoService.findAll();
        return new ResponseEntity<>(photos, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Photo> getPhoto(@PathVariable int id) {
        Photo photo = photoService.findById(id);
        if (photo == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(photo, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Photo> createPhoto(@RequestBody Photo photo) {
        photoService.save(photo);
        return new ResponseEntity<>(photo, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Photo> updatePhoto(@PathVariable int id, @RequestBody Photo updatedPhoto) {
        Photo existingPhoto = photoService.findById(id);
        if (existingPhoto == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        existingPhoto.setTitle(updatedPhoto.getTitle());
        existingPhoto.setDescription(updatedPhoto.getDescription());

        photoService.save(existingPhoto);
        return new ResponseEntity<>(existingPhoto, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePhoto(@PathVariable int id) {
        Photo photo = photoService.findById(id);
        if (photo == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        photoService.delete(photo);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
