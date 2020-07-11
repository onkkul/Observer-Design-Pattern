package replicasystem.treenode;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.FileInputStream;
import replicasystem.util.Results;
import replicasystem.treenode.TreeNodeI;
import replicasystem.treenode.SubjectI;
import replicasystem.treenode.ObserverI;

public class StudentRecord implements TreeNodeI{

    private TreeNodeI leftChild = null;
    private TreeNodeI rightChild = null;
    private TreeNodeI[] observers = new TreeNodeI[2];

    private int bNumber = Integer.MIN_VALUE;
    private String firstName = null;
    private String lastName = null;
    
    private String major = null;
    private double gpa = Integer.MIN_VALUE;
    private String[] skills = null;

    private static int uniqueID = 0;
    private static int nodeID = 0;

    /**
     * Constructor for class
     *
     * @exception None
     *
     * @return void
     */
    public StudentRecord(int bNumber){
        this.bNumber = bNumber;
        uniqueID++;
        this.nodeID = uniqueID;
    }

    /**
     * Add the new skills from "input" to existing list
     *
     * @exception None
     *
     * @return String[] array of skills
     */
    public String[] mergeSkills(String[] oldSkills, String[] newSkills){
        String[] temp = new String[newSkills.length];
        if (oldSkills == null)
            temp = newSkills;
        else{
            temp = new String[oldSkills.length+newSkills.length];
            int count = 0;

            for(int i = 0; i < oldSkills.length; i++) { 
             temp[i] = oldSkills[i];
             count++;
            } 
            for(int j = 0; j < newSkills.length;j++)
             temp[count++] = newSkills[j];
        }
        return temp;
    }

    /**
     * Insert values for node from "input.txt"
     *
     * @exception None
     *
     * @return void
     */
    @Override
    public void insertValues(String firstName, String lastName,
        String major, double gpa, String[] skills){
        this.firstName = firstName;
        this.lastName = lastName;

        this.major = major;
        this.gpa = gpa;

        this.skills = mergeSkills(this.skills, skills);
        notifyObservers();
    }


    /**
     * Replace skill specified in "modify" file
     *
     * @exception None
     *
     * @return void
     */
    public void replaceSkill(String oldSkill, String newSkill){
        String[] temp = new String[this.skills.length];
        for(int i = 0; i<temp.length;i++){
            if (this.skills[i].equals(oldSkill))
                temp[i] = newSkill;
            else
                temp[i] = this.skills[i];
        }
        this.skills = null;
        this.skills = temp;
    }

    /**
     * Update values for node from "modify.txt"
     *
     * @exception None
     *
     * @return void
     */
    @Override
    public void updateValues(String[] oldValues, String[] newValues){
        for (int i = 0; i<oldValues.length; i++){
            if (this.firstName.equals(oldValues[i]))
                this.firstName = newValues[i];
            if (this.lastName.equals(oldValues[i]))
                this.lastName = newValues[i];
            if (this.major.equals(oldValues[i]))
                this.major = newValues[i];
            replaceSkill(oldValues[i], newValues[i]);
        }
    }


    /**
     * funciton to return skills for node to write in Result
     *
     * @exception None
     *
     * @return String string of skills
     */
    @Override
    public String printNode(){
        // System.out.println("B-Number: "+ this.bNumber);
        // System.out.println("Name: "+this.firstName + " " + this.lastName);
        // System.out.println("Major: " + this.major + " GPA:" + this.gpa);
        // System.out.print("Skills: ");
        return Integer.toString(this.bNumber) + Arrays.toString(this.skills);
    }


    /**
     * Get method for NodeID
     * @exception None
     * @return int
     */
    @Override
    public int getNodeID()          {   return this.nodeID;     }
    
    /**
     * Get method for BNumber
     * @exception None
     * @return int
     */
    @Override
    public int getBnumber()         {   return this.bNumber;    }

    /**
     * Get method for FirstName
     * @exception None
     * @return String
     */
    @Override
    public String getFirstName()    {   return this.firstName;  }
    
    /**
     * Get method for LastName
     * @exception None
     * @return String
     */
    @Override
    public String getLastName()     {   return this.lastName;   }

    /**
     * Get method for Major
     * @exception None
     * @return String
     */
    @Override
    public String getMajor()        {   return this.major;      }
    
    /**
     * Get method for GPA
     * @exception None
     * @return double
     */
    @Override
    public double getGPA()          {   return this.gpa;        }
    
    /**
     * Get method for Skills
     * @exception None
     * @return  String[]
     */
    @Override
    public String[] getSkills()     {   return this.skills;     }
    
    /**
     * Get method to get RightChild
     * @exception None
     * @return TreeNodeI
     */
    @Override
    public TreeNodeI getRightChild(){   return this.rightChild; }
    
    /**
     * Get method to get LeftChild
     * @exception None
     * @return TreeNodeI
     */
    @Override
    public TreeNodeI getLeftChild() {   return this.leftChild;  }

    /**
     * Get method to set RightChild
     * @exception None
     * @return void
     */
    @Override
    public void setRightChild(TreeNodeI rightChild){
        this.rightChild = rightChild;
        return;
    }

    /**
     * Get method to set RightChild
     * @exception None
     * @return void
     */
    @Override
    public void setLeftChild(TreeNodeI leftChild){
        this.leftChild = leftChild;
        return;
    }


    /**
     * Get method to get Observers
     * @exception None
     * @return TreeNodeI[]
     */
    @Override
    public TreeNodeI[] getObservers(){  return this.observers;  }
    
    /**
     * method to notify Observers
     * @exception None
     * @return void
     */
    @Override
    public void notifyObservers(){
        this.observers[0].receiveNotification(this);
        this.observers[1].receiveNotification(this);
    }

    /**
     * method to get Notified (UPDATE call)
     * @exception None
     * @return void
     */
    @Override
    public void receiveNotification(TreeNodeI sender){
        if (sender != null){
            this.bNumber = sender.getBnumber();
            this.firstName = sender.getFirstName();
            this.lastName = sender.getLastName();
            
            this.major = sender.getMajor();
            this.gpa = sender.getGPA();
            this.skills = sender.getSkills();
            this.observers = sender.getObservers();
        }
        return;
    }

    /**
     * method to get register Observers
     * @exception None
     * @return void
     */
    @Override
    public void registerObservers(TreeNodeI firstObserver, TreeNodeI secondObeserver){
        this.observers[0] = firstObserver;
        this.observers[1] = secondObeserver;
    }

    /**
     * method to get unregister Observer
     * @exception None
     * @return void
     */
    @Override
    public void unregisterObserver(TreeNodeI observer){
        if (observer.getNodeID() == this.observers[0].getNodeID()){
            this.observers[0] = null;
        }
        else if (observer.getNodeID() == this.observers[1].getNodeID()) {
            this.observers[1] = null;
        }
    }
}