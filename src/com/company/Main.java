package com.company;
/*
Created by Cole Hartnett 2/24/2020
This is a homework assignment for CS 224 Algorithm Design and Analysis
Summary:
 */

import java.util.*;

//node class to comprise the search tree
class Node {
    //create fields for the nodes value
    public int value;
    //include a bool to flag if the node is visited
    public boolean visit;

    //constructor that specifies the node's value
    public Node(int num) {
        value = num;
        //by default set visit flag to false.
        visit = false;
    }
}

//create a Search Tree class to perform the traversal
class SearchTree {
    //set Max Number of Nodes to 8 for this assignment
    private final int NUM_NODES = 8;
    //include a list of nodes in the tree
    private Node nodeList[];
    //include an adjacency Matrix to keep track of the edges
    private int adjMatrix[][];
    //include the number of current nodes
    private int currNodes;
    //Use built in Java Queue for FIFO queue
    private Queue<Integer> qBFS;
    //Use built in JavaStack for LIFO Stack (DFS)
    private Stack<Integer> sDFS = new Stack<Integer>();

    //default constructor that creates the queue and set's the nodeList and adjMatrix to size 8
    public SearchTree() {
        nodeList = new Node[NUM_NODES];
        adjMatrix = new int[NUM_NODES][NUM_NODES];
        currNodes = 0;
        qBFS = new LinkedList<Integer>();
    }
    //Add a node to the tree
    public void addNode(int n) {
        nodeList[currNodes++] = new Node(n);
    }
    //add an edge to the tree
    public void addEdge(int node1, int node2) {
        adjMatrix[node1][node2] = 1;
        adjMatrix[node1][node2] = 1;
    }
    //display the current node value
    public void showNode(int n) {
        System.out.print(nodeList[n].value);
    }
    //create a function to return the neighboring Nodes, return -1 otherwise
    public int getNeighborNodes (int n) {
        for(int j=0; j<currNodes; j++) {
            if(adjMatrix[n][j]==1 && nodeList[j].visit==false) {
                return j;
            }
        }
        return -1;
    }
    //Binary First Search algorithm
    public void breadthFS(int start) {
        //set the first node in the list visit flag to true
        nodeList[start].visit = true;
        //show the first node
        showNode(start);
        //add the first element into the queue
        qBFS.add(start);
        int n2;
        //While the queue isn't empty
        while(!qBFS.isEmpty()) {
            //pop the queue
            int n1 = qBFS.remove();
            //while there are still neighboring nodes
            while((n2=getNeighborNodes(n1))!=-1) {
                //visit those nodes and set them to true
                nodeList[n2].visit = true;
                //show the node visited
                showNode(n2);
                //add it to the queue
                qBFS.add(n2);
            }
        }
    }
    public void depthFS(int s) {
        //set the first node visited true
        nodeList[s].visit = true;
        //show the node
        showNode(s);
        //push the first node into the stack
        sDFS.push(s);
        //Which the stack isn't empty
        while(!sDFS.isEmpty()) {
            //get the neighboring nodes
            int n = getNeighborNodes(sDFS.peek());
            //if there are neighboring nodes
            if(n == -1) {
                //pop from the stack
                sDFS.pop();
            } else {
                //set the node visit flag to true
                nodeList[n].visit = true;
                //show the node visited
                showNode(n);
                //push it into the stack
                sDFS.push(n);
            }
        }
    }
}


public class Main {

    public static void main(String[] args) {
        //create a new search tree for binary search and add all the nodes and edges
        SearchTree bfsTree = new SearchTree();
        bfsTree.addNode(1);
        bfsTree.addNode(2);
        bfsTree.addNode(3);
        bfsTree.addNode(4);
        bfsTree.addNode(5);
        bfsTree.addNode(6);
        bfsTree.addNode(7);
        bfsTree.addNode(8);

        //Add all edges relating to node 1
        bfsTree.addEdge(0, 1);
        bfsTree.addEdge(0, 2);
        //Node 2
        bfsTree.addEdge(1, 0);
        bfsTree.addEdge(1, 2);
        bfsTree.addEdge(1, 3);
        bfsTree.addEdge(1, 4);
        //Node 3
        bfsTree.addEdge(2, 0);
        bfsTree.addEdge(2, 1);
        bfsTree.addEdge(2, 4);
        bfsTree.addEdge(2, 6);
        bfsTree.addEdge(2, 7);
        //Node 4
        bfsTree.addEdge(3, 1);
        bfsTree.addEdge(3, 4);
        //Node5
        bfsTree.addEdge(4, 1);
        bfsTree.addEdge(4, 2);
        bfsTree.addEdge(4, 3);
        bfsTree.addEdge(4, 5);
        //Node 6
        bfsTree.addEdge(5, 4);
        //Node7
        bfsTree.addEdge(6, 2);
        bfsTree.addEdge(6, 7);
        //Node8
        bfsTree.addEdge(7, 2);
        bfsTree.addEdge(7, 6);

        //Create a search tree for the Depth First search *Note this tree will be identical
        SearchTree dfsTree = new SearchTree();
        dfsTree.addNode(1);
        dfsTree.addNode(2);
        dfsTree.addNode(3);
        dfsTree.addNode(4);
        dfsTree.addNode(5);
        dfsTree.addNode(6);
        dfsTree.addNode(7);
        dfsTree.addNode(8);

        dfsTree.addEdge(0, 1);
        dfsTree.addEdge(0, 2);
        dfsTree.addEdge(1, 0);
        dfsTree.addEdge(1, 2);
        dfsTree.addEdge(1, 3);
        dfsTree.addEdge(1, 4);
        dfsTree.addEdge(2, 0);
        dfsTree.addEdge(2, 1);
        dfsTree.addEdge(2, 4);
        dfsTree.addEdge(2, 6);
        dfsTree.addEdge(2, 7);
        dfsTree.addEdge(3, 1);
        dfsTree.addEdge(3, 4);
        dfsTree.addEdge(4, 1);
        dfsTree.addEdge(4, 2);
        dfsTree.addEdge(4, 3);
        dfsTree.addEdge(4, 5);
        dfsTree.addEdge(5, 4);
        dfsTree.addEdge(6, 2);
        dfsTree.addEdge(6, 7);
        dfsTree.addEdge(7, 2);
        dfsTree.addEdge(7, 6);


        //Now time for user input
        Scanner sc = new Scanner(System.in);
        System.out.println("Would search would you like: A) Breath First Search or B) Depth First Search");
        String ans = sc.next();

        while (ans.equals('A') || ans.equals('a') || ans.equals('B') || ans.equals('b')) {
            System.out.print("Invalid Input: Please enter either A or B: ");
            ans = sc.next();
        }
        //BFS
        if (ans.equals("A") || ans.equals("a")) {
            System.out.print("please enter a starting Node 1-8: ");
            int starting = sc.nextInt();
            while (starting < 1 || starting > 8) {
                System.out.print("InValid Input. Please enter a number 1-8: ");
                starting = sc.nextInt();
            }
            System.out.println("Node visited via Breath First Search: ");
            bfsTree.breadthFS(starting);
            System.out.println();
        }
        //DFS
        if (ans.equals("B") || ans.equals("b")) {
            System.out.print("please enter a starting Node 1-8: ");
            int starting2 = sc.nextInt();
            while (starting2 < 1 || starting2 > 8) {
                System.out.print("InValid Input. Please enter a number 1-8: ");
                starting2 = sc.nextInt();
            }
            System.out.println("Node visited via Depth First Search: ");
            dfsTree.depthFS(starting2);
            System.out.println();
        }
    }

}



