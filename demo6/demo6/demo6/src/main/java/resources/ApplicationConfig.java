package resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;
import domain.Product;


@SpringBootApplication
@EnableJpaRepositories("repository")
@CrossOrigin
public class ApplicationConfig implements CommandLineRunner 
{
	@Autowired
    ProductService prodService;

	
	public static void main(String[] args) throws Exception {
		
		System.out.println("Demo springboot");
		SpringApplication.run(ApplicationConfig.class, args);
	
	}
	
        @Override
        public void run(String... args) throws Exception 
        {
           System.out.println(" Running command lines");
           List<Product> products = new ArrayList<Product>();
           
           Product product = new Product("product1", "for sale", 12);
           Product product2 = new Product("product2", "for demo", 33);
           Product product3 = new Product("product3", "for sale", 15);
           Product product4 = new Product("product4", "for sale", 18);
          
           
           products.add(product);
           products.add(product2);
           products.add(product3);
           products.add(product4);
           
           prodService.persistProducts(products);
           
           
           
        }


}


