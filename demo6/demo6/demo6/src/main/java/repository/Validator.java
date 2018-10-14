package repository;

import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import domain.Product;

@RepositoryEventHandler
public class Validator 
{
   @HandleBeforeSave
   public void handleProductSave(Product p) throws Exception
   {
	   if (p.getName() == null)
	   {
		   throw new Exception("null name");
	   }
   }
}
