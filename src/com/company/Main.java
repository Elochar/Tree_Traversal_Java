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
    public void breadthFS() {
        //set the first node in the list visit flag to true
        nodeList[0].visit = true;
        //show the first node
        showNode(0);
        //add the first element into the queue
        qBFS.add(0);
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
    public void depthFS(){

    }
}

public class Main{

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

        //All Edged involving node 1 *Note: Since the search tree is undirected , only need to ad each edge once
        bfsTree.addEdge(0,1); bfsTree.addEdge(0,2);
        //Node 2
        bfsTree.addEdge(1,2); bfsTree.addEdge(1,3); bfsTree.addEdge(1,4);
        //Node 3
        bfsTree.addEdge(2,4); bfsTree.addEdge(2,6); bfsTree.addEdge(2,7);
        //Node 6
        bfsTree.addEdge(5,4);
        //Node 7
        bfsTree.addEdge(6,7);


        System.out.println("BFS Visits: ");
        bfsTree.breadthFS();
        System.out.println();
    }

}

