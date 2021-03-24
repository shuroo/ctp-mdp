//package org.ctp.mdp;
//
//import org.ctp.CTPGraph.Path;
//import org.jgrapht.graph.Edge;
//import org.jgrapht.graph.Graph;
//import org.jgrapht.graph.Vertex;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.HashMap;
//
//public class MDPImpl {
//
//    public Path getOptimalPath() {
//        return optimalPath;
//    }
//
//    // todo: put in constructor.
//    private Path optimalPath = null;
//
//
//    public MDPImpl(Graph gs) {
//        ArrayList<Path> graphPaths = findAllPossiblePaths(gs);
//        System.out.println(graphPaths.size());
//        System.out.println(graphPaths.get(0));
//        System.out.println(graphPaths.get(1));
//
//        this.optimalPath = findOptimalPath(graphPaths);
//    }
//
//
//    // todo: Bellman-Ford optimal path based .. here.
//    private Path findOptimalPath(ArrayList<Path> paths) {
//
//
//        setPathValues(paths);
//
//        Double cost = 100000000.0;
//        Path minimalPath = null;
//
//        for (Path path : paths) {
//
//            Double currentCost = path.getPathCost();
//            if (currentCost < cost) {
//                cost = currentCost;
//                minimalPath = path;
//            }
//        }
//
//        return minimalPath;
//
//    }
//
//    private void setPathValues(ArrayList<Path> paths) {
//
//        for (Path path : paths) {
//
//            Double pathValue = 0.0;
//
//            // Bellman ford Equation:
//            for (Edge e : path.getPath()) {
//                pathValue += path.getPathCost() + e.getReward() * (1 - e.getBlockingProb());
//                path.setPathCost(pathValue);
//            }
//
//        }
//    }
//
//
//
//
////    private Path buildPath( Vertex graphTail, Vertex currentVertex, Path parentPath) { //Path path, HashMap<Integer, Edge> graphEdges
////        Vertex current = currentVertex;
////        if (current == graphTail) {
////            return parentPath;
////        }
////        if (currentVertex.vertexEdges.iterator().hasNext()) {
////            Edge nextEdge = (Edge) currentVertex.vertexEdges.iterator().next();
////            parentPath.add(nextEdge);
////            current = nextEdge.getDest();
////            return buildPath(graphTail, current, parentPath);
////
////        }
////    }
//
//
////    private ArrayList<Path> buildVertexPaths(Vertex current, Path parentPath){
////        Object [] edges = current.vertexEdges.toArray();
////        ArrayList<Path> r = new ArrayList<Path>();
////        for(int i=0;i< edges.length;i++){
////
////            Edge e = (Edge)edges[i];
////            parentPath.add(e);
////            r.addAll(buildPath( e,
////                    parentPath,new ArrayList<Path>()));
////
////
////        }
////
////        return r;
////    }
//
//    public ArrayList<Path> findAllPossiblePaths(Graph graph) {
//        // starting from s:
//
//        // - Collect all edges where s is their source and add them to a starting path each.
//
//        HashMap<Integer, Edge> edges = graph.getEdges();
//        ArrayList<Path> paths = new ArrayList<Path>();
////        for (Edge edge : edges.values()) {
////
////            Vertex source = edge.getSource();
////            if (source.isInitial()) {
////                Path path = new Path();
////                path.getPath().add(edge);
////                paths.add(path);
////            }
////        }
//
//
//        // collect head and tail..
//        Vertex graphHead = null;
//        Vertex graphTail = null;
//
//        Collection<Edge> graphEdges = edges.values();
//        for (Edge edge : graphEdges) {
//
//            Vertex source = edge.getSource();
//            if (source.isInitial()) {
//                graphHead = source;
//                Path path = new Path();
//                path.getPath().add(edge);
//                paths.add(path);
//            } else if (edge.getDest().isFinal()) {
//                graphTail = edge.getDest();
//            }
//        }
//
//
//        // collect path
//
//        for (Path path : paths) {
//            collectPath(graphHead, graphTail, path);
//        }
//
//
//        return paths;
//
//        // - travel the graph until reaching t, or until done, and collect the path edges.
//    }
//
//
//    private ArrayList<Path> buildPath(Vertex currentVertex, Edge vertexCurrentEdge,
//                                      Path parentPath, ArrayList<Path> res) {
//
//        Vertex current = currentVertex;
//        if (current.isFinal()) {
//            res.add(parentPath);
//            return res;
//        }
//
//        parentPath.add(vertexCurrentEdge);
//        current = vertexCurrentEdge.getDest();
//        Object [] edges = current.vertexEdges.toArray();
//        for(int i=0;i< edges.length;i++){
//            Edge e = (Edge)edges[i];
//            ArrayList<Path> r = buildPath(current, e,
//                    parentPath,new ArrayList<Path>());
//            res.addAll(r);
//        }
//        return res;
//
//
//    }
//
//}
//
////
////
////    private ArrayList<Path> buildPath( Edge vertexCurrentEdge,
////                                      Path parentPath, ArrayList<Path> res) {
////
////        parentPath.add(vertexCurrentEdge);
////        Vertex current = vertexCurrentEdge.getDest();
////
////        Vertex finalV = null;
////        if (current.isFinal()) {
////            finalV = current;
////            res.add(parentPath);
////            return res;
////        }
////
////        Object [] edges = current.vertexEdges.toArray();
////        for(int i=0;i< edges.length;i++){
////
////            Edge e = (Edge)edges[i];
////            if(parentPath.getPath().contains(finalV)){
////                parentPath = new Path();
////            }
////            ArrayList<Path> r = buildPath( e, parentPath,  new ArrayList<Path>());
////            res.addAll(r);
////
////        }
////        return res;
////
////    }
//
////    private Path buildPaths(Path parentPath,
////                                             HashSet<Edge> nextEdges,  ArrayList<Path> res){
////
////
////        if (nextEdges.isEmpty()) {
////            return parentPath;
////        }
////
////
////        Edge currentEdge = nextEdges.iterator().next();
////        nextEdges.remove(currentEdge);
////
////        parentPath.add(currentEdge);
////        Vertex nextV = currentEdge.getDest();
////
////        ArrayList<Path> p2 = buildPaths(parentPath,
////                nextEdges
////                ,new ArrayList<Path>());
////
////        res.addAll(p2);
////
////        if(nextV.isFinal()){
////
////            res.add(parentPath);
////            return res;
////        }
////         ArrayList<Path> p1 = buildPaths(parentPath,
////                 nextV.vertexEdges
////                 ,new ArrayList<Path>());
////
////        res.addAll(p1);
////        return res;
////
////    }
//
////    private ArrayList<Path> buildPaths2(Path prev,Edge next, ArrayList<Path> res){
////        if(next.getDest().isFinal()){
////            prev.add(next);
////            res.add(prev);
////            prev = new Path();
////
////        }
////
////        if(next.getSource().vertexEdges.iterator().hasNext()){
////            Edge e = (Edge)next.getSource().vertexEdges.iterator().next();
////            next.getSource().vertexEdges.remove(e);
////            prev.add(e);
////           return buildPaths2(prev,e,res);
////        }
//////        if(next.getDest().vertexEdges.iterator().hasNext()){
//////            prev.add(next);
//////            Edge e = (Edge)next.getDest().vertexEdges.iterator().next();
//////            next.getSource().vertexEdges.remove(e);
//////            return buildPaths2(prev,e,res);
//////        }
////
////        return res;
////    }
//
//
//
//}

package org.ctp.mdp;

import org.ctp.CTPGraph.Path;
import org.jgrapht.graph.Edge;
import org.jgrapht.graph.Graph;
import org.jgrapht.graph.Vertex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

class PathComperator implements Comparator<Path>{
    // Used for sorting in ascending order of
    // roll number
    public int compare(Path a, Path b)
    {
        return  a.getPathCost() > b.getPathCost()?1:-1;
    }
}

public class MDPImpl {

    Graph graph;

    // Return optimal paths, sort desc (Minimal cost should be in position 0).

    public ArrayList<Path>  getGraphPaths() {
        return graphPaths;
    }

    // todo: put in constructor.


   private ArrayList<Path> graphPaths = null;


    public MDPImpl(Graph gs) {

        this.graph = gs;
        graphPaths = findAllPossiblePaths(gs);
        Collections.sort(graphPaths, new PathComperator());
        graphPaths.stream().map(p-> GraphUtils.setNextEdges(p.getPath())
        ).collect(Collectors.toList());

        System.out.println("*** Graph paths:"+graphPaths+"***");

    }




    private ArrayList<Path> findAllPossiblePaths(Graph graph) {
        // starting from s:

        // - Collect all edges where s is their source and add them to a starting path each.

        Vertex current = graph.getHead();
        Object[] edges = current.vertexEdges.toArray();
        Edge e = (Edge) edges[0];
        Path parentPath = new Path();

        return buildPath(e,
                parentPath, new ArrayList<Path>());

        // - travel the graph until reaching t, or until done, and collect the path edges.
    }

    private ArrayList<Edge> findRelatedDestEdges(ArrayList<Path> reservedPaths, Edge currentEdge){
        Vertex dest = currentEdge.getDest();
        ArrayList<Edge> res = new ArrayList<Edge>();
        for( int i=0;i<reservedPaths.size();i++ ){
            Path currentPath = reservedPaths.get(i);
            for(Edge e: currentPath.getPath()) {
                if (dest == e.getSource()) {
                    res.add(e);
                }
            }
        }

        return res;
    }



    private ArrayList<Path> buildPath( Edge vertexCurrentEdge,
                                      Path parentPath, ArrayList<Path> res) {

        parentPath.add(vertexCurrentEdge);
        Vertex destV = vertexCurrentEdge.getDest();

        // todo: use multiplication
        vertexCurrentEdge.getSource().vertexEdges.remove(vertexCurrentEdge);

        Vertex sourceV = vertexCurrentEdge.getSource();

        if(sourceV.vertexEdges.iterator().hasNext()){
            /// Go to sibling....

            // Start over..

            // System.out.println("---res BEFORE siblings:"+res);

            Path pathCpy = new Path();
            pathCpy.getPath().addAll(parentPath.getPath());
            pathCpy.getPath().remove(vertexCurrentEdge);

            if(!sourceV.vertexEdges.isEmpty()){
                buildPath((Edge) sourceV.vertexEdges.iterator().next(), pathCpy, res);
            }

        }


         if (!destV.isFinal() ) {


//            // go deeper

             try{
                 return buildPath((Edge) destV.vertexEdges.iterator().next(), parentPath, res);
             }
             catch(Exception e){
                 // Add partial path if start is not in the current graph..


                 ArrayList res2 = findRelatedDestEdges(res, vertexCurrentEdge);

                 // todo: while not path is complete \ final -
                 // 
                 //
                 parentPath.getPath().addAll(res2);
                 res.add(parentPath);
                 return res;
             }


        }

        /// End of path - Try again...

        else {

            res.add(parentPath);

        }

        return res;

    }

    public static void main(String [] args){

        Graph graph = new Graph("default_graph_input.json");
        MDPImpl impl = new MDPImpl(graph);
        for(Path p : impl.getGraphPaths()){
            System.out.println("----GraphPath Cost:"+p.getPathCost());
        }

    }

}
