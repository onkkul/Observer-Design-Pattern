package replicasystem.tree;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;
import java.util.Arrays;
import java.util.List;

import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.FileInputStream;

import replicasystem.util.Results;
import replicasystem.treenode.TreeNodeI;
import replicasystem.treenode.StudentRecord;

public class TreeHelper implements TreeI{
    public TreeNodeI currentNode = null;
    public TreeNodeI trees[] = new TreeNodeI[3];
    public TreeNodeI newNodes[] = new TreeNodeI[3];

    public boolean roots = false;

    /**
     * Constructor for TreeHelper
     *
     * @exception
     *
     * @return void
     */
    public TreeHelper(){}


    /**
     * Insert created node in the tree.
     *
     * @exception
     *
     * @return void
     */
    public void insertNode(TreeNodeI tree, TreeNodeI node){
        if (tree.getBnumber() < node.getBnumber()){
            TreeNodeI rightChild = tree.getRightChild();
            if (rightChild == null){
                tree.setRightChild(node);
            }
            else
                insertNode(rightChild, node);
        }
        else{
            TreeNodeI leftChild = tree.getLeftChild();
            if (leftChild == null){
                tree.setLeftChild(node);
            }

            else
                insertNode(leftChild, node);
        }
    }

    /**
     * Create node and set up the observers.
     *
     * @exception
     *
     * @return TreeNodeI the createNode node
     */
    @Override
    public TreeNodeI createNode(String tree, int bNumber){
        for (int i = 0; i < 3; i++){
            this.newNodes[i] = new StudentRecord(bNumber);
            if (roots == false)
                this.trees[i] = this.newNodes[i];
            else
                insertNode(this.trees[i], this.newNodes[i]);
        }
        roots = true;
        this.newNodes[0].registerObservers(this.newNodes[1], this.newNodes[2]);
        this.newNodes[1].registerObservers(this.newNodes[0], this.newNodes[2]);
        this.newNodes[2].registerObservers(this.newNodes[0], this.newNodes[1]);
        
        if (tree == "one")  {   this.currentNode = this.newNodes[0];    } 
        if (tree == "two")  {   this.currentNode = this.newNodes[1];    }
        if (tree == "three"){   this.currentNode = this.newNodes[2];    }

        return this.currentNode;
    }


    /**
     * Add node details such as name and major to the created node.
     *
     * @exception
     *
     * @return void
     */
    @Override
    public void addNodeDetails(String firstName, String lastName,
        String major, double gpa, String[] skills){
        this.currentNode.insertValues(firstName, lastName,
            major, gpa, skills);
    }


    /**
     * Search a node in asked tree
     *
     * @exception
     *
     * @return TreeNodeI Tree Node in that tree (null if not present)
     */
    @Override
    public TreeNodeI searchNode(TreeNodeI tree, int bNumber){
        if ((tree == null) || (tree.getBnumber()  == bNumber)) 
            return tree;
        if (tree.getBnumber() < bNumber) 
            return searchNode(tree.getRightChild(), bNumber);
        return searchNode(tree.getLeftChild(), bNumber);
    }


    /**
     * Update the node for inputs from modify file
     *
     * @exception
     *
     * @return void
     */
    @Override
    public void updateNode(String[] oldValues, String[] newValues){
        this.currentNode.updateValues(oldValues, newValues);
    }


    /**
     * Recursively traverse tree to get the skills
     *
     * @exception
     *
     * @return void
     */
    public void printHelper(Results result, TreeNodeI tree){
        if (tree != null){
            printHelper(result, tree.getLeftChild());
            String temp = tree.printNode();
            result.writeLine(temp+"\n");
            printHelper(result, tree.getRightChild());
        }
    }

    /**
     * Funciton to write the skills to file.
     *
     * @exception
     *
     * @return void
     */
    @Override
    public void printNodes(Results result, TreeNodeI tree){
        printHelper(result, tree);
        result.writeFile();
    }


    /**
     * Process the input from "input" file
     *
     * @exception IndexOutOfBoundsException
     * @exception NullPointerException
     * 
     * @return void
     */
    @Override
    public void parseInput(String line)
        throws IndexOutOfBoundsException, NullPointerException{
        int bNumber = 0;
        int index = 0;
        int skill_index = 0;
        
        double gpa = 0.0;

        String[] skills;
        String[] details;
        String[] inputArray;

        inputArray = line.split(":");

        bNumber = Integer.parseInt(inputArray[0]);
        if (bNumber < 0 || bNumber > 9999)
            throw new IndexOutOfBoundsException ("Invalid B number");
        inputArray = inputArray[1].split(",");

        details = new String[inputArray.length -1];
        skills = new String[inputArray.length-4];

        for (int i = 0; i < inputArray.length; i++){
            if (inputArray[i].contains(".")){
                gpa = Double.parseDouble(inputArray[i]);
            }
            if (index >= 4){
                skills[skill_index] = inputArray[i];
                skill_index++;
            }
            else{
                details[index] = inputArray[i];
                index++;
            }
        }

        this.currentNode = searchNode(trees[0], bNumber);
        if (this.currentNode == null)
            this.currentNode = createNode("one", bNumber);
        addNodeDetails(details[0], details[1],
            details[3], gpa, skills);
        System.out.println("Node " + bNumber + " Inserted successfully \n");
        // resetDetails();
        this.currentNode = null;
    }


    /**
     * Process the input from "modify" file
     *
     * @exception
     *
     * @return void
     */
    @Override
    public void modifyInput(String line)
        throws IndexOutOfBoundsException{
        // Handle empty in new Value
        int treeNumber;
        int bNumber;
        String[] inputArray;
        String[] oldValues;
        String[] newValues;

        inputArray = line.split(",");

        treeNumber = Integer.parseInt(inputArray[0]);
        bNumber = Integer.parseInt(inputArray[1]);

        if(treeNumber < 0 || treeNumber > this.trees.length)
            throw new IndexOutOfBoundsException ("Invalid Tree Index");
        if (bNumber < 1000 || bNumber > 9999)
            throw new IndexOutOfBoundsException ("Invalid B number");

        oldValues = new String[inputArray.length-2];
        newValues = new String[inputArray.length-2];

        for (int i = 2; i<inputArray.length;i++){
            String[] temp = inputArray[i].split(":");
            oldValues[i-2] = temp[0];
            newValues[i-2] = temp[1];
        }

        this.currentNode = searchNode(trees[treeNumber], bNumber);
        if (this.currentNode == null)
            this.currentNode = createNode("one", bNumber);  // configure tree number here
            // throw ClassNotFoundException()
        else
            updateNode(oldValues, newValues);
        this.currentNode = null;
    }


    /**
     * Return tree specified with index
     *
     * @exception ArrayIndexOutOfBoundsException // Index out of range
     *
     * @return TreeNodeI
     */
    @Override
    public TreeNodeI getTree(int index) 
        throws ArrayIndexOutOfBoundsException{
            if (index > this.trees.length)
                throw new ArrayIndexOutOfBoundsException("Invalid Tree Index");
            return this.trees[index];
    }
}