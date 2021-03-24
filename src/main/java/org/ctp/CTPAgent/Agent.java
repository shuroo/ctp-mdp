package org.ctp.CTPAgent;

import org.ctp.CTPGraph.Path;
import org.ctp.mdp.MDPImpl;
import org.ctp.mdp.State;
import org.ctp.mdp.StateList;
import org.ctp.snapshot.GraphSnapshot;
import org.jgrapht.graph.Edge;
import org.jgrapht.graph.Vertex;

import java.util.ArrayList;

public class Agent implements Runnable {

    private GraphSnapshot gs;

    private MDPImpl model;

    public Agent(MDPImpl model, GraphSnapshot gs) {

        this.gs = gs;
        this.model = model;

    }

    public void run() {
        //Graph g = gs.getGraph();
        State initial = gs.statesList.getInitialState();

        /// todo: put the state list as a path property?
        ArrayList<StateList> agentPath = travelGraph( initial,0, model.getGraphPaths(),0,new ArrayList<StateList>(),
                new StateList("First Attempt for stateList",gs.getGraph()));
        // todo: build function out of this:
        System.out.println("|| The agent total path was:"+agentPath+" ||");
        System.out.println("|| Possible paths and costs are:"+model.getGraphPaths() + " ||");
    }

    // Travel one step in the graph each time. try best cost. if fail, return.
    //todo: reorder params.
    public ArrayList<StateList> travelGraph( State current, Integer edgeIndex, ArrayList<Path> bestPaths,
                            int pathIndex ,ArrayList<StateList> statelstsHistory, StateList currentSL) {


        if(pathIndex >= bestPaths.size()){
            statelstsHistory.add(currentSL);
            return statelstsHistory;
        }
        Vertex currentPosition = current.getAgentPos();
        Path bestPath = bestPaths.get(pathIndex);

            if (currentPosition.isFinal()) {

                //ArrayList<StateList> currentState = new ArrayList<StateList>();
                statelstsHistory.add(currentSL);
                return statelstsHistory;
                // If not blocked - proceed path ...
            }

        ArrayList<Edge> bstPath = bestPath.getPath();
        Edge edge = bstPath.get(edgeIndex);

        if ( current.getAgentPos() == edge.getSource() && !edge.isBlocked()) {
                currentPosition = edge.getDest();
                // Travel best path..
                State updatedState = gs.statesList.addStateByPosition( currentPosition,
                        gs.getGraph().getEdges());

                //todo: move to init state?
             updatedState.setStateHistory(current);
                currentSL.addState(updatedState);
                return travelGraph(updatedState,edgeIndex+1, bestPaths,
                        pathIndex,statelstsHistory,currentSL);
            } else if (current.getAgentPos() == edge.getSource()) {
                //  find an alternative path as the current is blocked..
                // currentPosition = prev.getAgentPos();
                // todo:  find an alternative path

                String label = "\nReconstructing path for agent after edge:"+edge+" was found blocked.\n";
                statelstsHistory.add(currentSL);
                travelGraph(this.gs.statesList.getInitialState(),0, bestPaths,
                        pathIndex+1,statelstsHistory,new StateList(label,gs.getGraph()));


                // todo: - print stateList
                // - Handle regression


        }

        return statelstsHistory;
    }


}
