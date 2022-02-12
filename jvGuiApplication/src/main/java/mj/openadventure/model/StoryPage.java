package mj.openadventure.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents one story page parsed from a file.
 */
public class StoryPage {

    private final String name;
    private String text;
    private final HashMap<String, String> commands;

    /**
     * Creates a new Story Page with the given name and text,
     * and an empty command map.
     *
     * @param name name of this story page
     * @param text story text
     */
    public StoryPage(String name, String text) {
        this.name = name;
        this.text = text;
        this.commands = new HashMap<>();
    }

    /**
     * Creates a new Story Page with the given name, no initial text
     * and an empty command map.
     *
     * @param name name of this story page
     */
    public StoryPage(String name) {
        this.name = name;
        this.text = null;
        this.commands = new HashMap<>();
    }

    /**
     * Returns the name of this story page, without a '.txt' suffix
     *
     * @return story page name
     */
    public String getName() {
        return name;
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

    @Override
    public String toString() {
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append(String.format(">--- Page name: '%s' ---<\r\n", this.name));
        sBuilder.append(this.text);
        sBuilder.append("\r\n");
        sBuilder.append("- COMMANDS -\r\n");
        for (Map.Entry<String, String> cmdEntry : this.commands.entrySet()) {
            sBuilder.append(String.format("Name: '%s', Target: '%s'\r\n", cmdEntry.getKey(), cmdEntry.getValue()));
        }

        return sBuilder.toString();
    }
}
