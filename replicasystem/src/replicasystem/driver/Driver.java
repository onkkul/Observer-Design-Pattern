package replicasystem.driver;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.FileInputStream;

import replicasystem.util.FileProcessor;
import replicasystem.treenode.TreeNodeI;
import replicasystem.treenode.TreeNode;

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
    *
    * @return void
    */
	// private static void executeProcess(String inputFile, String outputFile){
 //        try {

 //            FileProcessor fileProcessor = new FileProcessor(inputFile); 

 //            SimpleStateFactoryI stateFactoryIn = null;
 //            ContextI channel = new ChannelContext(stateFactoryIn, outputFile);
            
 //            String line = fileProcessor.poll();
 //            while(line != null){
 //                channel.parseInput(line);
 //                line = fileProcessor.poll();
 //            }

 //            channel.persistResult();
 //        }
 //        catch(Exception e){
 //          e.printStackTrace();
 //        }

	// }
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
		System.out.println("Hello World! Lets get started with the assignment");
		// executeProcess(args[0], args[1]);
	}
}   


// alias 4='ant -buildfile replicasystem/src/build.xml run -Dinput="input.txt" -Dmodify="modify.txt" -Dmetrics="metrics.txt"'