package mj.openadventure.globals;

import java.util.HashMap;
import java.util.Map;

/**
 * Global available variables needed at runtime.
 *
 * @author corb
 */
public class RuntimeVars {
    //region hardcoded partial paths

    /** Path from workDir to the resources package. */
    public static final String resources = "/resources/mj/openadventure/openadventuregui/";

    //endregion

    //region global paths

    /** Path to config files. */
    public static String configPath;

    /** Path to working directory. */
    public static String workDirPath;

    //endregion

    //region settings

    /** load config (cfg.xml) nodes into here to safe them for runtime */
    public static Map<String, String> config;

    //endregion

    /**
     * Takes working directory as parameter and set all necessary paths accordingly.
     *
     * @param workDir Path to the working directory.
     */
    public RuntimeVars(String workDir){
        this.workDirPath = workDir;
        this.configPath = workDir + resources + "configs";

        this.config = new HashMap<>();
    }
}
