package resources;

import java.util.concurrent.Callable;

import org.springframework.web.context.request.async.DeferredResult;

public class ItemService implements Callable<String>{
	
	private DeferredResult result = null;
	
	public ItemService(DeferredResult result)
	{
		this.result = result;
	}
	public String call()
	{
		result.setResult("Thu get result");
		return new String("this string from callable");
	}

}

