// package replicasystem.state.factory;

// import replicasystem.context.ContextI;

// import replicasystem.state.StateI;
// import replicasystem.state.StateName;
// import replicasystem.state.GetStateObject;


// public class SimpleStateFactory implements SimpleStateFactoryI {

//     private ContextI channel;

//     /**
//     * Constructor for class. Stores the reference of context
//     *
//     * @exception None
//     *
//     * @return void
//     */
//     public SimpleStateFactory(ContextI channel){
//         this.channel = channel;
//     }


//     /**
//     * Creates state objects of all classes
//     *
//     * @exception None
//     *
//     * @return state objects.
//     */
//     @Override
//     public StateI createStateObjects(StateName stateName, int adLimit){
//         return new GetStateObject(this.channel, stateName, adLimit);
//     }

// }
