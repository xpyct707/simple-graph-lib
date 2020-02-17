package net.xpyct707.sgl.graph;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface Graph<V> {
    void addVertex(V vertex);

    void addVertices(Collection<V> vertices);

    Edge<V> addEdge(V source, V target);

    Set<V> getVertices();

    List<Edge<V>> getEdges();

    boolean contains(V vertex);

    List<Edge<V>> edges(V vertex);

    List<Edge<V>> edgesFrom(V vertex);
}
