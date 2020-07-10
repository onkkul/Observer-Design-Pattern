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

import replicasystem.treenode.TreeNodeI;
import replicasystem.treenode.StudentRecord;

public class TreeHelper implements TreeI{
    public TreeNodeI currentNode = null;
    public TreeNodeI newNodes[] = new TreeNodeI[3];

    public TreeNodeI trees[] = new TreeNodeI[3];

    public int bNumber = Integer.MIN_VALUE;

    public String firstName = null;
    public String lastName = null;
    
    public String major = null;
    public double gpa = Integer.MIN_VALUE;
    public String[] skills = null;
    public String details[];
    public boolean roots = false;
    /**
     * Create all three this.trees
     * @return a set of all three tree heads
     */
    public TreeHelper(){}

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

    @Override
    public TreeNodeI createNode(String tree, int bNumber){
        System.out.println("Creating Node for: " + bNumber);
        for (int i = 0; i < 3; i++){
            this.newNodes[i] = new StudentRecord(bNumber);
            if (roots == false)
                this.trees[i] = this.newNodes[i];
            else
                insertNode(this.trees[i], this.newNodes[i]);
        }
        roots = true;

        if (tree == "one"){
            this.newNodes[0].registerObservers(this.newNodes[1], this.newNodes[2]);
            this.currentNode = this.newNodes[0];
        } 
        if (tree == "two"){
            this.newNodes[1].registerObservers(this.newNodes[0], this.newNodes[2]);
            this.currentNode = this.newNodes[1];
        }
        if (tree == "three"){
            this.newNodes[2].registerObservers(this.newNodes[0], this.newNodes[1]);
            this.currentNode = this.newNodes[2];
        }

        return this.currentNode;
    }


    @Override
    public void addNodeDetails(String firstName, String lastName,
        String major, double gpa, String[] skills){
        System.out.println("adding details for : " + this.currentNode.getBnumber());
        this.currentNode.insertValues(firstName, lastName,
                major, gpa, skills);
    }


    @Override
    public TreeNodeI searchNode(TreeNodeI tree, int bNumber){
        if ((tree == null) || (tree.getBnumber()  == bNumber)) 
            return tree;
        if (tree.getBnumber() < bNumber) 
            return searchNode(tree.getRightChild(), bNumber);
        return searchNode(tree.getLeftChild(), bNumber);
    }


    @Override
    public void updateNode(String firstName, String lastName,
        String major, double gpa, String[] skills){
        this.currentNode.updateValues(firstName, lastName,
                major, gpa, skills);
    }


    @Override
    public void printNodes(TreeNodeI tree){
        if (tree != null){
            printNodes(tree.getLeftChild());
            tree.printNode();
            printNodes(tree.getRightChild());
        }
    }


    // Main function
    @Override
    public void parseInput(String line, String typeOfInput){
        System.out.println("Parsing the input");
        /*
         * Do not create this.trees. constructor will do it
         * Create Nodes
         * Insert Node
         * Add details to the nodes
         * Search Node 
         * Update Details
         * Print Node         // 1234:John,Doe,3.9,ComputerScience,Skill1,Skill2,Skill3,Skill4,Skill5
         */

        String inputArray[]= line.split(":");
        bNumber = Integer.parseInt(inputArray[0]);

        inputArray = inputArray[1].split(",");

        details = new String[inputArray.length -1];
        skills = new String[inputArray.length-4];
        
        int index = 0;
        int skill_index = 0;
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

    }


    public void resetDetails(){
        return;
    }

    @Override
    public TreeNodeI getTree(int index){
        return this.trees[index];
    }
}