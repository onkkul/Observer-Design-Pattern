package replicasystem.tree;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.FileInputStream;
import replicasystem.util.Results;
import replicasystem.treenode.TreeNodeI;

public interface TreeI{

    public TreeNodeI createNode(String tree, int bNumber);

    public void addNodeDetails(String firstName, String lastName,
        String major, double gpa, String[] skills);

    public TreeNodeI searchNode(TreeNodeI tree, int bNumber);

    public void updateNode(String[] oldValues, String[] newValues);

    public TreeNodeI getTree(int index);
    public void printNodes(Results result, TreeNodeI tree);

    public void parseInput(String line);
    public void modifyInput(String line);

}


