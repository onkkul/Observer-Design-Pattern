package replicasystem.util;

import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.InvalidPathException;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {

    private File outputFile;
    private String appendLine;
    private BufferedWriter outputWriter;

    /**
    * Constructor for Results class, initializes empty output file
    * 
    * @exception IOException
    *
    * @return void
    */
    public Results(String outputFile){
        try {
            
            this.outputFile = new File(outputFile);

            if (this.outputFile.createNewFile()) {
                System.out.println("File created: " + this.outputFile.getName());
            }
            else {
                new FileWriter(outputFile, false).close();
            }
        } 
        catch (IOException fileCreationError) {
          fileCreationError.printStackTrace();
        } 
    }


    /**
    * Stores each single line for later persistance.
    * 
    * @exception None
    *
    * @return void
    */
    public void writeLine(String line){
        appendLine = appendLine + line;
    }


    /**
    * Helper function that calls functions to display results.
    *
    * @exception None
    *
    * @return void
    */
    public void persistResult(){
        appendLine = appendLine.replace("null", "");
        writeFile();
        displayOutput();
    }


    /**
    * Writes results in the output file
    * 
    * @exception IOException
    *
    * @return void
    */
    @Override
    public void writeFile(){
        try{
            this.outputWriter = new BufferedWriter(new FileWriter(this.outputFile, true));
            outputWriter.write(appendLine);
            outputWriter.close();
        }

        catch(IOException writeRotatedError){
            writeRotatedError.printStackTrace();
        }
        return;
    }


    /**
    * Writes results to console
    * 
    * @exception None
    *
    * @return void
    */
    @Override
    public void displayOutput(){
        System.out.println(appendLine);
        return;
    }
}