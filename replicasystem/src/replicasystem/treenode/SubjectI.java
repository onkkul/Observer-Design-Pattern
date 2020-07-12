package replicasystem.treenode;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.FileInputStream;

import replicasystem.treenode.TreeNodeI;

public interface SubjectI{

    public TreeNodeI[] getObservers();

    public void notifyObservers();
    
    public void unregisterObserver(TreeNodeI observer);
    
    public void registerObservers(TreeNodeI firstObserver, TreeNodeI secondObeserver);
}