package ex1.src;


import java.io.*;
import java.util.*;
/**
 * This class implements the interface of weighted_graph_algorithms and represents the "regular" Graph Theory algorithms.
 *
 * @author eliav.amar
 *
 */

public class WGraph_Algo implements weighted_graph_algorithms {
    private weighted_graph graphAlgo;

    /**
     * constructor for WGraph_Algo
     */
    public WGraph_Algo() {
        graphAlgo = new WGraph_DS();
    }

    /**
     * Init the graph on which this set of algorithms operates on.
     *
     * @param g
     */
    @Override
    public void init(weighted_graph g) {
        graphAlgo = g;
    }

    /**
     * Return the underlying graph of which this class works.
     *
     * @return this graph
     */

    @Override
    public weighted_graph getGraph() {
        return graphAlgo;
    }

    /**
     * this method making a deep copy to the graph and return the deep copy.
     * in this graph i've used in Iterator (base data in java),i've insert
     * all the nodes in the graph to the iterator ,i've go all over the nodes that in the iterator
     * and i've insert one by one :the node info,connected the edges to each node siblings and updated the edge value
     *
     * @return weighted_graph (copy graph)
     */
    @Override
    public weighted_graph copy() {
        WGraph_DS copy = new WGraph_DS();
        Iterator<node_info> allNods = graphAlgo.getV().iterator();
        while (allNods.hasNext()) {
            node_info temp = allNods.next();
            copy.addNode(temp.getKey());
            copy.getNode(temp.getKey()).setInfo(temp.getInfo());
            copy.getNode(temp.getKey()).setTag(temp.getTag());
        }
        allNods = graphAlgo.getV().iterator();
        while (allNods.hasNext()) {
            node_info temp2 = allNods.next();
            int key = temp2.getKey();
            Iterator<node_info> neighbors = graphAlgo.getV(key).iterator();
            while (neighbors.hasNext()) {
                node_info temp3 = neighbors.next();
                int key2 = temp3.getKey();
                copy.connect(key, key2, graphAlgo.getEdge(key, key2));
            }
        }
        copy.setMC(graphAlgo.getMC());
        return copy;
    }

    /**
     * this method return true if and only if  there is a valid path from every node to each
     * other,else return false
     *
     * in this method i've used in Iterator(base data in java),
     * i've insert all the nodes that in the graph in to the iterator and "paint"
     * all the node info tag with "white".
     * i used ArrayDeque(base data in java) for insert random node from the iterator and then i've "paint"the node tag with black
     * i've run all over the node siblings and check if ive not painted them
     * if not i insert his node sibling in to the queue and "paint" his tag with black else do nothing.
     * After i've going through all the node sibling ,i poll new node from the queue and raise the counter by one.
     * and i've do it all over again.
     * if the counter equal to the node size the method return true else return false
     * @return boolean value
     */
    @Override
    public boolean isConnected() {
        if (graphAlgo.nodeSize() == 0 || graphAlgo.nodeSize() == 1)
            return true;
        Iterator<node_info> allNodes = graphAlgo.getV().iterator();

        while (allNodes.hasNext()) {
            node_info node = allNodes.next();
            node.setInfo("white");
        }
        allNodes = graphAlgo.getV().iterator();
        ArrayDeque<node_info> queue = new ArrayDeque<node_info>();
        int counter = 0;
        node_info node = allNodes.next();
        node.setInfo("black");
        queue.addFirst(node);
        while (!(queue.isEmpty())) {
            node = queue.pollLast();
            int key = node.getKey();
            counter++;
            Iterator<node_info> neighbors = graphAlgo.getV(key).iterator();
            while (neighbors.hasNext()) {
                node = neighbors.next();
                if (node.getInfo().equals("white")) {
                    queue.addFirst(node);
                    node.setInfo("black");
                }
            }

        }

        return counter == graphAlgo.nodeSize();
    }

    /**
     * this method returns the length of the shortest path between src to dest
     * Note: if no such path --> the method return -1
     *
     * in this method PriorityQueue(base data in java)
     * this PriorityQueue sort his object by the node's tag.
     * than insert all the node(src) siblings into Iterator(base data in java)
     * and then the method run all over the node siblings and update them tag,
     * after the method update all the siblings tag its poll from the PriorityQueue the firs object(remember that the queue is sorted)
     * and do it all over again.
     * the method return the node(dest) tag ,if we did not "visit" this node his tag stay -1
     * else he got in his tag the value of the shortest path
     *
     * @param src  - start node
     * @param dest - end (target) node
     * @return length of the sortest path (double value)
     */
    @Override
    public double shortestPathDist(int src, int dest) {
        if (src == dest)
            return 0;
        if (graphAlgo.getNode(src) == null || graphAlgo.getNode(dest) == null)
            return -1;
        Iterator<node_info> allNodes = graphAlgo.getV().iterator();
        while (allNodes.hasNext()) {
            node_info node = allNodes.next();
            node.setTag(-1);
        }
        PriorityQueue<node_info> queue = new PriorityQueue<node_info>(new WGraph_DS());
        node_info node = graphAlgo.getNode(src);
        node.setTag(0);
        queue.add(node);
        while (!queue.isEmpty()) {
            node = queue.poll();
            Iterator<node_info> nodeNi = graphAlgo.getV(node.getKey()).iterator();
            while (nodeNi.hasNext()) {
                node_info node1 = nodeNi.next();
                if (node1.getTag() != -1) {
                    double edge = graphAlgo.getEdge(node.getKey(), node1.getKey());
                    if (node1.getTag() > node.getTag() + edge) {
                        node1.setTag(edge + node.getTag());
                    }
                } else if (node1.getTag() == -1) {
                    double edge = graphAlgo.getEdge(node.getKey(), node1.getKey());
                    node1.setTag(edge + node.getTag());
                    queue.add(node1);
                }
            }

        }


        return graphAlgo.getNode(dest).getTag();
    }

    /**
     * this method return the the shortest path between src to dest - as an ordered List of nodes:
     * src--> n1-->n2-->...dest
     *
     * this method use the method shortestPathDist for set to each node info his tag that lead to the short path.
     * this method run all over the shortest path,start from the node(dest) go to and go back node(src)'
     * insert each one to LinkedList (base data in java)
     * and return the LinkedList
     * @param src  - start node
     * @param dest - end (target) node
     * @return list of the shortest path
     */
    @Override
    public List<node_info> shortestPath(int src, int dest) {
        double counter = shortestPathDist(src, dest);
        LinkedList<node_info> list = new LinkedList<node_info>();
        if (counter == -1)
            return list;
        if (counter == 0) {
            list.add(graphAlgo.getNode(src));
            return list;
        }
        node_info node = graphAlgo.getNode(dest);
        while (node.getKey() != src) {
            list.addFirst(node);
            Iterator<node_info> neighbors = graphAlgo.getV(node.getKey()).iterator();
            while (neighbors.hasNext()) {
                node_info node2 = neighbors.next();
                if (node2.getTag() + graphAlgo.getEdge(node.getKey(), node2.getKey()) == node.getTag()) {
                    node = node2;
                    break;
                }
            }
        }
        list.addFirst(node);


        return list;
    }

    /**
     * Saves this weighted (undirected) graph to the given
     * file name
     *
     * @param file - the file name (may include a relative path).
     * @return true - iff the file was successfully saved
     */
    @Override
    public boolean save(String file) {
        try {
            FileOutputStream fileOutPut = new FileOutputStream(file);
            ObjectOutputStream outPut = new ObjectOutputStream(fileOutPut);
            outPut.writeObject(graphAlgo);
            outPut.close();
            fileOutPut.close();
            return true;
        } catch (Exception i) {
            return false;
        }
    }

    /**
     * This method load a graph to this graph algorithm.
     * if the file was successfully loaded - the underlying graph
     * of this class will be changed (to the loaded one), in case the
     * graph was not loaded the original graph should remain "as is".
     *
     * @param file - file name
     * @return true - iff the graph was successfully loaded.
     */

    @Override
    public boolean load(String file) {
        try {
            FileInputStream fileInPut = new FileInputStream(file);
            ObjectInputStream inPut = new ObjectInputStream(fileInPut);
            graphAlgo = (WGraph_DS) inPut.readObject();
            inPut.close();
            fileInPut.close();
            return true;
        } catch (IOException i) {
            return false;
        } catch (ClassNotFoundException c) {
            return false;
        }
    }
}
