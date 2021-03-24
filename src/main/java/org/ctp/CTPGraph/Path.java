package org.ctp.CTPGraph;


import org.ctp.mdp.GraphUtils;
import org.jgrapht.graph.Edge;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Path {


    private ArrayList<Edge> path;

    private Double pathCost = null;

    public Double getPathCost() {
        return pathCost;
    }

    //todo: switch to hashset
    public ArrayList<Edge> getPath() {
        return path;
    }

    public Path() {
        this.path = new ArrayList<Edge>();
        this.pathCost = 0.0;
    }

    public Path(ArrayList<Edge> edges){
        this.path = edges;
        pathCost = this.path.stream().map(a ->a.getReward())
                .reduce((a,b)->a+b).get();
    }

    @Override
    public String toString(){
      return "PathCost:"+pathCost+":: Path:"+this.getPath().toString()+"\n";
    }

    private void addToCost(Edge e){
        pathCost+=e.getReward();
    }
    public void add(Edge e){
        this.path.add(e);
        addToCost(e);
    }



}
