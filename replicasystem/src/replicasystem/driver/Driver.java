package replicasystem.driver;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.FileInputStream;

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
	private static void executeProcess(String inputFile, String modifyFile){
        Results[] all_results = new Results[3];
        try {

            TreeI treeHelper = new TreeHelper();

            FileProcessor fileProcessor = new FileProcessor(inputFile); 
            
            String line = fileProcessor.poll();
            while(line != null){
                treeHelper.parseInput(line);
                line = fileProcessor.poll();
            }

            fileProcessor = new FileProcessor(modifyFile); 
            
            line = fileProcessor.poll();
            while(line != null){
                treeHelper.modifyInput(line);
                line = fileProcessor.poll();
            }

            for (int i = 0; i < 3; i++){
                String filename = "out"+Integer.toString(i);
                Results result = new Results(filename);
                treeHelper.printNodes(result, treeHelper.getTree(i));
                all_results[i] = result;
                System.out.println("Tree " + i + " written succesfully to " + filename);
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

		if (((args.length != 7)) || (args[0].equals("${input}")) || (args[1].equals("${modify}")) || (args[2].equals("${out1}"))) {
			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", 3);
			System.exit(0);
		}

        fileNames = args;

        for (int i = 0; i<7;i++){
            System.out.println(fileNames[i]);
        }
		executeProcess(args[0], args[1]);
	}
}   


// alias 4='ant -buildfile replicasystem/src/build.xml run -Dinput="input.txt" -Dmodify="modify.txt" -Dmetrics="metrics.txt"'