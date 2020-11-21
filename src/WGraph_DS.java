package ex1.src;

import java.util.*;
/**
 *  this class an implementation of the weighted_graph interface which represents an undirectional unweighted graph.
 * using an HashMap to contain the node (vertex) of the graph.
 * using an HashMap of HashMap to contain the node siblings of the graph.
 *  sing an HashMap of HashMap to contain the value of the nodes edge of the graph.
 *  each node has associate key for:graph,neighbor,vEdge.
 *  this class contains private class NodeInfo(description for NodeInfo is above the class NodeInfo)
 *  * @author Eliav.Amar
 *
 */

public class WGraph_DS implements weighted_graph, Comparator<node_info> {
    private HashMap<Integer, node_info> graph;
    private HashMap<Integer, HashMap<Integer, node_info>> neighbors;
    private HashMap<Integer, HashMap<Integer, Double>> vEdge;

   private int edgeSize;
     private  int MC;


    /**
     * constructor for WGraph_DS
     */
    public WGraph_DS() {
        graph = new HashMap<Integer, node_info>();
        neighbors = new HashMap<Integer, HashMap<Integer, node_info>>();
        vEdge = new HashMap<Integer, HashMap<Integer, Double>>();
        edgeSize = MC = 0;
    }


    @Override
    /**
     * this method return the node_info with this associated  key
     * @param key
     * @return NodeInfo
     * */
    public node_info getNode(int key) {
        if (graph.get(key) != null)
            return graph.get(key);
        return null;
    }

    /**
     * this method return true if there is edge between node1 to node2(node1&node2 is key for the node_info in the graph)
     * if there is no edge between the nodes or one of the nodes are not exist return false
     *
     * @param node1
     * @param node2
     * @retuen boolean value
     */
    @Override
    public boolean hasEdge(int node1, int node2) {
        if (node1 == node2)
            return true;
        if (graph.get(node1) == null || graph.get(node2) == null || neighbors.get(node1).get(node2) == null || neighbors.get(node2).get(node1) == null)
            return false;
        return true;

    }

    /**
     * this method return the value of the edge that connecting between the two nodes
     * if there is no edge return -1
     *
     * @param node1
     * @param node2
     * @return edge value
     */
    @Override
    public double getEdge(int node1, int node2) {
        if ((!(hasEdge(node1, node2))))
            return -1;
        if (node1 == node2)
            return 0;
        return vEdge.get(node1).get(node2);
    }

    /**
     * this method add a new node to this graph with the giving key
     *
     * @param key
     */
    @Override
    public void addNode(int key) {
        if (graph.get(key) != null)
            return;
        node_info node = new NodeInfo(key);
        graph.put(key, node);
        HashMap<Integer, node_info> Ni = new HashMap<Integer, node_info>();
        HashMap<Integer, Double> edgeValue = new HashMap<Integer, Double>();
        neighbors.put(key, Ni);
        vEdge.put(key, edgeValue);
        MC++;


    }

    /**
     * this method connecting an edge between node1 and node2 with the giving key
     * W is the weight of the edge
     *
     * @param node1
     * @param node2
     */
    @Override
    public void connect(int node1, int node2, double w) {
        if (w <= 0 || graph.get(node1) == null || graph.get(node2) == null)
            return;
        if (hasEdge(node1, node2)) {
            vEdge.get(node1).put(node2, w);
            vEdge.get(node2).put(node1, w);
            MC++;
            return;
        }
        neighbors.get(node1).put(node2, graph.get(node2));
        neighbors.get(node2).put(node1, graph.get(node1));
        vEdge.get(node1).put(node2, w);
        vEdge.get(node2).put(node1, w);
        edgeSize++;
        MC++;

    }

    /**
     * This method return a pointer (shallow copy) for a
     * Collection representing all the nodes in the graph.
     *
     * @return Collection<node_info>
     */
    @Override
    public Collection<node_info> getV() {
        return graph.values();
    }

    /**
     * This method returns a Collection that containing to all the nodes that connected to node_id
     *
     * @param node_id
     * @return Collection<node_info>
     */
    @Override
    public Collection<node_info> getV(int node_id) {
        if (graph.get(node_id) == null) {
            Collection<node_info> c = new ArrayList<node_info>();
            return c;
        }
        return neighbors.get(node_id).values();
    }

    /**
     * this method Delete the node (with the given ID) from the graph -
     * and removes all edges which starts or ends at this node
     *
     * @param key
     * @return node_info(node that we ' ve deleted)
     */
    @Override
    public node_info removeNode(int key) {
        if (graph.get(key) == null)
            return null;
        Iterator<node_info> neighbor = getV(key).iterator();
        while (neighbor.hasNext()) {
            node_info temp = neighbor.next();
            neighbors.get(temp.getKey()).remove(key);
            vEdge.get(temp.getKey()).remove(key);
            edgeSize--;
        }
        MC++;
        return graph.remove(key);
    }

    /**
     * Delete the edge from the graph
     *
     * @param node1
     * @param node2
     */
    @Override
    public void removeEdge(int node1, int node2) {
        if ((hasEdge(node1, node2) && node1 != node2)) {
            neighbors.get(node1).remove(node2);
            neighbors.get(node2).remove(node1);
            vEdge.get(node1).remove(node2);
            vEdge.get(node2).remove(node1);
            edgeSize--;
            MC++;
        }

    }

    /**
     * this method return the number of vertices (nodes) in the graph.
     *
     * @return graph size(int value)
     */
    @Override
    public int nodeSize() {
        return graph.size();
    }

    /**
     * this method return the number of edges (undirectional graph).
     *
     * @return edge  size(int value)
     */
    @Override
    public int edgeSize() {
        return edgeSize;
    }

    /**
     * this method return the Mode Count - for testing changes in the graph.
     *
     * @return MC(int value)
     */
    @Override
    public int getMC() {
        return MC;
    }

    /**
     * set new changes in the graph(for the copy constructor)
     *
     * @param mc
     */
    public void setMC(int mc) {
        MC = mc;
    }

    /**
     * this method get two nodes and compere them by there tap
     *
     * @param o1
     * @param o2
     * @return if node1>node2 return 1 if they equal return 0 else return -1
     */
    @Override
    public int compare(node_info o1, node_info o2) {
        int ans = 0;
        if (o1.getTag() > o2.getTag())
            ans = 1;
        if (o2.getTag() > o1.getTag())
            ans = -1;

        return ans;
    }
/**
 * this method compere between this graph and the graph that receive from the file.
 * this method go all over the two graphs and check if there :neighbors,edges value,node
 * are equal if they are return true else return false
 * @param obj
 * @return if equal true else return false*/
    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof WGraph_DS))
            return false;
        WGraph_DS cGr = (WGraph_DS) obj;
        if (cGr.nodeSize() != this.nodeSize() || cGr.getMC() != this.getMC() || cGr.edgeSize != this.edgeSize)
            return false;
        Iterator<node_info> copyGr = cGr.getV().iterator();
        while (copyGr.hasNext()) {
            node_info node1 = copyGr.next();
            if (graph.get(node1.getKey()) == null)
                return false;
            if (graph.get(node1.getKey()).getTag() != node1.getTag() || graph.get(node1.getKey()).getInfo() != node1.getInfo())
                return false;
            Iterator<node_info> Ni = cGr.getV(node1.getKey()).iterator();
            while (Ni.hasNext()) {
                node_info node2 = Ni.next();
                if (graph.get(node2.getKey()) == null)
                    return false;
                if (neighbors.get(node1.getKey()).get(node2.getKey()) == null || vEdge.get(node1.getKey()).get(node2.getKey()) != cGr.getEdge(node1.getKey(), node2.getKey()))
                    return false;

            }

        }
        return true;
    }
    /**
     * This class implements the node_info interface
     * and represents the set of operations applicable on a
     * node (vertex) in an (undirectional) weighted graph.
     * @author Eliav Amar
     */

    private class NodeInfo implements node_info {
        private int key;
        private String info;
        private double tag;


        /**
         * constructor for NodeInfo
         *
         * @param key
         */
        public NodeInfo(int key) {
            this.key = key;


        }

        /**
         * this method return the key (id) associated with this node.
         *
         * @return key(int)
         */
        @Override
        public int getKey() {
            return this.key;
        }

        /**
         * this method set new key to this node
         *
         * @param key
         */
        public void setKey(int key) {
            this.key = key;
        }

        /**
         * this method return the remark (meta data) associated with this node.
         *
         * @return info(String)
         */

        @Override
        public String getInfo() {
            return this.info;
        }

        /**
         * this method set new info to this node
         *
         * @param s
         */
        @Override
        public void setInfo(String s) {
            this.info = s;

        }

        /**
         * this method get the tag that associated with this node
         *
         * @return tag(double)
         */

        @Override
        public double getTag() {
            return this.tag;
        }

        /**
         * this method set new tag to this node
         *
         * @param t
         */
        @Override
        public void setTag(double t) {
            this.tag = t;

        }


    }
}