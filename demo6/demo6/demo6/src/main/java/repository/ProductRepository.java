package repository;

import java.util.List;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import domain.Product;

@Repository
@EntityScan("domain") 
public interface ProductRepository extends CrudRepository<Product, Long>
{
	
	Page<Product> findByDescriptionContaining(String description, Pageable pageable);
	
	@Query("select u from Product u where u.price >  ?1 and u.name = ?2")
	List<Product>  getProductMoreExpensiveThan(int price, String name);
	
    Product findById(int id);

}
