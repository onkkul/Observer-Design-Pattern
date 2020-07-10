package replicasystem.treenode;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.FileInputStream;

public interface ObserverI{

    public void updateValues(String firstName,String lastName,
        String major, double gpa, String[] skills);
    
    public void receiveNotification(TreeNodeI sender);

    public int getBnumber();
    
    public String getFirstName();
    public String getLastName();

    public String getMajor();
    public double getGPA();
    
    public String[] getSkills();

    public TreeNodeI getLeftChild();
    public TreeNodeI getRightChild();

}