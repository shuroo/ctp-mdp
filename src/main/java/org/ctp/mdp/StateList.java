package org.ctp.mdp;

import org.ctp.CTPGraph.Path;
import org.jgrapht.graph.Edge;
import org.jgrapht.graph.Graph;
import org.jgrapht.graph.Vertex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class StateList {

    // <agentPos,E1,E2,...,En>

    private ArrayList<State> stateList = new ArrayList<State>();

    private String statelistLabel = "";


    //todo: switch to using string builder.
    @Override
    public String toString(){
        return "\n"+statelistLabel+" "+stateList.stream().map(state->state.toString()).collect(Collectors.joining("\n"));
    }

    // what's the array size?  -->
    public StateList(String label,Graph g) {

        statelistLabel = label;
        State initial = State.createInitialState(g);
        this.addState(initial);

    }

    public State getInitialState(){

        return this.stateList.get(0);
    }

    public State addStateByPosition(Vertex agentPosition, HashMap<String,Edge> allEdges){
        State s = new State(agentPosition, allEdges);
        s.setStatuses(agentPosition);
        addState(s);
        return s;

    }

    public void addState(State s) {
        stateList.add(s);
    }


}
