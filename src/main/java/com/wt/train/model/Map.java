package com.wt.train.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.wt.train.utils.Converter.convertToTown;
import static java.nio.file.Files.readAllLines;
import static java.nio.file.Paths.get;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang.StringUtils.equalsIgnoreCase;

public class Map {
    private List<Edge> edges;
    private String filePath = "src/main/resource/input.txt";

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

    public List<Path> findPathInit(String start, String end, int numStop) {
        Town startTown = convertToTown(start);
        Town endTown = convertToTown(end);
        List<Path> finalPaths = new ArrayList<>();
        List<Path> candidatePaths = new ArrayList<>();

        List<Path> pathsFromStart = addEdgesToInitPath(findEdgesWithStart(startTown));
        candidatePaths.addAll(pathsFromStart);

//        pathsFromStart.stream().map(path->findPath(candidatePaths, finalPaths, path, endTown, 0, numStop)).collect(toList());
        for (Path path : pathsFromStart) {
            findPath(candidatePaths, finalPaths, path, endTown, 0, numStop);
        }
        return finalPaths;
    }

    private void findPath(List<Path> candidatePaths, List<Path> finalPaths, Path currentPath, Town endTown, int currentCount, double numStop) {
        Town currentEndTown = currentPath.getTowns().get(currentPath.getTowns().size() - 1);
        if (currentCount > numStop) {
            return;
        }
        if (currentCount == numStop - 1 && currentEndTown.equals(endTown)) {
            finalPaths.add(currentPath);
            return;
        }
        List<Path> newPaths = addEdgesToCurrentPath(findEdgesWithStart(currentEndTown), currentPath);
        candidatePaths.addAll(newPaths);
        for (Path path : newPaths) {
            findPath(candidatePaths, finalPaths, path, endTown, currentCount + 1, numStop);
        }
    }

    private List<Path> addEdgesToCurrentPath(List<Edge> edges, Path currentPath) {
        List<String> towns = currentPath.getTowns().stream().
                map(town -> town.getName()).collect(toList());
        return edges.stream().map(edge -> {
            Path path = new Path(towns);
            path.addTownToPath(edge.getEndTown());
            return path;
        }).collect(toList());
    }

    private List<Path> addEdgesToInitPath(List<Edge> edges) {
        return edges.stream().map(edge -> {
            Path newPath = new Path();
            newPath.addTownToPath(edge.getStartTown());
            newPath.addTownToPath(edge.getEndTown());
            return newPath;
        }).collect(toList());
    }

    private List<Edge> findEdgesWithStart(Town startTown) {
        return edges.stream().filter(edge -> edge.getStartTown().equals(startTown)).collect(toList());
    }

}
