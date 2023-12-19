package org.java.spring;

import org.java.spring.auth.conf.AuthConf;
import org.java.spring.auth.db.pojo.Role;
import org.java.spring.auth.db.pojo.User;
import org.java.spring.auth.db.service.RoleService;
import org.java.spring.auth.db.service.UserService;
import org.java.spring.db.pojo.Category;
import org.java.spring.db.pojo.ContactMessage;
import org.java.spring.db.pojo.Photo;
import org.java.spring.db.service.CategoryService;
import org.java.spring.db.service.ContactMessageService;
import org.java.spring.db.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringIlMioFotoalbumApplication implements CommandLineRunner {
	
	@Autowired
	private PhotoService photoService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ContactMessageService contactMessaggeService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

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
		
//		Messages
		ContactMessage messaggio1 = new ContactMessage("indirizzo.mail.it", "Ciao mi piacciono le tue foto");
		ContactMessage messaggio2 = new ContactMessage("indirizzo.mail.com", "Che bella foto!");
		ContactMessage messaggio3 = new ContactMessage("indirizzo.mail.org", "WOW! GREAT! AWESOME!");
		ContactMessage messaggio4 = new ContactMessage("indirizzo.mail.eu", "Ciao potrei acquistare una tua foto?");
		ContactMessage messaggio5 = new ContactMessage("indirizzo.mail.io", "Bella foto!");
		
		
		contactMessaggeService.save(messaggio1);
		contactMessaggeService.save(messaggio2);				
		contactMessaggeService.save(messaggio3);
		contactMessaggeService.save(messaggio4);
		contactMessaggeService.save(messaggio5);
		
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
		
		
		// AUTH
	    
	    Role roleUser = new Role("USER");
		Role roleAdmin = new Role("ADMIN");
		Role roleSuperAdmin = new Role("SUPERADMIN");
		
		roleService.save(roleUser);
		roleService.save(roleAdmin);
		roleService.save(roleSuperAdmin);
		
		String password = AuthConf.passwordEncoder().encode("password");
		
		User heroUser = new User("heroUser", password, roleUser);
		User heroAdmin = new User("heroAdmin", password, roleAdmin);
		User heroSuperAdmin = new User("heroSuperAdmin", password, roleSuperAdmin);
		
		userService.save(heroUser);
		userService.save(heroAdmin);
		userService.save(heroSuperAdmin);
	}
}
