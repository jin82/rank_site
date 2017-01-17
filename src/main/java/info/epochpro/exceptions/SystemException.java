package info.epochpro.exceptions;

/**
 * Created by jin on 2017/1/17.
 */
public class SystemException extends RuntimeException{

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemException(String message) {
        super(message);
    }
}
