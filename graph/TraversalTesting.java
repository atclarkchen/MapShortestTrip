package graph;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TraversalTesting {

    /** Test that DFS and BFS are working properly. */

    DirectedGraph g = new DirectedGraph();
    int v1 = g.add();
    int v2 = g.add();
    int v3 = g.add();
    int v4 = g.add();
    int v5 = g.add();

    int e1 = g.add(5, 4);
    int e2 = g.add(5, 3);
    int e3 = g.add(4, 1);
    int e4 = g.add(3, 2);
    int e5 = g.add(1, 5);

    public class DepthFirstTraversalX extends DepthFirstTraversal {

        protected DepthFirstTraversalX(Graph G) {
            super(G);
            processed = new ArrayList<Integer>();
        }

        protected boolean visit(int v) {
            processed.add(v);
            return super.visit(v);
        }

        public ArrayList<Integer> getProcessed() {
            ArrayList<Integer> temp = new ArrayList<Integer>();
            temp.addAll(processed);
            processed.clear();
            return temp;
        }

        /** Private variables */
        private ArrayList<Integer> processed;
    }

    public class DepthFirstPost extends DepthFirstTraversal {
        protected DepthFirstPost(Graph G) {
            super(G);
        }
        protected boolean shouldPostVisit(int v) {
            return true;
        }
        protected boolean postVisit(int v) {
            postVisited.add(v);
            return super.postVisit(v);
        }
        private ArrayList<Integer> getPostVisited() {
            return postVisited;
        }
        private ArrayList<Integer> postVisited = new ArrayList<Integer>();
    }

    public class BreathFirstTraversalX extends BreadthFirstTraversal {

        protected BreathFirstTraversalX(Graph G) {
            super(G);
            processed = new ArrayList<Integer>();
        }

        protected boolean visit(int v) {
            processed.add(v);
            return super.visit(v);
        }

        public ArrayList<Integer> getProcessed() {
            return processed;
        }
        private ArrayList<Integer> processed;
    }

    @Test
    public void dfsTest() {
        DepthFirstTraversalX dfsX = new DepthFirstTraversalX(g);

        List<Integer> expA1 = Arrays.asList(5, 3, 2, 4, 1);
        List<Integer> expA2 = Arrays.asList(5, 4, 1, 3, 2);

        dfsX.traverse(5);
        ArrayList<Integer> actual = dfsX.getProcessed();
        assertTrue(expA1.equals(actual) || expA2.equals(actual));

        DepthFirstPost dfsP = new DepthFirstPost(g);
        dfsP.traverse(5);
        List<Integer> expB1 = Arrays.asList(2, 3, 1, 4, 5);
        List<Integer> expB2 = Arrays.asList(1, 4, 2, 3, 5);

        ArrayList<Integer> act = dfsP.getPostVisited();
        assertTrue(expB1.equals(act) || expB2.equals(act));
    }

    @Test
    public void bfsTest() {
        BreathFirstTraversalX bfsX = new BreathFirstTraversalX(g);

        List<Integer> exp1 = Arrays.asList(5, 4, 3, 1, 2);
        List<Integer> exp2 = Arrays.asList(5, 3, 4, 1, 2);
        List<Integer> exp3 = Arrays.asList(5, 4, 3, 2, 1);
        List<Integer> exp4 = Arrays.asList(5, 3, 4, 2, 1);

        bfsX.traverse(5);
        ArrayList<Integer> actual = bfsX.getProcessed();

        assertTrue(exp1.equals(actual) || exp2.equals(actual)
            || exp3.equals(actual) || exp4.equals(actual));
    }
}
