package graph;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

public class PathsTesting {
    /**Class used like a client would be; basically tests SimpleShortestPaths.*/
    class VideoGraphPaths extends SimpleShortestPaths {

        public VideoGraphPaths(Graph G, int source, int dest) {
            super(G, source, dest);
            edgeWeights = new HashMap<Integer, Double>();
            estimatedDistances = new HashMap<Integer, Double>();
        }

        @Override
        public double getWeight(int u, int v) {
            return edgeWeights.get(_G.edgeId(u, v));
        }

        @Override
        public double estimatedDistance(int v) {
            return estimatedDistances.get(v);
        }

        public void setEdgeWeight(int edgeID, double weight) {
            edgeWeights.put(edgeID, weight);
        }

        public void setEstDist(int vertex, double est) {
            estimatedDistances.put(vertex, est);
        }
        /** Private fields (necessary??) */
        private HashMap<Integer, Double> edgeWeights;
        private HashMap<Integer, Double> estimatedDistances;
    }

    @Test
    public void testWeights() {
        DirectedGraph G = new DirectedGraph();
        int v1 = G.add(); int v2 = G.add(); int v3 = G.add();
        int v4 = G.add(); int v5 = G.add(); int v6 = G.add();
        int v7 = G.add();
        int v8 = G.add();

        G.add(v4, v3); G.add(v4, v2); G.add(v4, v5);
        G.add(v2, v3);
        G.add(v5, v3); G.add(v5, v6);
        G.add(v6, v7);
        G.add(v4, v1);
        G.add(v1, v8);


        VideoGraphPaths vgp = new VideoGraphPaths(G, 4, 3);
        vgp.setEdgeWeight(G.edgeId(v4, v2), 12.2);
        vgp.setEdgeWeight(G.edgeId(v4, v5), 11.2);
        vgp.setEdgeWeight(G.edgeId(v2, v3), 6.5);
        vgp.setEdgeWeight(G.edgeId(v5, v6), 30);
        vgp.setEdgeWeight(G.edgeId(v4, v3), 102);
        vgp.setEdgeWeight(G.edgeId(v5, v3), 9.1);
        vgp.setEdgeWeight(G.edgeId(v6, v7), 100000);
        vgp.setEdgeWeight(G.edgeId(v4, v1), 3);
        vgp.setEdgeWeight(G.edgeId(v1, v8), 4);


        vgp.setEstDist(v2, 4.0);
        vgp.setEstDist(v3, 0.0);
        vgp.setEstDist(v4, 102.0);
        vgp.setEstDist(v5, 5.1);
        vgp.setEstDist(v6, 40.0);
        vgp.setEstDist(v7, 8.0);
        vgp.setEstDist(v1, 1000000000);
        vgp.setEstDist(v8, 10);

        vgp.setPaths();


        assertEquals(4, vgp.getSource());
        assertEquals(3, vgp.getDest());


        List<Integer> actual = vgp.pathTo(3);


        List<Integer> expected = new ArrayList<Integer>();
        expected = Arrays.asList(4, 2, 3);
        assertEquals(expected, actual);

        assertEquals(Double.POSITIVE_INFINITY, vgp.getWeight(v7), 0.001);
        assertEquals(Double.POSITIVE_INFINITY, vgp.getWeight(v8), 0.001);
    }
}
