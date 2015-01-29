package graph;

import java.util.HashMap;


/** A partial implementation of ShortestPaths that contains the weights of
 *  the vertices and the predecessor edges.   The client needs to
 *  supply only the two-argument getWeight method.
 *  @author ClarkChen
 */
public abstract class SimpleShortestPaths extends ShortestPaths {

    /** The shortest paths in G from SOURCE. */
    public SimpleShortestPaths(Graph G, int source) {
        this(G, source, 0);
    }

    /** A shortest path in G from SOURCE to DEST. */
    public SimpleShortestPaths(Graph G, int source, int dest) {
        super(G, source, dest);
        predecessors = new HashMap<Integer, Integer>();
        distanceMap = new HashMap<Integer, Double>();
    }

    @Override
    public double getWeight(int v) {
        return distanceMap.get(v);
    }

    @Override
    protected void setWeight(int v, double w) {
        distanceMap.put(v, w);
    }

    @Override
    public int getPredecessor(int v) {
        if (!_G.contains(v) || !predecessors.containsKey(v)) {
            return 0;
        }
        return predecessors.get(v);
    }

    @Override
    protected void setPredecessor(int v, int u) {
        predecessors.put(v, u);
    }
    /** HashMap of predecessors used in this class.*/
    private HashMap<Integer, Integer> predecessors;
    /** HashMap mapping to weight. */
    private HashMap<Integer, Double> distanceMap;
}
