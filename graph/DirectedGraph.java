package graph;

import java.util.ArrayList;


/** Represents a general unlabeled directed graph whose vertices are denoted by
 *  positive integers. Graphs may have self edges.
 *
 *  @author ClarkChen
 */
public class DirectedGraph extends GraphObj {

    @Override
    public boolean isDirected() {
        return true;
    }

    @Override
    public int inDegree(int v) {
        int inDegree = 0;
        if (!this.contains(v)) {
            return 0;
        }
        Iteration<int[]> iter = this.edges();
        for (int[] t : iter) {
            if (t[1] == v) {
                inDegree++;
            }
        }
        return inDegree;
    }

    @Override
    public int predecessor(int v, int k) {

        int rtn = 0;
        int count = inDegree(v);

        if ((count == 0) || (k > count - 1) || (k < 0)) {
            return 0;
        }

        int i = 0;
        for (int[] edge : this.edges()) {
            if (edge[1] == v) {
                _predecessors.add(edge[0]);
                if (i == k) {
                    return edge[0];
                }
                i++;
            }
        }

        return rtn;
    }

    @Override
    public Iteration<Integer> predecessors(int v) {
        _predecessors = new ArrayList<Integer>();
        int k = inDegree(v) - 1;
        this.predecessor(v, k);
        Iteration<Integer> iter = Iteration.iteration(_predecessors);
        return iter;
    }
    /** Arraylist of predecessors. */
    private ArrayList<Integer> _predecessors = new ArrayList<Integer>();

}
