package com.wt.train.model;

import java.io.IOException;
import java.util.List;

import static java.nio.file.Files.readAllLines;
import static java.nio.file.Paths.get;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang.StringUtils.equalsIgnoreCase;

public class Map {
    private List<Edge> edges;
    private String filePath="src/main/resource/input.txt";

    public Map(String filePath) {
        this.filePath = filePath;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public void initMap() throws IOException {
        List<String> texts = readAllLines(get(filePath));
        this.edges = texts.stream().map(line -> convertStringToEdge(line)).collect(toList());
    }

    public int getDisatnce(Town start, Town end) {
        return edges.stream().filter(edge ->
                equalsIgnoreCase(edge.getStartTown().getName(), start.getName())
                        && equalsIgnoreCase(edge.getEndTown().getName(), end.getName()))
                .findFirst()
                .orElse(new Edge(start, end, Integer.MAX_VALUE))
                .getDistance();

    }

    private Edge convertStringToEdge(String line) {
        Town startTown = new Town(line.charAt(0) + "");
        Town endTown = new Town(line.substring(1, 2));
        int distance = Integer.parseInt(line.substring(2));
        return new Edge(startTown, endTown, distance);
    }
}
