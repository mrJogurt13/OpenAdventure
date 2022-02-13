package mj.openadventure.openadventuregui;

import mj.openadventure.exceptions.ConfigParsingException;
import mj.openadventure.globals.RuntimeVars;
import mj.openadventure.io.ConfigParser;

public class OpenAdventure {

    public static void main(String[] args) {
        RuntimeVars rv = new RuntimeVars(System.getProperty("user.dir") + "/src/main");
        try {
            new ConfigParser().loadConfigFile();
            System.out.println(new ConfigParser().getConfigNode("testNode"));
        } catch (ConfigParsingException e) {
            //TODO: handle exception
            e.printStackTrace();
        }
    }
}