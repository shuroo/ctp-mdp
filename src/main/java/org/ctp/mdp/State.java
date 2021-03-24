package org.ctp.mdp;

import org.ctp.CTPGraph.EdgeStatus;
import org.jgrapht.graph.Edge;
import org.jgrapht.graph.Graph;
import org.jgrapht.graph.Vertex;

import java.util.HashMap;
import java.util.Iterator;
import java.util.stream.Collectors;


public class State {

    public Vertex getAgentPos() {
        return agentPos;
    }

    public void setAgentPos(Vertex agentPos) {
        this.agentPos = agentPos;
    }

    private Vertex agentPos;
    private HashMap<String, EdgeStatus> edgesStatuses = new HashMap<String, EdgeStatus>();

    // todo: pass the graph!
    public State(Vertex pos, HashMap<String, Edge> edges) {

        this.agentPos = pos;
        initStatuses(edges);
        setStatuses(pos);

    }

    public String toString(){
        // fill map here
        String key_value_tuples = this.edgesStatuses.entrySet().stream()//;.reduce("", (a, b) -> ""+a+""+b);
                .map(x -> x.getKey() + ": " + x.getValue()).collect(Collectors.joining(","));
        return "("+this.agentPos+","+key_value_tuples+")";
    }

    public static  State createInitialState(Graph g) {

        HashMap<String, Edge> edges = g.getEdges();

        Vertex s = g.getHead();

        return new State(s, edges);
    }

    public void setStatuses(Vertex agentPosition) {

        // Creating an iterator


        Iterator<Edge> iter = agentPosition.vertexEdges.iterator();
        while (iter.hasNext()) {
            Edge e = iter.next();
            EdgeStatus edgeStatus = e.isBlocked() ? EdgeStatus.CLOSED : EdgeStatus.OPEN;
            edgesStatuses.put(e.edgeName(), edgeStatus);
        }
        // todo: stream it
    }

    private void initStatuses(HashMap<String, Edge> edges){
        // Init edgesStatuses to unknown..
        for (Edge e : edges.values()) {
            edgesStatuses.put(e.edgeName(), EdgeStatus.UNKNOWN);
        }
    }

    public void setStateHistory(State oldState){
        HashMap<String,EdgeStatus> newStatuses = this.edgesStatuses;
        HashMap<String,EdgeStatus> oldStatuses = oldState.edgesStatuses;

        newStatuses.entrySet().stream().map(e-> {
            EdgeStatus oldStateStatus = oldStatuses.get(e.getKey());
            if(e.getValue() == EdgeStatus.UNKNOWN) {
                e.setValue(oldStateStatus);
            }
            return e;
        }).collect(Collectors.toList());

    }

}
