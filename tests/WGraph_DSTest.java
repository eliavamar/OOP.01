package ex1.tests;

import ex1.src.WGraph_DS;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class WGraph_DSTest {

    /**
     * this method return connected graph with valid path from each node too all the nodes in the graph
     * and return the graph
     * this graph has 8 edges and 9 vertices
     *
     * @return graph
     */


    private WGraph_DS buildGraph1() {
        WGraph_DS graph = new WGraph_DS();
        for (int i = 1; i < 10; i++) {
            graph.addNode(i);
        }
        for (int i = 1; i < 9; i++) {
            graph.connect(i, i + 1, i);
        }
        return graph;
    }

    /**
     * this method build empty graph and return the graph
     *
     * @return graph 2
     */
    private WGraph_DS buildGraph2() {
        WGraph_DS graph2 = new WGraph_DS();
        return graph2;
    }

    /**
     * this method take take the graph1(buildGraph1 method)
     * and change the edge's value
     *
     * @return graph3
     */
    private WGraph_DS buildGraph3() {
        WGraph_DS graph3 = buildGraph1();
        for (int i = 1; i < 9; i++) {
            graph3.connect(i, i + 1, i * 5);
        }
        return graph3;
    }

    /**
     * this test check:
     * 1)if g1 has 8 edge's and if all the edge's that we connected are exist
     * 2)if g2 has no vertices
     * 3)that method hasEdge(g2) return -1
     */
    @Test
     void test1() {
        WGraph_DS g1 = buildGraph1();
        WGraph_DS g2 = buildGraph2();
        assertEquals(0, g2.edgeSize());
        assertTrue(g2.hasEdge(1, 1));
        for (int i = 1; i < 9; i++) {
            assertTrue(g1.hasEdge(i, i + 1));
            assertEquals(-1, g2.getEdge(i, i * 25));
            assertFalse(g2.hasEdge(200, i * 2));
        }
        g1.connect(1,2,30);
        assertEquals(8, g1.edgeSize());


    }

    /**
     * this test check:
     * 1) if method buildGraph3 change correctly the edge value
     * 2) if the edge connected
     * 3) if we get the node info after we remove it from the graph and if the same spot is null
     * 4) add node again
     */
    @Test
     void test2() {
        WGraph_DS g3 = buildGraph3();
        for (int i = 1; i < 9; i++) {
            assertNotEquals(i, g3.getEdge(i, i + 1));
            assertEquals(i * 5, g3.getEdge(i, i + 1));
            assertTrue(g3.hasEdge(i,i+1));
            assertFalse(g3.hasEdge(1,5));
        }
        assertEquals(g3.getNode(2),g3.removeNode(2));
        assertNull(g3.getNode(2));
        g3.addNode(2);
        assertNotNull(g3.getNode(2));
    }

}
