package org.ctp.mdp;

import org.jgrapht.graph.Edge;
import org.jgrapht.graph.Vertex;

public class Action {

    // move(e) = vi

    public static Vertex move(Vertex source, Edge e) throws Exception {
        if(source != e.getSource()){ throw new Exception("Invalid parameters");}

        return e.getDest();
    }
}
