package com.ai.bdex.dataexchange.solrutil;

public class SearchException extends Exception{

    private static final long serialVersionUID = 8226088403886996564L;
    
    public SearchException(String message) {
        super(message);
    }

    public SearchException(String message, Throwable cause) {
        super(message, cause);
    }

}
