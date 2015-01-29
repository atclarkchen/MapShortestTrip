package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/** A partial implementation of Graph containing elements common to
 *  directed and undirected graphs.
 *
 *  @author ClarkChen
 */

abstract class GraphObj extends Graph {

    /** A new, empty Graph. */
    GraphObj() {
        _adjList = new HashMap<Integer, LinkedList<Integer>>();
        _vertexCount = 0;
        _edgeCount = 0;
        _currVertex = 0;
        removeQueue = new LinkedList<Integer>();
    }

    @Override
    public int vertexSize() {
        return _vertexCount;
    }

    @Override
    public int maxVertex() {
        return _currVertex;
    }

    @Override
    public int edgeSize() {
        if (!isDirected()) {
            return _edgeCount / 2;
        }
        return _edgeCount;
    }

    @Override
    public abstract boolean isDirected();

    @Override
    public int outDegree(int v) {
        if (_adjList.containsKey(v)) {
            LinkedList<Integer> t = _adjList.get(v);
            return t.size();
        }
        return 0;
    }

    @Override
    public abstract int inDegree(int v);

    @Override
    public boolean contains(int u) {
        return _adjList.containsKey(u);
    }

    @Override
    public boolean contains(int u, int v) {

        if (!contains(u)) {
            return false;
        }
        LinkedList<Integer> temp = _adjList.get(u);
        if (temp.contains(v)) {
            return true;
        }
        return false;
    }

    @Override
    public int add() {
        if (!removeQueue.isEmpty()) {
            int add = (int) removeQueue.poll();
            _adjList.put(add, new LinkedList<Integer>());
            _vertexCount++;
            return add;
        } else {
            _currVertex++;
            _adjList.put(_currVertex, new LinkedList<Integer>());
            _vertexCount++;
            return _currVertex;
        }
    }

    @Override
    public int add(int u, int v) {
        if (!_adjList.containsKey(u) || !_adjList.containsKey(v)) {
            return Integer.MIN_VALUE;
        }
        if (this.contains(u, v)) {
            return u;
        }
        LinkedList<Integer> t = _adjList.get(u);
        t.add(v);
        _adjList.put(u, t);

        _edgeCount++;
        if (!this.isDirected()) {
            if (u != v) {
                LinkedList<Integer> t2 = _adjList.get(v);
                t2.add(u);
            }
            _edgeCount++;
        }
        return u;
    }

    @Override
    public void remove(int v) {
        if (_adjList.containsKey(v)) {
            LinkedList<Integer> t = _adjList.get(v);
            int numEdges = t.size();
            _adjList.remove(v);
            removeQueue.add(v);
            _vertexCount--;
            _edgeCount -= numEdges;
            for (int i : _adjList.keySet()) {
                LinkedList<Integer> temp = _adjList.get(i);
                if (temp.remove(Integer.valueOf(v))) {
                    _edgeCount--;
                }
                _adjList.put(i, temp);
            }
            return;
        }
    }

    @Override
    public void remove(int u, int v) {

        /** Remove edge (U, V) from me, if present. */

        if (this.contains(u, v)) {
            LinkedList<Integer> temp = _adjList.get(u);
            temp.remove((Integer) v);
            _adjList.put(u, temp);
            _edgeCount--;
        }
    }

    @Override
    public Iteration<Integer> vertices() {
        Iteration<Integer> s = Iteration.iteration(_adjList.keySet());
        return s;
    }

    @Override
    public int successor(int v, int k) {
        if (this.contains(v)) {
            try {
                int s = _adjList.get(v).get(k);
                return s;
            } catch (IndexOutOfBoundsException e) {
                return 0;
            }
        }
        return 0;
    }

    @Override
    public abstract int predecessor(int v, int k);

    @Override
    public Iteration<Integer> successors(int v) {

        LinkedList<Integer> t = _adjList.get(v);
        if (t == null || t.isEmpty()) {
            return Iteration.iteration(new LinkedList<Integer>());
        }
        Iteration<Integer> iter = Iteration.iteration(t);
        return iter;
    }

    @Override
    public abstract Iteration<Integer> predecessors(int v);

    @Override
    public Iteration<int[]> edges() {
        ArrayList<int[]> edges = new ArrayList<int[]>();
        ArrayList<Integer> edgeID = new ArrayList<Integer>();
        for (int i : _adjList.keySet()) {
            LinkedList<Integer> t = _adjList.get(i);
            for (int n : t) {
                int[] add = new int[2];
                add[0] = i;
                add[1] = n;
                if (!edgeID.contains(this.edgeId(i, n))) {
                    edges.add(add);
                    edgeID.add(this.edgeId(i, n));
                }
            }
        }
        Iteration<int[]> edgeIter = Iteration.iteration(edges);
        return edgeIter;
    }

    @Override
    protected boolean mine(int v) {
        if (_adjList.containsKey(v)) {
            return true;
        }
        return false;
    }

    @Override
    protected void checkMyVertex(int v) {
        if (!_adjList.containsKey(v)) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    protected int edgeId(int u, int v) {
        if (!this.contains(u, v)) {
            return 0;
        }
        int edgeID;
        if (!this.isDirected()) {
            if (u > v) {
                int temp = v;
                v = u;
                u = temp;
            }
        }
        edgeID = (int) (0.5 * (u + v) * (u + v + 1) + v);
        return edgeID;
    }

    /** Private variables. */

    /** Data structure used to represent this class. */
    private HashMap<Integer, LinkedList<Integer>> _adjList;

    /** Current number of vertices in this graph.*/
    private int _vertexCount;

    /** Current number of edges in this graph. */
    private int _edgeCount;

    /** Represents the number of the current vertex. */
    private int _currVertex;

    /** Used for edges iteration. Contains only unique edgeID's.*/

    private LinkedList<Integer> removeQueue;
}
