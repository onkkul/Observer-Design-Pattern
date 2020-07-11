package replicasystem.treenode;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.FileInputStream;

import replicasystem.treenode.TreeNodeI;
import replicasystem.treenode.SubjectI;
import replicasystem.treenode.ObserverI;

public class StudentRecord implements TreeNodeI{

    private TreeNodeI leftChild = null;
    private TreeNodeI rightChild = null;

    private int bNumber = Integer.MIN_VALUE;
    private String firstName = null;
    private String lastName = null;
    
    private String major = null;
    private double gpa = Integer.MIN_VALUE;
    private String[] skills = null;

    private TreeNodeI[] observers = new TreeNodeI[2];
    private static int uniqueID = 0;
    private static int nodeID = 0;


    public StudentRecord(int bNumber){
        this.bNumber = bNumber;
        uniqueID++;
        this.nodeID = uniqueID;
    }


    public String[] mergeSkills(String[] oldSkills, String[] newSkills){
        if (oldSkills == null)
            return newSkills;
        else{
            String[]temp = new String[oldSkills.length+newSkills.length];
            int count = 0;

            for(int i = 0; i < oldSkills.length; i++) { 
             temp[i] = oldSkills[i];
             count++;
            } 
            for(int j = 0; j < newSkills.length;j++) { 
             temp[count++] = newSkills[j];
            }
            return temp;
        }
    }
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


    public void replaceSkill(String oldSkill, String newSkill){
        for(int i = 0; i<this.skills.length;i++){
            if (this.skills[i].equals(oldSkill))
                this.skills[i] = newSkill;
        }
    }
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
        notifyObservers();
    }


    @Override
    public void printNode(){
        System.out.println("B-Number: "+ this.bNumber);
        System.out.println("Name: "+this.firstName + " " + this.lastName);
        System.out.println("Major: " + this.major + " GPA:" + this.gpa);
        System.out.print("Skills: ");
            for (String s:skills)
                System.out.print(s+" ");
        System.out.println("\n----------------------------------");

    }

    @Override
    public int getBnumber()         {   return this.bNumber;    }

    @Override
    public String getFirstName()    {   return this.firstName;  }
    @Override
    public String getLastName()     {   return this.lastName;   }

    @Override
    public String getMajor()        {   return this.major;      }
    @Override
    public double getGPA()          {   return this.gpa;        }
    @Override
    public String[] getSkills()     {   return this.skills;     }
    
    @Override
    public TreeNodeI getRightChild(){   return this.rightChild; }
    @Override
    public TreeNodeI getLeftChild() {   return this.leftChild;  }


    @Override
    public void setRightChild(TreeNodeI rightChild){
        this.rightChild = rightChild;
        return;
    }
    @Override
    public void setLeftChild(TreeNodeI leftChild){
        this.leftChild = leftChild;
        return;
    }


    @Override
    public int getNodeID(){
        return this.nodeID;
    }

    @Override
    public TreeNodeI[] getObservers(){  return this.observers;  }
    @Override
    public void notifyObservers(){
        this.observers[0].receiveNotification(this);
        this.observers[1].receiveNotification(this);
    }
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

    @Override
    public void registerObservers(TreeNodeI firstObserver, TreeNodeI secondObeserver){
        this.observers[0] = firstObserver;
        this.observers[1] = secondObeserver;
    }

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