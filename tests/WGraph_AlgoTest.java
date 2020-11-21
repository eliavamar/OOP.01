package ex1.tests;

import ex1.src.WGraph_Algo;
import ex1.src.WGraph_DS;
import ex1.src.node_info;
import ex1.src.weighted_graph;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class implements the interface of wgraph_algorithms and represents the "regular" Graph Theory algorithms.
 *
 * @author eliav.amar
 */

public class WGraph_AlgoTest {


    /**
     * this method build graph (not connected) with 99 vertices and 96 edges
     *
     * @return this graph (WGraph_DS)
     */
    private WGraph_DS buildGraph1() {
        WGraph_DS graph = new WGraph_DS();

        for (int i = 1; i < 100; i++) {
            graph.addNode(i);
        }
        for (int i = 0; i < 97; i++) {
            graph.connect(i, i + 2, i);


        }
        return graph;
    }

    /**
     * this method build connected graph with 199 vertices and 201 edges
     *
     * @return this graph(WGraph_DS)
     */
    private WGraph_DS buildGraph2() {
        WGraph_DS graph = new WGraph_DS();
        int j = 0;
        for (int i = 1; i < 200; i++) {
            j++;
            graph.addNode(i);
        }
        int c = 0;
        for (int i = 0; i < 200; i++) {
            c++;
            graph.connect(i, i + 1, i);
        }

        graph.connect(1, 150, 20);
        return graph;
    }

    /**
     * this test check :
     * 1) if g1 is empty if its connected(true)
     * 2) if there is not path in g1 return -1
     * 2) g edges
     * 3) g is not connected,if method init work well then we expect that g1 will ne not connected
     * 4) if if g2=g after than we used the method copy
     */
    @Test
    void test1() {
        WGraph_DS g = buildGraph1();
        WGraph_Algo g1 = new WGraph_Algo();
        assertEquals(-1, g1.shortestPathDist(12, 5));
        assertTrue(g1.isConnected());
        assertEquals(g.edgeSize(), 96);
        g1.init(g);
        assertFalse(g1.isConnected());
        weighted_graph g2 = g1.copy();
        for (int i = 0; i < 97; i++) {
            assertEquals(g2.getEdge(i, i + 2), g.getEdge(i, i + 2));
            assertEquals(g2.nodeSize(), g.nodeSize());
            assertEquals(g2.edgeSize(), g.edgeSize());

        }

    }

    /**
     * this test check :
     * 1) if g1 is connected graph
     * 2) what the shortestdist path from 1->150
     * 3)what the shortest path from 1->150
     */
    @Test
    void test2() {
        WGraph_DS g = buildGraph2();
        WGraph_Algo g1 = new WGraph_Algo();
        g1.init(g);
        assertTrue(g1.isConnected());
        assertEquals(20, g1.shortestPathDist(1, 150));
        assertNotEquals(50, g1.shortestPathDist(1, 35));
        LinkedList<node_info> shortPath = new LinkedList<node_info>();
        shortPath.addFirst(g1.getGraph().getNode(150));
        shortPath.addFirst(g1.getGraph().getNode(1));
        assertEquals(shortPath, g1.shortestPath(1, 150));

    }
/**
 * this test check:
 * 1) the methods load and save
 * 2) the method equals
 * */
    @Test
    void test3() {
       weighted_graph g1=buildGraph1();
        WGraph_Algo aG1 = new WGraph_Algo();
        aG1.init(g1);
        String str = "aG1.obj";
        aG1.save(str);
        weighted_graph g2=buildGraph1();
        assertEquals(g1,g2);

    }
}