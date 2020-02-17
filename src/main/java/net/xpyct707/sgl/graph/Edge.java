package net.xpyct707.sgl.graph;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static java.lang.String.format;
import static java.util.Objects.nonNull;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class Edge<V> {
    private final V source;
    private final V target;


    public boolean contains(V vertex) {
        return nonNull(vertex) && (vertex.equals(source) || vertex.equals(target));
    }

    public V getOther(V vertex) {
        return source.equals(vertex) ? target : source;
    }

    @Override
    public String toString() {
        return format("%s -> %s",  source, target);
    }
}
