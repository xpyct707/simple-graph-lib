package net.xpyct707.sgl.path;

import lombok.RequiredArgsConstructor;
import net.xpyct707.sgl.graph.Edge;
import net.xpyct707.sgl.graph.Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Collections.emptyList;

public class GraphPaths {
    public static <V> List<Edge<V>> findUndirectedPath(Graph<V> graph, V start, V end) {
        if (isInvalidInput(graph, start, end)) {
            return emptyList();
        }

        return new UndirectedSearcher<V>(graph, end).search(start, new HashSet<>(), new ArrayList<>());
    }

    private static <V> boolean isInvalidInput(Graph<V> graph, V start, V end) {
        return !graph.contains(start) || !graph.contains(end);
    }

    public static <V> List<Edge<V>> findDirectedPath(Graph<V> graph, V start, V end) {
        if (isInvalidInput(graph, start, end)) {
            return emptyList();
        }

        return new DirectedSearcher<V>(graph, end).search(start, new HashSet<>(), new ArrayList<>());
    }


    @RequiredArgsConstructor
    private static class UndirectedSearcher<V> {
        private final Graph<V> graph;
        private final V end;


        List<Edge<V>> search(V start, Set<V> visited, List<Edge<V>> path) {
            visited.add(start);

            for (var edge : graph.edges(start)) {
                if (!path.contains(edge)) {
                    var newPath = new ArrayList<>(path);
                    newPath.add(edge);
                    var other = edge.getOther(start);
                    if (end.equals(other)) {
                        return newPath;
                    }
                    if (!visited.contains(other)) {
                        var result = search(other, visited, newPath);
                        if (!result.isEmpty()) {
                            return result;
                        }
                    }
                }
            }
            return emptyList();
        }
    }


    @RequiredArgsConstructor
    private static class DirectedSearcher<V> {
        private final Graph<V> graph;
        private final V end;


        List<Edge<V>> search(V start, Set<V> visited, List<Edge<V>> path) {
            visited.add(start);

            for (var edge : graph.edgesFrom(start)) {
                if (!path.contains(edge)) {
                    var newPath = new ArrayList<>(path);
                    newPath.add(edge);
                    var other = edge.getOther(start);
                    if (end.equals(other)) {
                        return newPath;
                    }
                    if (!visited.contains(other)) {
                        var result = search(other, visited, newPath);
                        if (!result.isEmpty()) {
                            return result;
                        }
                    }
                }
            }
            return emptyList();
        }
    }
}
