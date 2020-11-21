# OOP.01

@author eliav.amar

Intrudoction:

this project represent a weighted undirectional  graph and it has three class

1)NodeInfo

2)WGraph_DS

3)WGraph_Algp


Getting Started:

1) NodeInfo-its a private class in WG_Graph that represent the vertices of the graph.
---------------------------------------------------------------------------------------------------------------------------
--NodeInfo contains three field--:
int key,String info,doublue tap.
--NodeInfo function--:
1.1) constructor for build a new node with the giving key->public NodeInfo(int key) 

1.2) get the key that associated with this node-> node.getKey() , return int value

1.3) set a new key to this node-> node.setKey(int key) , void

1.3) return the info of this node-> node. getInfo() , return string value

1.4) set a new info to this node-> node.setInfo(String s) , void

1.5) return the tag of this node-> node.getTag() , return double value 

1.6) set a new tag for this node -> node.setTag(double t) , void

------------------------------------------------------------------------------------------------------------------------------------------------
2) WGraph_DS-this class represent weighted undirectional.

-------------------------------------------------------------------------------------------------------------------------------------------


--WGraph_DS contains five field--:

-HashMap<Integer, node_info> graph--> this field contains all the graph's vertices

-HashMap<Integer, HashMap<Integer, node_info>> neighbors --> this field contains the "siblings" of each node

HashMap<IntegerHashMap<Integer, Double>> vEdge--> this field contains the edge's value 

-int edgeSize-

-int MC-

--WGraph_DS function--:

2.1)constructor for build new WGraph_DS -> public WGraph_DS()

2.2)return the node with the associated key -> graph.getNode(int key) , return NodeInfo value

2.3)return true if threre is a edge between the two nodes else return false(node1&node2 is the keys of the nodes)-> graph.hasEdge(int node1, int node2) , return boolean value


2.4)return the edge's value between the two nodes if there is no edge return -1 (node1&node2 is the keys of the nodes)-> graph.getEdge(int node1, int node2) ,return double value

2.5)create a new node in the graph by this giving key if the node is already exist the nethod do nothing -> graph.addNode(int key) , void

2.6)connecting an edge between two node with this giving key if there is an edge already the method just change the edge value 
if on of the nodes are not exist in the graph the method do nothing (node1&node2 is the keys of the nodes)-> graph.connect(int node1, int node2, double w) , void

2.7)return a pointer (shallow copy) for a Collection representing all the nodes in the graph -> graph. getV()  , return collection<node_info> value

2.8)returns a Collection that containing  all the nodes that connected to node with the giving key -> graph.getV(int node_id) , return collection<node_info> value

2.9)Delete the node withh the giving key and return the node that we deleted if the node are not exist return null -> graph. removeNode(int key) , return node_info value

2.10)Delete the edge betweeen the two nodes with the giving key from the graph 
if there is no edge the method do nothing(node1&node2 is the keys of the nodes) -> graph.removeEdge(int node1, int node2) ,void

2.11)return the number of vertices in the graph-> graph.nodeSize() , return int value

2.12)return the number of edges in the graph ->  graph.edgeSize() , return int value

2.13)return the Mode Count - for testing changes in the graph-> graph.getMC() , return int value

2.14)set new changes in the graph(this method was created for copy in WGraph_Algp class) -> graph.setMC(int mc) ,void

------------------------------------------------------------------------------------------------------------------------------------------------

3)WGraph_Algp-this class represent the "regular" Graph Theory algorithms

-----------------------------------------------------------------------------------------------------------------------------------------------=

--WGraph_Algp contain one field--:

weighted_graph graphAlgo



--WGraph_Algp function--:

3.1) constructor for WGraph_Algo -> public WGraph_Algo()

3.2)Init the graph on which this set of algorithms-> algoGraph. init(weighted_graph g) , void

3.3)Return the underlying graph of which this class works -> algoGraph.getGraph() , return  weighted_graph value

3.4)making a deep copy to this graph and return weighted_graph -> algoGraph.copy() , return  weighted_graph value

3.5)return true if and only if  there is a valid path from each node to any other node in the graph 
else return false -> algoGraph.isConnected() , return boolean value 

3.6)returns the length of the shortest path between src to dest if no such path the method return -1->algoGraph.shortestPathDist(int src, int dest) , return double value

3.7) this method return the the shortest path between src to dest - as an ordered List of nodes-> algoGraph.shortestPath(int src, int dest) , return lisst<node_info>

3.8) Saves this weighted (undirected) graph to the given file name if it success return true else false-> algoGraph.save(String file) , return boolean value

3.9)load a graph to this graph algorithm  if it success return true else false -> algoGraph.boolean load(String file) , return boolean value


--------------------------------------------------------------------------------------------------------------------------------------------------


