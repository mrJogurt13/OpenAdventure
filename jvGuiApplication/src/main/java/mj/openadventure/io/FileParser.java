package mj.openadventure.io;

import mj.openadventure.exceptions.StoryFileMalformedException;
import mj.openadventure.model.StoryPage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FileParser {

    private final String cmdSeq = "<#>";

    /**
     * Parses all files of a given directory and returns a list of respective
     * StoryPage representations.
     * If any files could not be parsed, the list contains one or multiple null entries,
     * one for each file that could not be parsed.
     *
     * @param dirPath Path of the directory
     * @return List of StoryPage representations
     * @throws IOException if an I/O error is thrown when accessing the starting file
     */
    public List<StoryPage> storyPagesFromDir(Path dirPath) throws IOException {
        return Files.walk(dirPath).filter(Files::isRegularFile).map(path -> {
            try {
                return storyPageFromFile(path.toFile());
            } catch (FileNotFoundException | StoryFileMalformedException e) {
                e.printStackTrace();
                return null;
            }
        }).collect(Collectors.toList());
    }

    /***
     * Parses a file and returns a respective StoryPage representation
     *
     * @param inputFile File that should be parsed
     * @return StoryPage representation
     * @throws FileNotFoundException if the given file does not exist
     */
    public StoryPage storyPageFromFile(File inputFile) throws FileNotFoundException, StoryFileMalformedException {
        Scanner fileScanner = new Scanner(inputFile); // uses default charset

        // remove potential .txt suffix from story page name
        String pageName = inputFile.getName();
        if (pageName.endsWith(".txt")) {
            pageName = pageName.substring(0, pageName.length() - 4);
        }
        StoryPage storyPage = new StoryPage(pageName);
        int lineNr = 1;
        boolean firstStoryLine = true;
        StringBuilder mainText = new StringBuilder();

        while (fileScanner.hasNextLine()) {
            String currentLine = fileScanner.nextLine();
            if (currentLine.startsWith(cmdSeq)) {
                // command line detected
                currentLine = currentLine.substring(cmdSeq.length());
                String[] cmdArray = currentLine.split(cmdSeq, 2);

                if (cmdArray.length != 2 || cmdArray[0].isEmpty() || cmdArray[1].isEmpty()) {
                    String errMsg = String.format("Syntax error in file %s at line %d:\r\n", inputFile.getName(), lineNr) +
                            "Command definition must include exactly 2 arguments\r\n";
                    throw new StoryFileMalformedException(errMsg);
                }

                storyPage.addCommand(cmdArray[0], cmdArray[1]);
            } else {
                // story line
                if (firstStoryLine) {
                    // first story line: do not prepend line break
                    mainText.append(currentLine);
                    firstStoryLine = false;
                } else {
                    // subsequent story lines: prepend line break
                    mainText.append("\r\n");
                    mainText.append(currentLine);
                }
            }
            lineNr++;
        }

        String storyText = mainText.toString();
        // check validity of story page
        if (storyText.length() <= 0) {
            // no story text
            throw new StoryFileMalformedException(
                    String.format("No story text in file %s detected", inputFile.getName()));
        }
        if (storyPage.getCommands().isEmpty()) {
            // no commands
            // TODO allow no commands for end pages
            throw new StoryFileMalformedException(
                    String.format("No commands in file %s detected", inputFile.getName()));
        }

        storyPage.setText(storyText);
        return storyPage;
    }

}
