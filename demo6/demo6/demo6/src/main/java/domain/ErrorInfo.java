package domain;

import org.springframework.dao.DataAccessException;

public class ErrorInfo 
{

    private  String url;
    private  String ex;

    public ErrorInfo(String url, Exception ex) 
    {
        this.url = url;
        this.ex = ex.getLocalizedMessage();
   }
}