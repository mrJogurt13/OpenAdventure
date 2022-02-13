package mj.openadventure.io;

import mj.openadventure.exceptions.ConfigParsingException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static mj.openadventure.globals.RuntimeVars.*;
import static mj.openadventure.globals.enums.ConfigFiles.cfg;

/**
 * Parser class to parse config files into the application and handling prompts for config entries.
 * Saves all prompted entries in a global variable in RuntimeVars
 *
 * @author corb
 */
public class ConfigParser {

    /**
     * Loads single config node and returns its value.
     *
     * @param key key of the requested node.
     * @return value of the requested config node.
     * @throws ConfigParsingException when loading from file fails due to an Error while initialising DocumentBuilder or Document.
     */
    public String getConfigNode(String key) throws ConfigParsingException {
        if(config.containsKey(key)) {
            return config.get(key);
        }

        File configFile = file(cfg.name());
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document doc;

        try {
            builder = factory.newDocumentBuilder();
            doc = builder.parse(configFile);
            doc.getDocumentElement().normalize();
        } catch (Exception e){
            throw new ConfigParsingException(e);
        }
        return doc.getElementsByTagName(key).item(0).getTextContent();
    }

    /**
     * Loads the cfg.xml file into the corresponding global variable in RuntimeVars.
     *
     * @throws ConfigParsingException when loading from file fails due to an Error while initialising DocumentBuilder or Document.
     */
    public void loadConfigFile() throws ConfigParsingException {
        File configFile = file(cfg.name());
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document doc;

        try {
            builder = factory.newDocumentBuilder();
            doc = builder.parse(configFile);
            doc.getDocumentElement().normalize();
            List<Node> nodes = new ArrayList<>();
            for(int i=0; i<doc.getDocumentElement().getChildNodes().getLength(); i++){
                nodes.add(doc.getDocumentElement().getChildNodes().item(i));
            }
            for(Node n : nodes) {
                for(int i=0;i<n.getChildNodes().getLength();i++) {
                    Node configNode = n.getChildNodes().item(i);
                    if (!config.containsKey(configNode.getNodeName())) {
                        config.put(configNode.getNodeName(), configNode.getTextContent());
                    }
                }
            }
        } catch (Exception e){
            throw new ConfigParsingException(e);
        }
    }

    /**
     * Creates new File object for a config file with a given name.
     *
     * @param fileName Name of the config file.
     * @return new File object.
     */
    private File file(String fileName){
        return new File(configPath, fileName + ".xml");
    }
}
