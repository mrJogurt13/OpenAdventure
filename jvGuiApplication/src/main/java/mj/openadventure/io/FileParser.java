package mj.openadventure.io;

import mj.openadventure.model.StoryPage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileParser {

    private final String cmdSeq = ">";

    private StoryPage storyPageFromFile(File inputFile) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(inputFile); // uses default charset

        StoryPage storyPage = new StoryPage();
        boolean firstStoryLine = true;
        StringBuilder mainText = new StringBuilder();

        while (fileScanner.hasNextLine()) {
            String currentLine = fileScanner.nextLine();
            if (currentLine.startsWith(cmdSeq)) {
                // command line detected
                currentLine = currentLine.substring(1);
                String[] cmdArray = currentLine.split(cmdSeq);

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
        }

        storyPage.setText(mainText.toString());
        return storyPage;
    }

}
