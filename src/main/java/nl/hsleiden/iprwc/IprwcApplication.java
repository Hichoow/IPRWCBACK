package nl.hsleiden.iprwc;

import nl.hsleiden.iprwc.model.Product;
import nl.hsleiden.iprwc.model.Role;
import nl.hsleiden.iprwc.model.User;
import nl.hsleiden.iprwc.service.ProductService;
import nl.hsleiden.iprwc.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class IprwcApplication {

	public static void main(String[] args) {
		SpringApplication.run(IprwcApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserService userService , ProductService productService){
		return args -> {
			userService.createRole(new Role("ROLE_USER"));
			userService.createRole(new Role("ROLE_MANAGER"));
			userService.createRole(new Role("ROLE_ADMIN"));
			userService.createRole(new Role("ROLE_SUPER_ADMIN"));

			userService.create(new User("hichoow", "hichoow@gmail.com", "1234"));
			userService.create(new User("praque", "praque@gmail.com", "1234"));
			userService.create(new User("khalid1999", "khalid1999@gmail.com", "1234"));
			userService.create(new User("azdin070", "azdin070@gmail.com", "1234"));

			userService.addRoleToUser("hichoow", "ROLE_SUPER_ADMIN");
			userService.addRoleToUser("hichoow", "ROLE_USER");
			userService.addRoleToUser("praque", "ROLE_USER");
			userService.addRoleToUser("khalid1999", "ROLE_ADMIN");
			userService.addRoleToUser("azdin070", "ROLE_USER");

			productService.save(new Product("Haribo Starmix", 1.59, "https://www.kantinewinkel.nl/media/catalog/product/cache/3/image/9df78eab33525d08d6e5fb8d27136e95/4/5/453505.jpg"));
			productService.save(new Product("Haribo Happy Peaches", 1.49, "https://assets.haribo.com/image/upload/s--bN7_Fc4q--/ar_1852:2480,c_fill,f_auto,q_60/w_807/v1/consumer-sites/nl-nl/Products/Happy_Peaches_200g_share_size__1_-removebg.png"));
			productService.save(new Product("Skittles Party Pack", 2.59, "https://www.action.com/globalassets/cmsarticleimages/40/15/039D66FD0EDC472AAA028A8B465EDFFF.png/"));
			productService.save(new Product("Skittles", 3.99, "https://d1sca6vi4fbl8x.cloudfront.net/media/uploads/2018/03/08/42266ccd-f862-418d-ae6d-b24e94d43600-skittles-sweet-heat.png"));
			productService.save(new Product("Katja Appekoppen", 4.99, "https://d3r3h30p75xj6a.cloudfront.net/artikelen/67690_1_421802_637753565053477237.png"));
			productService.save(new Product("Katja Zurematten", 3.99, "https://static-images.jumbo.com/product_images/080520191923_141822ZK-1_360x360_2.png"));
			productService.save(new Product("Katja Biggetjes", 3.49, "https://static-images.jumbo.com/product_images/080420211415_422202ZK-1_360x360_2.png"));
			productService.save(new Product("Taki Fuego", 4.99, "https://donteattakis.ca/static/sac-fuego.cf8aa39e.png"));
			productService.save(new Product("Mike & Ike Tropical Typhoon", 6.00, "https://cdn.webshopapp.com/shops/225503/files/380404121/image.jpg"));
			productService.save(new Product("Mike & Ike Jolly Joes", 6.00, "https://cdn.webshopapp.com/shops/225503/files/371773584/image.jpg"));



		};
	}

}
