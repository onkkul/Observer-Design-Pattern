package replicasystem.treenode;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.FileInputStream;
import replicasystem.treenode.SubjectI;
import replicasystem.treenode.ObserverI;
import replicasystem.treenode.TreeNodeI;


public interface TreeNodeI extends SubjectI, ObserverI{

    public void insertValues(String firstName, String lastName, String major, double gpa, String[] skills);
    public void updateValues(String[] oldValues, String[] newValues);

    public void printNode();

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