package mj.openadventure.exceptions;

/**
 * Exception for errors in the parsing of configs.
 *
 * @author corb
 */
public class ConfigParsingException extends OpenAdventureException {
    public ConfigParsingException(Exception e){
        super(e);
    }
}
