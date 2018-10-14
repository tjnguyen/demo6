package resources;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.DeferredResult;

import domain.Product;
import domain.ProductResponse;
import exception.DataAccessException;

@RestController
@RequestMapping(value="/demo")
public class ProductController 
{
   
   @Autowired
   private ProductService prodService;
   
   @Autowired
   private ProductValidator prodValidator;
   
   private ExecutorService  executor = Executors.newFixedThreadPool(10);
   
   
   
   @InitBinder("product") 
   public void setupBinder(WebDataBinder binder) 
   { 
      binder.addValidators(prodValidator); 
   } 

   @RequestMapping(method=RequestMethod.GET, value="/Hello/{name}", produces = "application/json")
   public ProductResponse helloText(@PathVariable("name") String name)
   {
	   System.out.println("calling helloText");
	   ProductResponse resp = new ProductResponse();
	   
	   resp.setMessage("Hello " + name + "!  How are you? " );
	   return resp;
   }
   
   @RequestMapping(method=RequestMethod.GET, value="/getAllProduct")
    public Iterable<Product> getProducts () throws DataAccessException
    {
	   Iterable<Product> products  = null;
	   try
	   {
	      System.out.println("retrieve all products");
	      products = prodService.findAllProducts();
	   }
	   catch(Exception ex)
	   {
		   throw new DataAccessException("Error retriving products from database");
	   }
    	
    	
    	return products;
    }  
   
   
   
   
   @RequestMapping(method=RequestMethod.POST, value="/saveProducts")
   public void saveProducts(@Valid List<Product> prods)
   {
	   prodService.persistProducts(prods);
	   
   }
   
   @RequestMapping(method=RequestMethod.PUT, value="/modifyProduct/{id}")
   public void modifyProduct(@Valid Product prod, @PathVariable("id") int id)
   {
	   prodService.modifyProduct(prod, id);
	   
   }
   
   @RequestMapping(method=RequestMethod.GET, value="/displayerror")
   public String showError()
   {
	   return "displayerror";
   }
   
   @RequestMapping(method=RequestMethod.GET, value="/throwException")
   public ResponseEntity<String> throwException ()
   {
	   throw new DataAccessException("Error accessing from database");
   }
   
   @ExceptionHandler(DataAccessException.class)
   @ResponseStatus(HttpStatus.BAD_GATEWAY)
   @ResponseBody ResponseEntity<String>
   handleProductException (DataAccessException ex, HttpServletRequest req)
   {
	   System.out.println("demo3 calling handleProductException");
	   req.setAttribute("javax.servlet.error.status_code",
						HttpStatus.INTERNAL_SERVER_ERROR.value()); 
	   req.setAttribute("exceptionMessage", ex.getMessage());
	   
	   ResponseEntity<String> errorInfo = new ResponseEntity<String>("External error" , HttpStatus.BAD_GATEWAY);
	   
	   return errorInfo;
	   

   }
   
   /*private void excecute()
   {
		RestTemplateBuilder restTemplateBuilder;
        RestTemplate  restTemplate = restTemplateBuilder.build();
		
	    restTemplate.getForObject("/{name}/details", Details.class, name);
   }*/
   
   
   @RequestMapping(value = "/deferred", method = RequestMethod.GET, produces = "text/html")
   
       public DeferredResult<String> executeSlowTask() {
   
           System.out.println("Request received");
   
           DeferredResult<String> deferredResult = new DeferredResult<>();
           
           deferredResult.onCompletion(() -> System.out.println(" IT COMPLETE"));
           new Thread(() -> {
               System.out.println("async task started");
               try {
                   Thread.sleep(2000);
                   deferredResult.setResult("test async result");
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               System.out.println("async task finished");
              
           }).start();

           return deferredResult;
   }
   
   
   @RequestMapping(value = "/testdefer", method = RequestMethod.GET, produces = "text/html")
   public DeferredResult<String> execute()
   {   
	   DeferredResult<String> deferredResult = new DeferredResult<>();
	   deferredResult.onCompletion(() -> System.out.println(" IT COMPLETE"));
	   
	   Future<String> f = executor.submit(new ItemService(deferredResult));
	   
	   return  deferredResult;
	   
   }

   
	   
}
