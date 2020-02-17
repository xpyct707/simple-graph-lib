package net.xpyct707.sgl.path;

import lombok.RequiredArgsConstructor;
import net.xpyct707.sgl.graph.BaseGraph;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static java.lang.String.valueOf;
import static java.util.Collections.emptyList;
import static net.xpyct707.sgl.path.GraphPaths.findDirectedPath;
import static net.xpyct707.sgl.path.GraphPaths.findUndirectedPath;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GraphPathsTest {
    @Test
    void undirectedTest() {
        var graph = new BaseGraph<Vertex>();
        var v0 = new Vertex(0);
        var v1 = new Vertex(1);
        var v2 = new Vertex(2);
        var v3 = new Vertex(3);
        var v4 = new Vertex(4);
        var v5 = new Vertex(5);
        var v6 = new Vertex(6);
        var v7 = new Vertex(7);
        var v8 = new Vertex(8);

        graph.addVertices(Set.of(v0, v1, v2, v3, v4, v5, v6, v7, v8));

        var e01 = graph.addEdge(v0, v1);
        var e12 = graph.addEdge(v1, v2);
        graph.addEdge(v0, v2);
        var e03 = graph.addEdge(v0, v3);
        var e24 = graph.addEdge(v2, v4);
        graph.addEdge(v3, v4);
        var e15 = graph.addEdge(v1, v5);
        graph.addEdge(v5, v6);
        var e57 = graph.addEdge(v5, v7);
        var e28 = graph.addEdge(v2, v8);

        assertEquals(List.of(e01, e12), findUndirectedPath(graph, v0, v2));
        assertEquals(List.of(e01, e12, e24), findUndirectedPath(graph, v0, v4));
        assertEquals(List.of(e03, e01, e12, e24), findUndirectedPath(graph, v3, v4));
        assertEquals(List.of(e28, e12, e15, e57), findUndirectedPath(graph, v8, v7));
    }

    @Test
    void directedTest1() {
        var graph = new BaseGraph<Vertex>();
        var v0 = new Vertex(0);
        var v1 = new Vertex(1);
        var v2 = new Vertex(2);
        var v3 = new Vertex(3);
        var v4 = new Vertex(4);
        var v5 = new Vertex(5);
        var v6 = new Vertex(6);
        var v7 = new Vertex(7);
        var v8 = new Vertex(8);
        var v9 = new Vertex(9);

        graph.addVertices(Set.of(v0, v1, v2, v3, v4, v5, v6, v7, v8, v9));

        var e10 = graph.addEdge(v1, v0);
        var e02 = graph.addEdge(v0, v2);
        graph.addEdge(v2, v1);
        var e31 = graph.addEdge(v3, v1);
        graph.addEdge(v3, v4);
        graph.addEdge(v4, v5);
        graph.addEdge(v6, v4);
        var e28 = graph.addEdge(v2, v8);
        var e29 = graph.addEdge(v2, v9);
        var e87 = graph.addEdge(v8, v7);
        graph.addEdge(v0, v7);

        assertEquals(emptyList(), findDirectedPath(graph, v4, v1));
        assertEquals(List.of(e02), findDirectedPath(graph, v0, v2));
        assertEquals(List.of(e02, e28, e87), findDirectedPath(graph, v0, v7));
        assertEquals(List.of(e31, e10, e02, e29), findDirectedPath(graph, v3, v9));
    }

    @RequiredArgsConstructor
    private static class Vertex {
        private final int id;

        @Override
        public String toString() {
            return valueOf(id);
        }
    }
}