# Observer Design Pattern - Replica System
-----------------------------------------------------------------------
## Description:
- WorkFlow:
    - The TreeHelper processes one line of input at a time
    - It creates nodes and sets up observers
    - Then it adds details for that node such as Name, Major etc
    - Once details are added, node sends notifyObservers() call
    - Nodes are notified and changes are made by update() call
    - Please remember that both actions are done at node level; thus you will find both methods in StudentRecord
    - While processing the modify file:
        - If an entry is dirty, it is written to **error.txt** file
        - All entries of same value are replaced
    - If all goes well(which it will!! :-P) the output is written to respective files in sorted BNumbers.
    - All exceptions are written to **error.txt** file
- Complexity:
    - Tree used :- **Binary Search Tree**
    - Avg Case and Best Case time : **O(log(n))**
    - Worst Case time: **O(n)** --> when all entries are in increasing order of BNumber.
-----------------------------------------------------------------------

Following are the commands and the instructions to run ANT on your project.
Note: build.xml is present in [replicasystem/src](./replicasystem/src/) folder.

-----------------------------------------------------------------------
## Instruction to clean: </br>

```commandline
    ant -buildfile replicasystem/src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you compiled your code.

-----------------------------------------------------------------------
## Instructions to compile:

```commandline
    ant -buildfile replicasystem/src/build.xml all
```
The above command compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instructions to run:

```commandline
    ant -buildfile replicasystem/src/build.xml run -Dinput="input.txt" -Dmodify="modify.txt" -Dout1="out1.txt" -Dout2="out2.txt" -Dout3="out3.txt" -Derror="error.txt" -Ddebug="debug.txt"
```
Note: Arguments accept the absolute path of the files.

-----------------------------------------------------------------------
-----------------------------------------------------------------------
