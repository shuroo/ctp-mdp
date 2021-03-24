//package org.jgrapht.graph;
//
//import org.jgrapht.GraphType;
//
//import java.util.Collection;
//import java.util.Set;
//import java.util.function.Supplier;
//
//public interface CTPGraphI<V,E> implements Graph{
//
//        double DEFAULT_EDGE_WEIGHT = 1.0D;
//
//        Set<E> getAllEdges(V var1, V var2);
//
//        E getEdge(V var1, V var2);
//
//        Supplier<V> getVertexSupplier();
//
//        Supplier<E> getEdgeSupplier();
//
//        E addEdge(V var1, V var2);
//
//        boolean addEdge(V var1, V var2, E var3);
//
//        V addVertex();
//
//        boolean addVertex(V var1);
//
//        boolean containsEdge(V var1, V var2);
//
//        boolean containsEdge(E var1);
//
//        boolean containsVertex(V var1);
//
//        Set<E> edgeSet();
//
//        int degreeOf(V var1);
//
//        Set<E> edgesOf(V var1);
//
//        int inDegreeOf(V var1);
//
//        Set<E> incomingEdgesOf(V var1);
//
//        int outDegreeOf(V var1);
//
//        Set<E> outgoingEdgesOf(V var1);
//
//        boolean removeAllEdges(Collection<? extends E> var1);
//
//        Set<E> removeAllEdges(V var1, V var2);
//
//        boolean removeAllVertices(Collection<? extends V> var1);
//
//        E removeEdge(V var1, V var2);
//
//        boolean removeEdge(E var1);
//
//        boolean removeVertex(V var1);
//
//        Set<V> vertexSet();
//
//        V getEdgeSource(E var1);
//
//        V getEdgeTarget(E var1);
//
//        GraphType getType();
//
//        double getEdgeWeight(E var1);
//
//        void setEdgeWeight(E var1, double var2);
//
//        default void setEdgeWeight(V sourceVertex, V targetVertex, double weight) {
//            this.setEdgeWeight(this.getEdge(sourceVertex, targetVertex), weight);
//        }
//
//}
