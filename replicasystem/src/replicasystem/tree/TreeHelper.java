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
import replicasystem.treenode.TreeNode;

class TreeHelper implements TreeI{
    public TreeNodeI currentNode = null;
    public TreeNodeI newNodes[] = new TreeNodeI[3];

    public  TreeNodeI trees[] = new TreeNodeI[3];

    public int bNumber = Integer.MIN_VALUE;

    public String firstName = null;
    public String lastName = null;
    
    public String major = null;
    public double gpa = Integer.MIN_VALUE;
    public String skills = null;

    /**
     * Create all three trees
     * @return a set of all three tree heads
     */
    public TreeHelper(){

        for (int i = 0; i<3; i++){
            this.trees[i] = createTree();
            // System.out.println(currentNode.bNumber);
        } 
    }


    @Override
    public TreeNodeI createTree(){
        int rootVal = Integer.MIN_VALUE;
        currentNode = new TreeNode(rootVal);
        return currentNode;
    }


    public void insertNode(TreeNodeI tree, TreeNodeI node, TreeNodeI parent){
        if ((tree == null) || (tree.getBnumber() == Integer.MIN_VALUE)){
            tree = node;
        }
        else{
            if (tree.getBnumber() < node.getBnumber()){
                TreeNodeI rightChild = tree.getRightChild();
                if (rightChild == null)
                    tree.setRightChild(node);
                else
                    insertNode(rightChild, node, rightChild);
            }
            else{
                TreeNodeI leftChild = tree.getLeftChild();
                if (leftChild == null)
                    tree.setLeftChild(node);
                else
                    insertNode(leftChild, node, leftChild);
            }
        }
        node.setParent(parent);
    }

    @Override
    public TreeNodeI CreateNode(String tree, int bNumber){
        TreeNodeI emptyNode = null;
        for (int i = 0; i < 3; i++){
            newNodes[i] = new TreeNode(bNumber);
            insertNode(trees[i], newNodes[i], emptyNode);
        }
        if (tree == "one"){
            newNodes[0].registerObserver(newNodes[1], newNodes[2]);
            currentNode = newNodes[0];
        } 
        if (tree == "two"){
            newNodes[1].registerObserver(newNodes[0], newNodes[2]);
            currentNode = newNodes[1];
        }
        if (tree == "three"){
            newNodes[2].registerObserver(newNodes[0], newNodes[1]);
            currentNode = newNodes[1];
        }

        return currentNode;
    }


    @Override
    public void addNodeDetails(String firstName, String lastName,
        String major, double gpa, String skills){
        currentNode.insertValues(firstName, lastName,
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
        String major, double gpa, String skills){

        currentNode.updateValues(firstName, lastName,
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
    public void parseInput(String line){
        /*
         * Do not create trees. constructor will do it
         * Create Nodes
         * Insert Node
         * Add details to the nodes
         * Search Node 
         * Update Details
         * Print Node
         */
    }


    public void resetDetails(){
        return;
    }
}