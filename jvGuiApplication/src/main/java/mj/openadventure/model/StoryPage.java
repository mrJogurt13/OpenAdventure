package mj.openadventure.model;

import java.util.HashMap;

/**
 * Represents one story page parsed from a file
 */
public class StoryPage {

    private String text;
    private final HashMap<String, String> commands;

    /**
     * Creates a new Story Page with the given text
     * and an empty command map.
     *
     * @param text story text
     */
    public StoryPage(String text) {
        this.text = text;
        this.commands = new HashMap<>();
    }

    /**
     * Creates a new Story Page with no initial text
     * and an empty command map.
     */
    public StoryPage() {
        this.text = null;
        this.commands = new HashMap<>();
    }

    /**
     * Setter for the story text of this story node
     *
     * @param text story text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Returns the story text of this story node
     *
     * @return story text
     */
    public String getText() {
        return this.text;
    }

    /**
     * Gets the parsed command map
     * (key: command name, value: command target/path)
     *
     * @return command map
     */
    public HashMap<String, String> getCommands() {
        return commands;
    }

    /**
     * Adds an entry to the command map.
     *
     * @param cmdName name of the command
     * @param target target of the command (path)
     */
    public void addCommand(String cmdName, String target) {
        this.commands.put(cmdName, target);
    }
}
