package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {
    
    private File file;

    Controller(){
        this.file = new File(
                System.getProperty("user.home") + 
                File.separator +
                "output.txt"
            );
    }

    public void setFile(File newFile){
        this.file = newFile;
    };

    public File getFile(){
        return this.file;
    };

    public String getPath(){
        return this.file.getPath();
    };

    public void writeOnFile(final String text) throws IOException{
        try (PrintStream out = new PrintStream(file)) {
            out.print(text);
        }
    };
}
