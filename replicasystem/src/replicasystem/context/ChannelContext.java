// package replicasystem.context;


// import java.util.HashMap;
// import java.util.Map;
// import java.util.Iterator;
// import java.util.Set;
// import java.util.Arrays;
// import java.util.List;
// import java.io.IOException;

// import replicasystem.util.Results;
// import replicasystem.context.ContextI;
// import replicasystem.state.StateI;
// import replicasystem.state.StateName;
// import replicasystem.state.factory.SimpleStateFactory;
// import replicasystem.state.factory.SimpleStateFactoryI;


// public class ChannelContext implements ContextI {

//     private StateI curState;
//     public String command;
//     public String[] details;
//     public int popularityScore = 0;
//     public Results result;
//     public int[] stats = new int[]{0, 0, 0, 0};
//     private Map<String, int[]> account = new HashMap<String, int[]>();
//     private Map<StateName, StateI> availableStates = new HashMap<StateName, StateI>();


//     /**
//     * Function to construct the channel.
//     *
//     * @exception None
//     *
//     * @return void
//     */
//     public ChannelContext(SimpleStateFactoryI stateFactoryIn, List<StateName> stateNames, String outputFile) {
//         int adLimit = 10;

//         stateFactoryIn = new SimpleStateFactory(this);
//         for (StateName each_state: stateNames){
//             curState = stateFactoryIn.createStateObjects(each_state, adLimit);
//             availableStates.put(each_state, curState);
//             adLimit = adLimit + 10;
//         }
//         curState = availableStates.get(StateName.UNPOPULAR);

//         this.result = new Results(outputFile);
//     }

//     /**
//     * Gets value from hash map.
//     *
//     * @exception IllegalArgumentException for details that are not in the hash map.
//     *
//     * @return integer array of previous stats of the video*/
//     @Override
//     public int[] editAccount(String action, String key, int[] value)
//         throws IllegalArgumentException{
//         if (action == "get"){    
//             if (account.containsKey(key)){
//                 value = account.get(key);
//             }
//             else{
//                 throw new IllegalArgumentException("Video not present");
//             }
//         }
//         if (action == "put"){
//             if (account.containsKey(key)){
//                 throw new IllegalArgumentException("Video Already Exists");
//             }
//             else{
//                account.put(key, value);  
//             }
//         }

//         if (action == "remove"){   
//             if (account.containsKey(key) == false){
//                 throw new IllegalArgumentException("Video not present");
//             }
//             else{
//                 account.remove(key);
//             }
//         }
//         if (action == "addM"){
//             account.put(key, value);
//         }
//         return value;
//     }


//     /**
//     * Function to calculate and update the popularity score of channel.
//     *
//     * @exception None
//     *
//     * @return int value of the popularity score
//     */
//     @Override
//     public int updateScore() throws ArithmeticException{
//         int[] totalCount = new int[4];
//         int start = 0;
//         int numberOfVideos = 0;
//         for (String name: account.keySet()){
//             String key = name.toString();
//             int[] value = account.get(name);  
//             for (int a:value){
//                 totalCount[start] = totalCount[start] + a;
//                 start++;
//             }
//             start = 0;
//             numberOfVideos++;
//         }
//         try{
//             if (numberOfVideos > 0){
//                 popularityScore = (totalCount[0] + 2 * (totalCount[1] - totalCount[2]))/numberOfVideos;
//             }
//         }
//         catch (ArithmeticException videoNotFoundError){
//             videoNotFoundError.printStackTrace();
//         }
//         return popularityScore;
//     }


//     /**
//     * Function to set current state of channel.
//     *
//     * @exception None
//     *
//     * @return void
//     */
//     @Override
//     public void setState(StateName newStateName){curState = availableStates.get(newStateName);   }
    

//     /**
//     * Function to add a video to the channel.
//     *
//     * @exception None
//     *
//     * @return void
//     */
//     @Override
//     public void addVideo(String[] details){     curState.addVideo(details);     }


//     /**
//     * Function to remove a video from a channel.
//     *
//     * @exception None
//     *
//     * @return void
//     */
//     @Override
//     public void removeVideo(String[] details){  curState.removeVideo(details);  }


//     /**
//     * Function to add stat details to a video on channel.
//     *
//     * @exception None
//     *
//     * @return void
//     */
//     @Override
//     public void addMetrics(String[] details){   curState.addMetrics(details);   }


//     /**
//     * Function to request an AD.
//     *
//     * @exception None
//     *
//     * @return void
//     */
//     @Override
//     public void adRequest(String[] deails){     curState.adRequest(details);    }


//     /**
//     * Initially parse input to send it to all states.
//     *
//     * @exception None
//     *
//     * @return void
//     */
//     @Override
//     public void parseInput(String inpt){
//         String[] arrOfKeywords = {"ADD_VIDEO", "METRICS__", "AD_REQUEST__", "REMOVE_VIDEO", "]",  "::", "[VIEWS=", ",LIKES=", ",DISLIKES=", "LEN="};

//         for (int i = 0; i< 10; i++){
//             if (inpt.contains(arrOfKeywords[i]) && i <4){   command = arrOfKeywords[i]; }
//             if (i < 6)      {   inpt = inpt.replace(arrOfKeywords[i], "");              }
//             else            {   inpt = inpt.replace(arrOfKeywords[i], " ");             }
//         }

//         details = inpt.split(" ", -1);
//         if (command == "ADD_VIDEO")     {   addVideo(details);     }
//         if (command == "REMOVE_VIDEO")  {   removeVideo(details);  }
//         if (command == "METRICS__")     {   addMetrics(details);   }
//         if (command == "AD_REQUEST__")  {   adRequest(details);    }
//     }


//     /**
//     * Store result after each line.
//     *
//     * @exception None
//     *
//     * @return void
//     */
//     @Override
//     public void storeResult(String line){
//         this.result.writeLine(this.curState.getName()+line);
//     }


//     /**
//     * Persists results after input is processed.
//     *
//     * @exception None
//     *
//     * @return void
//     */
//     @Override
//     public void persistResult(){
//         this.result.persistResult();
//     }

// }