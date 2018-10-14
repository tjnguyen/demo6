package resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import repository.ProductRepository;
import domain.Product;

@Component
public class ProductValidator implements Validator 
{
    ProductRepository prodRepo;
    
    @Autowired
    ProductValidator(ProductRepository prodRepo)
    {
    	this.prodRepo = prodRepo;
    }
	@Override
	public boolean supports(Class<?> clazz) {
		
		return Product.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) 
	{
		Product prod = (Product)target;
		
		if (true)
		{
			errors.reject("ALREADY_EXISTS");
		}
		
	}
}
				
		

