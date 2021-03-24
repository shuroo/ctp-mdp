package org.ctp.snapshot;

import org.ctp.mdp.StateList;
import org.jgrapht.graph.Edge;
import org.jgrapht.graph.Graph;

import java.util.HashMap;

public class GraphSnapshot {


    private Graph graph;
    // this is the agent history...
    public StateList statesList;

    public Graph getGraph() {
        return graph;
    }

    // fix the current path if a blocked edge is found.
    // public void calcPathFix(){}


    // set if a new graph snapshot with fixed edge blockings.
    public GraphSnapshot(Graph graph){
        this.graph = graph;
        setEdgeBlockings(graph.getEdges());
        statesList = new StateList("initial stateList",graph);

    }

    // --- Set blocking state for edges ---

    private void setEdgeBlockings(HashMap<Integer,Edge> edges){
        for(Edge e : edges.values()){
            // todo: change here to conductIsBlocked, and notify the log.
            if(e.getBlockingProb() >= 0.5){
                e.setIsBlocked(true);
            }
        }
    }

}
