package resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Service;
import repository.ProductRepository;
import domain.Product;
import exception.DataAccessException;


@Service
@EntityScan("domain") 
public class ProductService 
{
   private ProductRepository prodRepo;
   
   @Autowired
   public ProductService(ProductRepository prodRepo)
   {
	   this.prodRepo = prodRepo;
	   
   }
   
   
   
   public void persistProducts(List<Product> products)
   {
	   System.out.println("persistProducts ");
	   
	      for (Product prod:products)
	      {
		     prodRepo.save(prod);
	      }
	  
   }
   
   
   public void modifyProduct(Product prod, int id)
   {
	   System.out.println("modifyProduct");
	   Optional<Product> product = prodRepo.findById(new Long(Integer.toUnsignedLong(id)));
	   if (product.isPresent())
	   {
		   prodRepo.save(prod);
	   }
	   else
	   {
		   throw new DataAccessException("Product id " + id + " not found");
	   }
	  
   }
   
   public Iterable<Product>  findAllProducts()
   {
	   Iterable<Product> prods  = prodRepo.findAll();
	   
	   for(Product prod:prods)
	   {
		   System.out.println("Prod name " + prod.getName() + " description: " + prod.getDescription() + " price: " + prod.getPrice());
	   }
	   
	   return prods;
   }
   
   
   public Product findProduct(int id)
   {
	   Product prod = prodRepo.findById(id);
	   
	   System.out.println("Prod name " + prod.getName() + " description: " + prod.getDescription() + " price: " + prod.getPrice());
   
	   
	   return prod;
   }
   
   
   
   
   public List<Product> getProductMoreExpensiveThan(int price, String name) 
   {
	   System.out.println("get product for " + name + price);
	   List<Product> prods = prodRepo.getProductMoreExpensiveThan(price, name);
	   
	   return prods;
   }
   
   
   
   
   
   
   
}
