package org.java.spring.controller;

import java.util.List;

import org.java.spring.db.pojo.Category;
import org.java.spring.db.pojo.Photo;
import org.java.spring.db.service.CategoryService;
import org.java.spring.db.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
public class PhotoController {
	
	@Autowired
	private PhotoService photoService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public String getPhotos(Model model, @RequestParam(required = false) String title) {
		
		List<Photo> photos = title == null
				? photoService.findAll()
				: photoService.findByTitle(title);
		
		model.addAttribute("photos", photos);
		model.addAttribute("title", title);
		
		return "photos";
	}
	
	@GetMapping("/photos/{id}")
	public String getPhotos(Model model,
			@PathVariable int id) {
		
		Photo photo = photoService.findById(id);

		model.addAttribute("photo", photo);

		
		return "photo";
	}
	
	@GetMapping("/photos/create")
	public String createPhotoForm(Model model) {
	    Photo photo = new Photo();
	    List<Category> categories = categoryService.findAll();
	    model.addAttribute("photo", photo);
	    model.addAttribute("categories", categories);
	    return "newPhoto";
	}

	@PostMapping("/photos/create")
	public String createPhoto(@Valid @ModelAttribute Photo photo, BindingResult bindingResult, Model model) {
	    return savePhoto(photo, bindingResult, model);
	}

	@GetMapping("/photos/edit/{id}")
	public String editPhoto(Model model, @PathVariable int id) {
	    Photo photo = photoService.findById(id);
	    List<Category> categories = categoryService.findAll();

	    model.addAttribute("categories", categories);
	    model.addAttribute("photo", photo);

	    return "newPhoto";
	}

	@PostMapping("/photos/edit/{id}")
	public String updatePhoto(@Valid @ModelAttribute Photo photo, BindingResult bindingResult, Model model) {
	    return savePhoto(photo, bindingResult, model);
	}

	@PostMapping("/photos/delete/{id}")
	public String deletePhoto(@PathVariable int id, RedirectAttributes redirectAttributes) {

	    Photo photo = photoService.findById(id);
	    photoService.delete(photo);

	    redirectAttributes.addFlashAttribute("deletedPhoto", photo);

	    return "redirect:/";
	}

	private String savePhoto(@Valid @ModelAttribute Photo photo, BindingResult bindingResult, Model model) {
	    if (bindingResult.hasErrors()) {
	        return "newPhoto";
	    }

	    try {
	        photoService.save(photo);
	    } catch (Exception e) {
	        bindingResult.addError(new FieldError("photo", "title", photo.getTitle(), false, null, null, "Custom error message"));
	        return "newPhoto";
	    }

	    return "redirect:/";
	}

}
