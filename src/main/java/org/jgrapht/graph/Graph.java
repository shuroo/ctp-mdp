package org.jgrapht.graph;

import com.google.gson.Gson;
import org.ctp.mdp.GraphUtils;
import org.jgrapht.demo.HelloJGraphT;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Graph<V, E> extends HelloJGraphT {

    public Vertex getHead() {
        return head;
    }

    private Vertex head;

    public Vertex getTail() {
        return tail;
    }

    private Vertex tail;

    private org.jgrapht.Graph<Vertex, Edge> graph = new DefaultDirectedGraph<>(Edge.class);


    public HashMap<String,Vertex> getVertices() {
        return vertices;
    }


    public HashMap<String, Edge> getEdges() {
        return edges;
    }

    private HashMap<String,Vertex> vertices = new  HashMap<String,Vertex>();

    private HashMap<String, Edge> edges = new HashMap<String, Edge>();


    // Init basic graph.
    // todo: replace this with a real graph loading method

    public Graph(Graph<Vertex, Edge> g) {
        this.edges = g.edges;
        this.vertices = g.vertices;
        init();
    }

//    public Graph() {
//       // initSampleGraph();
//    }

    // The REAL constructor
    public Graph(String jsonFileName) {
        readFromJson(jsonFileName);
        init();
    }

    private void init() {
        this.setHeadTail();
        this.setNextEdges();
    }

    private void setHeadTail() {
        for (Vertex v : vertices.values()) {
            if (v.isInitial()) {
                this.head = v;
            } else if (v.isFinal()) {
                this.tail = v;
            }
        }
    }

    private void setNextEdges() {

        // todo: switch the edges back to an array?

        GraphUtils.setNextEdges(this.edges.values());
    }

    // Init on startup ... todo: put this in init method?
//
//    public org.jgrapht.Graph<Vertex, Edge> initSampleGraph() {
//        Vertex v1 = new Vertex("v1", true, false);
//        Vertex v2 = new Vertex("v2", false, false);
//        Vertex v3 = new Vertex("v3", false, false);
//        Vertex v4 = new Vertex("v4", false, true);
//
//
//        // add the vertices
//        graph.addVertex(v1);
//        graph.addVertex(v2);
//        graph.addVertex(v3);
//        graph.addVertex(v4);
//
//        vertices.put(v1.toString(),v1);
//        vertices.put(v2.toString(),v2);
//        vertices.put(v3.toString(),v3);
//        vertices.put(v4.toString(),v4);
//
//        Edge e1 = graph.addEdge(v1, v2);
//        e1.setBlockingAndReward(0.3, 1.0);
//
//        Edge e2 = graph.addEdge(v2, v4);
//
//        e2.setBlockingAndReward(0.2, 10.0);
//
//        Edge e3 = graph.addEdge(v1, v4);
//
//        e3.setBlockingAndReward(0.0, 10000.0);
//
//        Edge e4 = graph.addEdge(v1, v3);
//
//        e4.setBlockingAndReward(0.4, 4.0);
//
//        Edge e5 = graph.addEdge(v2, v3);
//
//        e5.setBlockingAndReward(0.0, 1.0);
//
//        Edge e6 = graph.addEdge(v3, v4);
//
//        e6.setBlockingAndReward(0.4, 2.0);
//
//        this.addEdge(e1);
//        this.addEdge(e2);
//        this.addEdge(e3);
//        this.addEdge(e4);
//        this.addEdge(e5);
//        this.addEdge(e6);
//
//        // Data structure for graph search..
//        init();
//        return graph;
//
//    }

    public Boolean addVertex(Vertex vi) {
        if (graph.addVertex(vi)) {
            vertices.put(vi.toString(),vi);
            return true;
        }
        return false;
    }

    public boolean addEdge(Edge ei) {
        try {
            Vertex sourceV = vertices.get(ei.source.toString());
            Vertex destV = vertices.get(ei.target.toString());
            Edge e = graph.addEdge(sourceV, destV);
            e.setBlockingAndReward(ei.getBlockingProb(), ei.getReward());
            edges.put(e.edgeName(), e);
            this.addEdge(e);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    private org.jgrapht.Graph<Vertex, Edge> loadGraph(HashSet<Vertex> vertices, HashSet<Edge> edges) {

        vertices.stream().map(vi -> addVertex(vi)).collect(Collectors.toList());
        edges.stream().map(ei -> addEdge(ei)).collect(Collectors.toList());
        // Data structure for graph search..
        init();

        return graph;
    }

    private Graph readFromJson(String filename) {
        JSONParser parser = new JSONParser();
        try {

            Object obj = parser.parse(new FileReader(filename));

            // A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
            JSONObject jsonObject = (JSONObject) obj;

            // A JSON array. JSONObject supports java.util.List interface.
            JSONArray vertices_json = (JSONArray) jsonObject.get("vertices");
            JSONArray edges_json = (JSONArray) jsonObject.get("edges");

            Gson g = new Gson();
            HashSet vertices = (HashSet) vertices_json.stream().map(vert ->

                    g.fromJson(vert.toString(), Vertex.class)).collect(Collectors.toSet());


            HashSet edges = (HashSet) edges_json.stream().map(edg ->

                    g.fromJson(edg.toString(), Edge.class)).collect(Collectors.toSet());

            loadGraph(vertices, edges);
        } catch (FileNotFoundException e) {
            System.out.println(e.getStackTrace());
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        } catch (ParseException e) {
            System.out.println(e.getStackTrace());
        }
        return this;
    }
}