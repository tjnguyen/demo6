package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="Product")
@NamedQuery(name="Product.getProductMoreExpensiveThan",
            query="select u from Product u where u.price >  ?1 and u.name = ?2")
public class Product 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, unique=true)
	@Size(min = 2, max = 14)
	private String name;
	
	@Column(nullable = false)
    private String description;
   
    @Column(nullable = false)
    private int price;
   
  
   
   protected Product() {}
   
   public Product(String name, String description, int price)
   {
	   this.name = name;
	   this.description = description;
	   this.price = price;
   }
   
  

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public int getPrice() {
	return price;
}

public void setPrice(int price) {
	this.price = price;
}

public Long getId() {
	return id;
}


   
   
}
