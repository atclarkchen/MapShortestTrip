package graph;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import static org.junit.Assert.*;

/** Unit tests for the Graph class.
 *  @author ClarkChen
 */
public class GraphTesting {

    @Test
    public void emptyGraph() {
        DirectedGraph g = new DirectedGraph();
        assertEquals("Initial graph has vertices", 0, g.vertexSize());
        assertEquals("Initial graph has edges", 0, g.edgeSize());
        assertEquals(g.isDirected(), true);

        UndirectedGraph u = new UndirectedGraph();
        assertEquals("Initial graph has vertices", 0, u.vertexSize());
        assertEquals("Initial graph has edges", 0, u.edgeSize());
        assertEquals(u.isDirected(), false);
    }

    @Test
    public void basicTests() {
        DirectedGraph g = new DirectedGraph();
        int v1 = g.add();
        int v2 = g.add();
        int v3 = g.add();
        assertEquals(0, g.edgeSize());
        assertTrue(g.contains(1));
        assertTrue(g.contains(2));
        assertTrue(!g.contains(4));
        g.remove(20);
        assertEquals(g.vertexSize(), 3);
        g.remove(v2);
        assertEquals(g.vertexSize(), 2);
        assertEquals(3, g.maxVertex());
        assertEquals(2, g.add());
        assertEquals(3, g.vertexSize());
        assertEquals(3, g.maxVertex());
        int v4 = g.add();
        assertEquals(4, v4);
        assertEquals(4, g.vertexSize());
        assertTrue(g.contains(2));
        g.remove(v1);
        assertEquals(3, g.vertexSize());
        assertEquals(4, g.maxVertex());
        assertTrue(!g.contains(1));
        g.add();
        assertTrue(g.contains(1));
        int e1 = g.add(v1, v2);
        int e0 = g.add(20, 20);
        int e2 = g.add(v1, v2);
        assertEquals(1, g.edgeSize());
        int e3 = g.add(v1, v3);
        assertEquals(2, g.edgeSize());
        int e4 = g.add(v3, v4);
        int e5 = g.add(v4, v2);
        int e6 = g.add(v2, v3);
        assertEquals(5, g.edgeSize());
        g.remove(v2);
        assertEquals(3, g.vertexSize());
        assertEquals(2, g.edgeSize());
        assertEquals(0, g.predecessor(v2, 0));
        assertEquals(1, g.predecessor(v3, 0));
        g.remove(v1, v3);
        g.remove(20, 1);
        assertEquals(1, g.edgeSize());
        assertEquals(3, g.vertexSize());
        assertEquals(2, g.add());
        g.add(2, 1);
        g.add(2, 2);
        assertEquals(3, g.edgeSize());
        assertEquals(1, g.successor(2, 0));
        assertEquals(2, g.successor(2, 1));
        int self = g.add(v2, v2);
        assertEquals(2, g.predecessor(1, 0));
        assertEquals(2, g.predecessor(2, 0));
    }

    @Test
    public void iterationTests() {
        GraphObj g = new DirectedGraph();
        int v1 = g.add();
        int v2 = g.add();
        int v3 = g.add();
        int v4 = g.add();
        int v5 = g.add();
        g.add(v1, v2);
        g.add(v1, v3);
        g.add(v1, v4);
        g.add(v2, v3);
        g.add(v3, v4);
        g.add(v4, v1);
        g.add(v4, v5);
        g.add(v5, v4);
        assertTrue(!g.mine(6));
        assertTrue(g.mine(4));

        int count = 0;
        for (int[] x : g.edges()) {
            count++;
        }
        assertEquals(8, count);

        assertEquals(0, g.edgeId(v4,  v2));
        assertTrue(g.edgeId(v4, v5) != g.edgeId(v5, v4));


        assertEquals(3, g.outDegree(v1));

        assertEquals(v2, g.successor(v1, 0));
        assertEquals(v3, g.successor(v1, 1));
        assertEquals(0, g.successor(8, 100));
    }

    @Test
    public void undirectedTest() {
        UndirectedGraph ug = new UndirectedGraph();
        int v1 = ug.add();
        assertEquals(1, ug.vertexSize());
        int v2 = ug.add();
        int e1 = ug.add(v1, v2);
        assertEquals(v1, e1);
        assertEquals(1, ug.edgeSize());
        int e0 = ug.add(v2, v1);
        int e00 = ug.add(v1, v2);
        assertEquals(1, ug.edgeSize());
        assertEquals(ug.edgeId(v1, v2), ug.edgeId(v2, v1));
        assertEquals(v2, ug.successor(v1, 0));
        assertEquals(v1, ug.successor(v2, 0));
        assertEquals(0, ug.successor(v1, 1));
        int v3 = ug.add(); int v4 = ug.add();
        int v5 = ug.add();
        ug.add(v1, v2);
        ug.add(v1, v3);
        ug.add(v1, v4);
        ug.add(v2, v3);
        ug.add(v3, v4);
        ug.add(v4, v1);
        ug.add(v4, v5);
        ug.add(v5, v4);
        assertEquals(6, ug.edgeSize());
        int count = 0;
        assertEquals(3, ug.outDegree(v1));
        assertEquals(2, ug.outDegree(v2));
        UndirectedGraph ug1 = new UndirectedGraph();
        ug1.add();
        ug1.add(); ug1.add();
        ug1.add(1, 2); ug1.add(2, 3);
        ug1.add(3, 1);
        assertEquals(3, ug1.successor(1, 1));
        assertEquals(2, ug1.successor(3, 0));
        assertEquals(3, ug1.successor(2, 1));
        assertEquals(3, ug1.edgeSize());
        ug1.add(1, 1);
        assertEquals(4, ug1.edgeSize());
        for (int[] x : ug1.edges()) {
            count++;
        }
        UndirectedGraph ug2 = new UndirectedGraph();
        ug2.add(); ug2.add(1, 1);
        assertEquals(1, ug2.inDegree(1));
        assertEquals(1, ug2.edgeSize());
        ug2.add(); ug2.add(2, 2);
        assertEquals(1, ug2.outDegree(2));
        assertEquals(2, ug2.edgeSize());
        ug2.add(1, 2);
        assertEquals(3, ug2.edgeSize());
        assertEquals(2, ug2.inDegree(1));
        assertEquals(2, ug2.inDegree(2));
        assertEquals(2, ug2.outDegree(1));
    }

    @Test
    public void directedTest() {
        DirectedGraph g = new DirectedGraph();
        int v1 = g.add();
        int v2 = g.add();
        g.add(v1, v2);
        assertEquals(1, g.edgeSize());
        g.add(v2, v1);
        assertEquals(2, g.edgeSize());

        int v3 = g.add();
        int v4 = g.add();
        int v5 = g.add();
        g.add(v1, v3);
        g.add(v1, v4);
        g.add(v2, v3);
        g.add(v3, v4);
        g.add(v4, v1);
        g.add(v4, v5);
        g.add(v5, v4);

        assertEquals(9, g.edgeSize());
        assertEquals(3, g.outDegree(v1));
        assertEquals(2, g.outDegree(v4));

        assertEquals(3, g.inDegree(v4));
        assertEquals(2, g.inDegree(v3));
        assertEquals(2, g.inDegree(v1));
        assertEquals(1, g.inDegree(v5));
    }

    @Test
    public void predecessorTest() {

        DirectedGraph g = new DirectedGraph();
        int v1 = g.add(); int v2 = g.add();
        int v3 = g.add(); int v4 = g.add();
        g.add(v1, v2);
        g.add(v1, v3);
        g.add(v2, v3);
        g.add(v3, v4);
        g.add(v4, v2);

        assertEquals(0, g.predecessor(v1, 0));
        assertEquals(0, g.predecessor(v3, 2));
        assertEquals(1 , g.predecessor(v3, 0));
        assertEquals(2, g.predecessor(v3, 1));
        assertEquals(1, g.predecessor(v2, 0));
        assertEquals(4, g.predecessor(v2, 1));


        ArrayList<Integer> expV3 = new ArrayList<Integer>();
        expV3.add(1); expV3.add(2);
        ArrayList<Integer> actualV3 = new ArrayList<Integer>();
        for (int i : g.predecessors(v3)) {
            actualV3.add(i);
        }
        assertTrue(Arrays.equals(expV3.toArray(), actualV3.toArray()));

        ArrayList<Integer> expV4 = new ArrayList<Integer>();
        expV4.add(3);
        ArrayList<Integer> actualV4 = new ArrayList<Integer>();
        for (int n : g.predecessors(v4)) {
            actualV4.add(n);
        }
        assertTrue(Arrays.equals(expV4.toArray(), actualV4.toArray()));
    }

    /** Test that fails autograder (CPU run-time) passes here. */
    @Test
    public void traversalCase() {
        UndirectedGraph ug = new UndirectedGraph();
        ug.add(); ug.add(); ug.add(); ug.add(); ug.add();
        ug.add(); ug.add(); ug.add(); ug.add(); ug.add();
        ug.add(1, 8); ug.add(1, 1); ug.add(1, 2); ug.add(1, 3); ug.add(1, 4);
        ug.add(2, 5); ug.add(2, 3); ug.add(2, 6); ug.add(2, 2); ug.add(2, 1);
        ug.add(3, 2); ug.add(3, 7); ug.add(3, 8); ug.add(3, 1);
        ug.add(4, 1);
        ug.add(5, 2);
        ug.add(6, 2);
        ug.add(7, 3); ug.add(7, 10);
        ug.add(8, 3); ug.add(8, 1); ug.add(8, 9); ug.add(8, 8); ug.add(8, 10);
        ug.add(9, 8);
        ug.add(10, 8); ug.add(10, 7);

        Traversal dftTrav = new DepthFirstTraversal(ug);
        Traversal bftTrav = new BreadthFirstTraversal(ug);

        dftTrav.traverse(1);
        bftTrav.traverse(1);
    }
}
