package graph;


/** Represents an undirected graph.  Out edges and in edges are not
 *  distinguished.  Likewise for successors and predecessors.
 *
 *  @author ClarkChen
 */
public class UndirectedGraph extends GraphObj {

    @Override
    public boolean isDirected() {
        return false;
    }

    @Override
    public int inDegree(int v) {

        return super.outDegree(v);
    }

    @Override
    public int predecessor(int v, int k) {
        return super.successor(v, k);
    }

    @Override
    public Iteration<Integer> predecessors(int v) {

        return super.successors(v);
    }

}
