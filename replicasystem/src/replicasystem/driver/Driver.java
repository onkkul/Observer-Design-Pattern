package replicasystem.driver;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.InvalidPathException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import replicasystem.util.FileProcessor;
import replicasystem.util.Results;
import replicasystem.treenode.TreeNodeI;
import replicasystem.treenode.StudentRecord;
import replicasystem.tree.TreeI;
import replicasystem.tree.TreeHelper;


/**
 * @author John Doe
 *
 */
public class Driver {
	private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 7;
    public static String[] fileNames;

    /**
    * Starts file processing
    * @exception InvalidPathException On invalid path string.
    * @exception SecurityException On not having necessary read permissions to the input file.
    * @exception FileNotFoundException On input file not found.
    * @exception IOException On any I/O errors while reading lines from input file.
    * @exception ArrayIndexOutOfBoundsException when BNumber or TreeIndex is invalid.
    * @return void
    */
	private static void executeProcess(String[] fileNames){
        Results[] all_results = new Results[3];
        try {

            TreeI treeHelper = new TreeHelper();

            FileProcessor fileProcessor = new FileProcessor(fileNames[0]); 
            
            String line = fileProcessor.poll();
            while(line != null){
                treeHelper.parseInput(line);
                line = fileProcessor.poll();
            }

            fileProcessor = new FileProcessor(fileNames[1]); 
            
            line = fileProcessor.poll();
            while(line != null){
                treeHelper.modifyInput(line);
                line = fileProcessor.poll();
            }

            for (int i = 0; i < 3; i++){
                // String filename = "out"+Integer.toString(i);
                Results result = new Results(fileNames[2+i]);
                treeHelper.printNodes(result, treeHelper.getTree(i));
                all_results[i] = result;
                System.out.println("Tree " + i + " written succesfully to " + fileNames[2+i]);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


	public static void main(String[] args) throws Exception {

		/*
		 * As the build.xml specifies the arguments as input,output or metrics, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
        String[] defaults = {"input", "modify", "out1", "out2", "out3", "error", "debug"};
        File errorFile = null;
		if (args.length != 7) {
			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", 7);
			System.exit(0);
		}
        for (int i = 0; i < 7; i++){
            if (args[i].equals(defaults[i])){
                System.err.printf("Error: Incorrect input file.");
                System.exit(0);
            }
            else
                System.out.println("Correct " + defaults[i] + " file :" + args[i]);
        }

        errorFile = new File(args[5]);
        FileOutputStream errorStream = new FileOutputStream(errorFile);
        PrintStream printToFile = new PrintStream(errorStream);
        System.setErr(printToFile);

		executeProcess(args);
	}
}   
