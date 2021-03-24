package org.ctp.mdp;

import org.jgrapht.graph.Edge;

import java.util.Collection;
import java.util.stream.Collectors;

public class GraphUtils {


    public static Collection<Edge> setNextEdges(Collection<Edge> edges){

        edges.stream().map(e-> e.getSource().vertexEdges.add(e)
        ).collect(Collectors.toList());

        return edges;
    }
}
