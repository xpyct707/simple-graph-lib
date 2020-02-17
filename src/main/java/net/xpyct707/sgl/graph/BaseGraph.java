package net.xpyct707.sgl.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

public class BaseGraph<V> implements Graph<V> {
    private final Set<V> vertices;
    private final List<Edge<V>> edges;


    public BaseGraph() {
        vertices = new HashSet<>();
        edges = new ArrayList<>();
    }

    @Override
    public void addVertex(V vertex) {
        vertices.add(requireNonNull(vertex));
    }

    @Override
    public void addVertices(Collection<V> vertices) {
        requireNonNull(vertices).forEach(this::addVertex);
    }

    @Override
    public Edge<V> addEdge(V source, V target) {
        if (!contains(source) || !contains(target)) {
            throw new IllegalArgumentException("The specified vertex doesn't belong to the graph");
        }
        var edge = new Edge<>(source, target);
        edges.add(edge);
        return edge;
    }

    @Override
    public Set<V> getVertices() {
        return vertices;
    }

    @Override
    public List<Edge<V>> getEdges() {
        return edges;
    }

    @Override
    public boolean contains(V vertex) {
        return vertices.contains(vertex);
    }

    @Override
    public List<Edge<V>> edges(V vertex) {
        return edgeStream()
                .filter(edge -> edge.contains(vertex))
                .collect(toList());
    }

    private Stream<Edge<V>> edgeStream() {
        return edges.stream();
    }

    @Override
    public List<Edge<V>> edgesFrom(V vertex) {
        return edgeStream()
                .filter(edge -> Objects.equals(vertex, edge.getSource()))
                .collect(toList());
    }
}
