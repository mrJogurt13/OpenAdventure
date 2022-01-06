package mj.openadventure.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Represents one story page in the story graph
 *
 * @author Andi
 */
public class StoryNode {

    private String text;
    private final HashMap<String, StoryNode> references;

    /**
     * Creates a StoryNode with the given text and no initial references.
     *
     * @param text main text of this story node (immutable after creation)
     */
    public StoryNode(String text) {
        this.text = text;
        this.references = new HashMap<>();
    }

    /**
     * Creates an empty StoryNode with no initial references.
     */
    public StoryNode() {
        this.text = null;
        this.references = new HashMap<>();
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
     * Adds a reference to this StoryNode
     *
     * @param link name of the link/command for the reference
     * @param child StoryNode that the reference points to
     */
    public void addChild(String link, StoryNode child) {
        this.references.put(link, child);
    }

    /**
     * Returns a list of all StoryNodes that this StoryNode references to
     *
     * @return List of StoryNodes
     */
    public List<StoryNode> getChildren() {
        return new ArrayList<>(this.references.values());
    }

    /**
     * Returns a map of all command references from this story node
     *
     * @return reference map
     */
    public HashMap<String, StoryNode> getReferenceMap() {
        return this.references;
    }

    /**
     * Checks if this StoryPage contains a link with the given name.
     * This method can be used to check if a command that the user entered
     * has been defined in this story page, in order to check if the command
     * is valid
     *
     * @param link link/command name
     * @return true if it contains this link, false otherwise
     */
    public boolean hasLink(String link) {
        return this.references.containsKey(link);
    }

}
