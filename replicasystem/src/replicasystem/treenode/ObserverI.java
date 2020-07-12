package replicasystem.treenode;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.FileInputStream;
import replicasystem.treenode.TreeNodeI;

public interface ObserverI{

    public void update(TreeNodeI sender);

    public int getNodeID();

}