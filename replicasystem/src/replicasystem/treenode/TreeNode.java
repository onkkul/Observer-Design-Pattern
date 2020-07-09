package replicasystem.treenode;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.FileInputStream;

import replicasystem.treenode.TreeNodeI;

public class TreeNode implements TreeNodeI{
    private TreeNodeI parent = null;
    private TreeNodeI leftChild = null;
    private TreeNodeI rightChild = null;

    private int bNumber = Integer.MIN_VALUE;
    private String firstName = null;
    private String lastName = null;
    
    private String major = null;
    private double gpa = Integer.MIN_VALUE;
    private String skills = null;

    private TreeNodeI observerOne = null;
    private TreeNodeI observerTwo = null;

    public TreeNode(int bNumber){
        this.bNumber = bNumber;
    }


    @Override
    public void insertValues(String firstName, String lastName,
        String major, double gpa, String skills){
        this.firstName = firstName;
        this.lastName = lastName;
        
        this.major = major;
        this.gpa = gpa;
        this.skills = skills;

        notifyObservers();
    }


    @Override
    public void updateValues(String firstName,String lastName,
        String major, double gpa, String skills){
        if (firstName != null)
            this.firstName = firstName;
        if (lastName != null)
            this.lastName = lastName;
        
        if (major != null)
            this.major = major;
        if (gpa != Integer.MIN_VALUE)
            this.gpa = gpa;
        if (skills != null)
            this.skills = skills;

        notifyObservers();
    }


    @Override
    public void printNode(){

    }


    @Override
    public void registerObserver(TreeNodeI firstObserver, TreeNodeI secondObeserver){
        this.observerOne = firstObserver;
        this.observerTwo = secondObeserver;
    }

    @Override
    public void unregisterObserver(TreeNodeI observer){
        if (observer.getBnumber() == this.observerOne.getBnumber()){
            this.observerOne = null;
        }
        else if (observer.getBnumber() == this.observerTwo.getBnumber()) {
            this.observerTwo = null;
        }
        // else{
        //     throw IOException
        // }
    }

    @Override
    public void notifyObservers(){
        this.observerTwo.receiveNotification(this);
        this.observerOne.receiveNotification(this);
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
        }
        return;
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
    public String getSkills()       {   return this.skills;     }
    
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
    public void setParent(TreeNodeI parent){
        this.parent = parent;
    }
}