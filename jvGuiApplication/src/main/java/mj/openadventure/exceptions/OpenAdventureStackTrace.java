package mj.openadventure.exceptions;

/**
 * Class for Exception message creation
 */
public class OpenAdventureStackTrace {

    /**
     * Creates a String from the StackTrace of a given Exception.
     *
     * @param e Thrown Exception.
     * @return StackTrace of the Exception as a String.
     */
    public static String getCustomExceptionMessageFromStackTrace(Exception e){
        StringBuilder sb = new StringBuilder(e + "\r\n");
        for(StackTraceElement ste : e.getStackTrace()){
            sb.append(ste.toString()).append("\r\n");
        }
        return sb.toString();
    }
}
