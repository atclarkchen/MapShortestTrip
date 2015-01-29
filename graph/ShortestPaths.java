package graph;

/* See restrictions in Graph.java. */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/** The shortest paths through an edge-weighted graph.
 *  By overrriding methods getWeight, setWeight, getPredecessor, and
 *  setPredecessor, the client can determine how to represent the weighting
 *  and the search results.  By overriding estimatedDistance, clients
 *  can search for paths to specific destinations using A* search.
 *  @author ClarkChen
 *  Discussed with Rachel Zhang
 */
public abstract class ShortestPaths {

    /** Distance based comparator to be used by DistanceQueue. */
    private class DistanceComparator implements Comparator<Integer> {
        @Override
        /** This is the foundation of the distance queue. */

        public int compare(Integer v1, Integer v2) {
            double weight1 = getWeight(v1) + estimatedDistance(v1);
            double weight2 = getWeight(v2) + estimatedDistance(v1);

            if (weight1 > weight2) {
                return 1;
            } else if (weight1 < weight2) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    /** A* Traversal class. */
    private class ShortestPathX extends Traversal {
        /** Constructor for class that takes in graph G. */
        protected ShortestPathX(Graph G) {
            super(G, new LinkedList<Integer>());
            _G = G;
        }

        @Override
        protected boolean processSuccessor(int u, int v) {
            return false;
        }

        @Override
        protected boolean marked(int v) {
            return false;
        }

        @Override
        protected void mark(int v) {
            return;
        }

        /** Returns a boolean V when it is visited. */
        protected boolean visit(int v) {
            if (v == getDest()) {
                return false;
            }
            DistanceComparator dc = new DistanceComparator();
            for (int w : _G.successors(v)) {
                double weightN = getWeight(v, w);
                if (getWeight(w) > (weightN + getWeight(v))) {
                    setWeight(w, weightN + getWeight(v));
                    setPredecessor(w, v);
                }
            }
            Collections.sort((LinkedList<Integer>) _fringe, dc);
            return true;
        }
        /** Private variables. */
        private Graph _G;
    }

    /** The shortest paths in G from SOURCE. */
    public ShortestPaths(Graph G, int source) {
        this(G, source, 0);
    }

    /** A shortest path in G from SOURCE to DEST. */
    public ShortestPaths(Graph G, int source, int dest) {
        _G = G;
        _source = source;
        _dest = dest;
        shortestPath = new ArrayList<Integer>();
    }

    /** Initialize the shortest paths.  Must be called before using
     *  getWeight, getPredecessor, and pathTo. */
    public void setPaths() {
        for (int v : _G.vertices()) {
            setPredecessor(v, 0);
            if (v == this._source) {
                setWeight(v, 0);
            } else {
                setWeight(v, Double.POSITIVE_INFINITY);
            }
        }
        ArrayList<Integer> vertices = new ArrayList<Integer>();
        for (int i : _G.vertices()) {
            vertices.add(i);
        }
        ShortestPathX spTraversal = new ShortestPathX(_G);
        spTraversal.traverse(vertices);
    }

    /** Returns the starting vertex. */
    public int getSource() {
        return _source;
    }

    /** Returns the target vertex, or 0 if there is none. */
    public int getDest() {
        return _dest;
    }

    /** Returns the current weight of vertex V in the graph.  If V is
     *  not in the graph, returns positive infinity. */
    public abstract double getWeight(int v);

    /** Set getWeight(V) to W. Assumes V is in the graph. */
    protected abstract void setWeight(int v, double w);

    /** Returns the current predecessor vertex of vertex V in the graph, or 0 if
     *  V is not in the graph or has no predecessor. */
    public abstract int getPredecessor(int v);

    /** Set getPredecessor(V) to U. */
    protected abstract void setPredecessor(int v, int u);

    /** Returns an estimated heuristic weight of the shortest path from vertex
     *  V to the destination vertex (if any).  This is assumed to be less
     *  than the actual weight, and is 0 by default. */
    protected double estimatedDistance(int v) {
        return 0.0;
    }

    /** Returns the current weight of edge (U, V) in the graph.  If (U, V) is
     *  not in the graph, returns positive infinity. */
    protected abstract double getWeight(int u, int v);

    /** Returns a list of vertices starting at _source and ending
     *  at V that represents a shortest path to V.  Invalid if there is a
     *  destination vertex other than V. */
    public List<Integer> pathTo(int v) {
        Stack<Integer> temp = new Stack<Integer>();
        int x = _dest;
        temp.push(x);
        while (x != _source) {
            int curr = getPredecessor(x);
            temp.push(curr);
            x = curr;
        }
        while (!temp.isEmpty()) {
            shortestPath.add(temp.pop());
        }
        return shortestPath;
    }

    /** Returns a list of vertices starting at the source and ending at the
     *  destination vertex. Invalid if the destination is not specified. */
    public List<Integer> pathTo() {
        return pathTo(getDest());
    }

    /** The graph being searched. */
    protected final Graph _G;
    /** The starting vertex. */
    private final int _source;
    /** The target vertex. */
    private final int _dest;
    /** The shortest path.*/
    private List<Integer> shortestPath;
}
