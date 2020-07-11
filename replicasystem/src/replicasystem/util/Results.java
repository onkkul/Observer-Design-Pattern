package replicasystem.util;

import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.InvalidPathException;

import replicasystem.treenode.TreeNodeI;
import replicasystem.treenode.SubjectI;
import replicasystem.treenode.ObserverI;

public class Results implements FileDisplayInterface {

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
                new FileWriter(this.outputFile, false).close();
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
        this.appendLine = this.appendLine + line;
    }


    /**
    * Helper function to writeResult.
    *
    * @exception None
    *
    * @return void
    */
    public void persistResult(){
        this.appendLine = this.appendLine.replace("null", "");
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
        persistResult();
        try{
            this.outputWriter = new BufferedWriter(new FileWriter(this.outputFile, true));
            this.outputWriter.write(this.appendLine);
            this.outputWriter.close();
        }

        catch(IOException failedToWriteFile){
            failedToWriteFile.printStackTrace();
        }
        return;
    }
}