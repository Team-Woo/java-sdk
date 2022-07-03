package org.discordbots.api.client.io;

public class UnsuccessfulHttpException extends Exception {
    
    /**
    * If the http exception is unsuccess
    *
    * @param code the Http code
    * @param message the Http Exception message
    */
    public UnsuccessfulHttpException(int code, String message) {
        super("The server responded with code: " + code + ", message: " + message);
    }

}
