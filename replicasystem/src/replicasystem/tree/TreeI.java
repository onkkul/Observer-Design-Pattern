package replicasystem.tree;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.FileInputStream;

import replicasystem.treenode.TreeNodeI;
public interface TreeI{

    public TreeNodeI createTree();

    public TreeNodeI CreateNode(String tree, int bNumber);

    public void addNodeDetails(String firstName, String lastName,
        String major, double gpa, String skills);

    public TreeNodeI searchNode(TreeNodeI tree, int bNumber);

    public void updateNode(String firstName, String lastName,
        String major, double gpa, String skills);

    public void printNodes(TreeNodeI tree);

    public void parseInput(String line);

}


