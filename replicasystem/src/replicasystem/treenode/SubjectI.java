package replicasystem.treenode;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.FileInputStream;

public interface SubjectI{

    public void insertValues(String firstName, String lastName,
        String major, double gpa, String[] skills);
    public void updateValues(String firstName,String lastName,
        String major, double gpa, String[] skills);

    public void printNode();

    public TreeNodeI[] getObservers();
    public void notifyObservers();
    public void receiveNotification(TreeNodeI sender);
    public void unregisterObserver(TreeNodeI observer);
    public void registerObservers(TreeNodeI firstObserver, TreeNodeI secondObeserver);


    public int getBnumber();
    
    public String getFirstName();
    public String getLastName();

    public String getMajor();
    public double getGPA();
    
    public String[] getSkills();



    public TreeNodeI getLeftChild();
    public TreeNodeI getRightChild();
    public void setRightChild(TreeNodeI rightChild);
    public void setLeftChild(TreeNodeI leftChild);
}