package org.java.spring;

import org.java.spring.db.pojo.Category;
import org.java.spring.db.pojo.Photo;
import org.java.spring.db.service.CategoryService;
import org.java.spring.db.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringIlMioFotoalbumApplication implements CommandLineRunner {
	
	@Autowired
	private PhotoService photoService;
	
	@Autowired
	private CategoryService categoryService;

	public static void main(String[] args) {
		SpringApplication.run(SpringIlMioFotoalbumApplication.class, args);
	}
	
	@Override
	public void run(String...args) throws Exception{
		
		//CATEGORIES
		
		Category nature = new Category("Nature");
		Category urbanArchitecture = new Category("Urban and Architecture");
		Category portrait = new Category("Portrait");
		Category travel = new Category("Travel");
		Category streetPhotography = new Category("Street Photography");
		Category sportsAction = new Category("Sports and Action");
		Category foodPhotography = new Category("Food Photography");
		Category stillLife = new Category("Still Life");
		Category abstractArt = new Category("Abstract");
		Category animalsWildlife = new Category("Pets and Wildlife");

		categoryService.save(nature);
		categoryService.save(urbanArchitecture);
		categoryService.save(portrait);
		categoryService.save(travel);
		categoryService.save(streetPhotography);
		categoryService.save(sportsAction);
		categoryService.save(foodPhotography);
		categoryService.save(stillLife);
		categoryService.save(abstractArt);
		categoryService.save(animalsWildlife);
		
		// PHOTOS
		
		photoService.save(new Photo("Sunset Over Mountains", "A breathtaking sunset view over the mountains", "https://picsum.photos/300?random=1", true, nature));
		photoService.save(new Photo("City Lights", "Night view of city lights and skyscrapers", "https://picsum.photos/300?random=2", true, urbanArchitecture));
		photoService.save(new Photo("Portrait of a Woman", "Black and white portrait of a young woman", "https://picsum.photos/300?random=3", true, portrait));
		photoService.save(new Photo("Ancient Ruins", "The ruins of an ancient temple in a forest", "https://picsum.photos/300?random=4", true, travel));
		photoService.save(new Photo("Urban Street Life", "Everyday street life captured in an urban setting", "https://picsum.photos/300?random=5", true, streetPhotography));
		photoService.save(new Photo("High Jump", "An athlete performing a high jump", "https://picsum.photos/300?random=6", true, sportsAction));
		photoService.save(new Photo("Gourmet Meal", "Artistically presented gourmet meal", "https://picsum.photos/300?random=7", true, foodPhotography));
		photoService.save(new Photo("Colorful Abstract", "A colorful and abstract painting", "https://picsum.photos/300?random=8", true, abstractArt));
		photoService.save(new Photo("Wildlife in Africa", "A lion roaming in the African savanna", "https://picsum.photos/300?random=9", true, animalsWildlife));	
	}

}
