package mj.openadventure.exceptions;

import static mj.openadventure.exceptions.OpenAdventureStackTrace.getCustomExceptionMessageFromStackTrace;

/**
 * Parent-class for all custom Exception classes
 *
 * @author corb
 */
public class OpenAdventureException extends Exception{
    //TODO: Exception handling when application is in use (error popup reference issue #17)

    /**
     * Constructor for StackTrace as message.
     *
     * @param e Exception that was thrown.
     */
    public OpenAdventureException(Exception e){
        super(getCustomExceptionMessageFromStackTrace(e));
    }

    /**
     * Constructor for String as message.
     *
     * @param msg String message that is defined when thrown.
     */
    public OpenAdventureException(String msg){
        super(msg);
    }

    /**
     * Constructor for String and StackTrace as message.
     *
     * @param msg String message that is defined when thrown.
     * @param e Exception that was thrown.
     */
    public OpenAdventureException(String msg, Exception e){
        super(msg + "\r\n" + getCustomExceptionMessageFromStackTrace(e));
    }
}