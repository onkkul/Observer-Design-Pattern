// package replicasystem.state;
// import replicasystem.context.ContextI;
// import replicasystem.state.StateName;


// public abstract class AbstractState implements StateI {

//     public String video;
//     public int[] stats;
//     private int adLimit;
//     public int popularityScore;
//     private ContextI currentChannel;
//     private StateName stateName;

//     /**
//     * Sets details of current state.
//     *
//     * @exception None
//     *
//     * @return void
//     */
//     public void setDetails(ContextI channel, StateName name, int adLimit){
//         this.currentChannel = channel;  
//         this.stateName = name;
//         this.adLimit = adLimit;
//     }


//     /**
//     * Returns Name of current state.
//     *
//     * @exception None
//     *
//     * @return StateName of current state.
//     */
//     @Override
//     public StateName getName()      { return this.stateName;        }


//     /**
//     * Returns limit of current state.
//     *
//     * @exception None
//     *
//     * @return int value of ad limit of current state.
//     */
//     @Override
//     public int getLimit()           { return this.adLimit;          }


//     /**
//     * Returns context of current state.
//     *
//     * @exception None
//     *
//     * @return context of current state
//     */
//     @Override
//     public ContextI getContext()    { return this.currentChannel;   }


//     /**
//     * Adds given video to the channel.
//     * 
//     * @exception None
//     *
//     * @return void
//     */
//     @Override
//     public void addVideo(String[] details){
//         validateInput(details);
//         stats = new int[]{0, 0, 0, 10};
//         this.currentChannel.editAccount("put", details[0], stats);
//         decideState();

//         this.currentChannel.storeResult("__VIDEO_ADDED::"+details[0]+"\n");
//     }


//     /**
//     * Removes given video from channel.
//     * 
//     * @exception None
//     *
//     * @return void
//     */
//     @Override
//     public void removeVideo(String[] details){
//         validateInput(details);
//         int[] previous = this.currentChannel.editAccount("get", details[0], stats);
//         this.currentChannel.editAccount("remove", details[0], stats);
//         decideState();
//         this.currentChannel.storeResult("__VIDEO_REMOVED::"+details[0]+"\n");
//         return;
//     }


//     /**
//     * Adds given stats to the output throws exception if incorrect stats.
//     * 
//     * @exception IllegalArgumentException for incorrect stats
//     *
//     * @return void
//     */
//     @Override
//     public void addMetrics(String[] details)throws IllegalArgumentException{
//         validateInput(details);
//         int[] previous = this.currentChannel.editAccount("get", details[0], stats);
//         for(int i = 1; i < 4; i++){
//             previous[i-1] = previous[i-1] + Integer.parseInt(details[i]);
//             if (previous[i-1] < 0){
//                 throw new IllegalArgumentException("Decrease in Likes/Dislikes cannot Exceed count of Likes/Dislikes");
//             }
//         }
//         previous[3] = this.adLimit;
//         this.currentChannel.editAccount("addM", details[0], previous);
//         decideState();
//         this.currentChannel.storeResult("__POPULARITY_SCORE_UPDATE::"+Integer.toString(popularityScore)+"\n");
//         return;
//     }


//     /**
//     * Accepts or rejects an AD request.
//     * 
//     * @exception None
//     *
//     * @return void
//     */
//     @Override
//     public void adRequest(String[] details){
//         validateInput(details);
//         int[] previous = this.currentChannel.editAccount("get", details[0], stats);

//         decideState();

//         if (Integer.parseInt(details[1]) > this.adLimit){
//             this.currentChannel.storeResult("__AD_REQUEST::REJECTED\n");
//         }
//         else{
//             this.currentChannel.storeResult("__AD_REQUEST::APPROVED\n");
//         }
        
//     return;
//     }


//     /**
//     * Decides what the next state should be.
//     * 
//     * @exception None
//     *
//     * @return void
//     */
//     public void decideState(){
//         // UNPOPULAR, MILDLY_POPULAR, HIGHLY_POPULAR, ULTRA_POPULAR;
//         StateName nexttState = null;
//         popularityScore = this.currentChannel.updateScore();
//         if (0 <= popularityScore && popularityScore <= 1000){
//             this.currentChannel.setState(StateName.UNPOPULAR);
//         }
//         if (1000 < popularityScore && popularityScore <= 10000){
//             this.currentChannel.setState(StateName.MILDLY_POPULAR);
//         }
//         if (10000 < popularityScore && popularityScore <= 100000){
//             this.currentChannel.setState(StateName.HIGHLY_POPULAR);            // nexttState = this.currentChannel.getHighlyPopularState();
//         }
//         if (100000 < popularityScore && popularityScore <= Integer.MAX_VALUE){
//             this.currentChannel.setState(StateName.ULTRA_POPULAR);            // nexttState = this.currentChannel.getUltraPopularState();
//         }
//     }


//     /**
//     * Validates input for corrrectness.
//     * 
//     * @exception IllegalArgumentException
//     *
//     * @return void
//     */
//     public void validateInput(String[] details) throws IllegalArgumentException{
//         // String[] validations = {"video", "0", "1", "2", "40"}
//         if (!details[0].contains("video")|| details[0].replace("video", "").isEmpty()){
//         throw new IllegalArgumentException("invalid input");
//         }

//         for (int i=1;i<details.length;i++){
//             if (details[i].contains(".") || details[1].contains("-")){
//                 throw new IllegalArgumentException("invalid input");
//             }
//         }
//     }
// }