package mj.openadventure.io;

import mj.openadventure.model.Command;

/**
 * Parses raw commands into Command objects.
 *
 * @author corb
 */
public class CommandParser {

    /**
     * Parses a raw, single line string command into a Command Object
     *
     * @param cmdRaw command in its raw form (String in StoryPage)
     * @return parsed Command object
     */
    public Command parseSingleCmd(String cmdRaw){
        String[] cmdArr = cmdRaw.split(/*cfg command splitting string/character*/"");
        return new Command(cmdArr[0], cmdArr[1]);
    }
}
