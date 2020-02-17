package net.xpyct707.sgl.path;

import lombok.RequiredArgsConstructor;
import net.xpyct707.sgl.graph.Edge;
import net.xpyct707.sgl.graph.Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;

import static java.util.Collections.emptyList;

public class GraphPaths {
    public static <V> List<Edge<V>> findUndirectedPath(Graph<V> graph, V start, V end) {
        return findPath(graph, Graph::edges, start, end);
    }

    private static <V> List<Edge<V>> findPath(Graph<V> graph,
                                              BiFunction<Graph<V>, V, List<Edge<V>>> edgesGetter,
                                              V start,
                                              V end) {
        if (isInvalidInput(graph, start, end)) {
            throw new IllegalArgumentException("Both vertices must belong to the specified graph");
        }

        return new Searcher<V>(graph, edgesGetter, end).search(start, new HashSet<>(), new ArrayList<>());
    }

    private static <V> boolean isInvalidInput(Graph<V> graph, V start, V end) {
        return !graph.contains(start) || !graph.contains(end);
    }

    public static <V> List<Edge<V>> findDirectedPath(Graph<V> graph, V start, V end) {
        return findPath(graph, Graph::edgesFrom, start, end);
    }


    @RequiredArgsConstructor
    private static class Searcher<V> {
        private final Graph<V> graph;
        private final BiFunction<Graph<V>, V, List<Edge<V>>> edgesGetter;
        private final V end;


        List<Edge<V>> search(V start, Set<V> visited, List<Edge<V>> pathToStart) {
            visited.add(start);

            for (var edge : edgesGetter.apply(graph, start)) {
                if (pathToStart.contains(edge)) {
                    continue;
                }

                var pathToNext = new ArrayList<>(pathToStart);
                pathToNext.add(edge);

                var next = edge.getOtherSide(start);
                if (visited.contains(next)) {
                    continue;
                }

                if (reachedDestination(next)) {
                    return pathToNext;
                }

                var result = search(next, visited, pathToNext);
                if (!result.isEmpty()) {
                    return result;
                }
            }
            return emptyList();
        }

        private boolean reachedDestination(V other) {
            return end.equals(other);
        }
    }
}
