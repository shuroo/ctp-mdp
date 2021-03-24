package org.ctp.GraphSnapshotRunner;

// todo: rename this class according to the UML.

import org.ctp.CTPAgent.Agent;
import org.ctp.mdp.MDPImpl;
import org.ctp.mdp.StateList;
import org.ctp.snapshot.GraphSnapshot;
import org.jgrapht.graph.Graph;

public class SnapshotRunner implements Runnable{

    Graph g = null;

    public SnapshotRunner(String graphFileName){
         g = new Graph(graphFileName);
    }

    @Override
    public void run() {

        GraphSnapshot gs = new GraphSnapshot(g);
        StateList st = new StateList("Initial stateList",g);
        // todo: calc optimal path;
        MDPImpl model = new MDPImpl(g);

        // todo: run agent runner to add states.
        Agent a = new Agent(model, gs);

        // todo: start from s, get optimal path, try and error. pass the current snapshot?
        a.run();
    }

    public static void main(String[] args){

        /// My Default Graph Example
        // SnapshotRunner sr = new SnapshotRunner("default_graph_input.json");

        /// First Graph Example
        // SnapshotRunner sr = new SnapshotRunner("graphs_data/default_graph_input.json");

        /// Dror's first Graph Example
         SnapshotRunner sr = new SnapshotRunner("graphs_data/dror_data/first_graph.json");

        /// Dror's second Graph Example
        // SnapshotRunner sr = new SnapshotRunner("graphs_data/dror_data/second_graph.json");

        /// Dror's third Graph Example
        // SnapshotRunner sr = new SnapshotRunner("graphs_data/dror_data/third_graph.json");


        sr.run();
    }
}

