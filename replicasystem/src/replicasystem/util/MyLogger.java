// FIXME: replace XYZ with the package name for the assignment
package replicasystem.util;

public class MyLogger{

    // FIXME: Add more enum values as needed for the assignment
    public static enum DebugLevel {
        NONE, CONSTRUCTOR, FILE_PROCESSOR, TREEI, 
        TREE_HELPER, TREENODEI, STUDENT_RECORD
    };

    private static DebugLevel debugLevel;


    // FIXME: Add switch cases for all the levels
    public static void setDebugValue (int levelIn) {
        switch (levelIn) {
            case 2: debugLevel = DebugLevel.CONSTRUCTOR;    break;
            case 1: debugLevel = DebugLevel.FILE_PROCESSOR; break;
            case 3: debugLevel = DebugLevel.TREEI;          break;
            case 4: debugLevel = DebugLevel.TREE_HELPER;     break;
            case 5: debugLevel = DebugLevel.TREENODEI;      break;
            case 6: debugLevel = DebugLevel.STUDENT_RECORD; break;
            default: debugLevel = DebugLevel.NONE;          break;
        }
    }

    public static void setDebugValue (DebugLevel levelIn) {
    debugLevel = levelIn;
    }

    public static void writeMessage(String message, DebugLevel levelIn ) {
        if (levelIn == debugLevel)
            System.out.println(message);
    }

    public String toString() {
        return "The debug level has been set to the following " + debugLevel;
    }
}