package mj.openadventure.model;

/**
 * Represents a command.
 * The name attribute represents how the command is executed.
 * The function attribute describes the next story part.
 *
 * @author corb
 */
public class Command {
    private final String name;
    private final String function;

    /**
     * creates new Command object with a command name and the function.
     *
     * @param name name of the command. This gets typed in.
     * @param function function of the command/next StoryNode-/StoryPage-name
     */
    public Command(String name, String function){
        this.name = name;
        this.function = function;
    }

    /**
     * Returns the name to check for correct execution/spelling errors.
     *
     * @return the name of the command
     */
    public String getName(){
        return this.name;
    }

    /**
     * Returns the function to get what story the command links to.
     *
     * @return the function of the command
     */
    public String getFunction(){
        return this.function;
    }
}
