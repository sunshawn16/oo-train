package com.wt.train.model;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class Path {
    private List<Town> towns;

    public Path(List<String> towns) {
        this.towns = towns.stream().map(this::covertToTown).collect(toList());
    }

    private Town covertToTown(String town) {
        return new Town(town);
    }

    public List<Town> getTowns() {
        return towns;
    }

    public void setTowns(List<Town> towns) {
        this.towns = towns;
    }

    public void addTownToPath(Town town) {
        towns.add(town);
    }

    public int calculateDistance(Map map, Path path) {
        int totalDistance = 0;
        for (int town = 0; town < path.getTowns().size() - 1; town++) {
            Town start = path.getTowns().get(town);
            Town end = path.getTowns().get(town + 1);
            int disatnce = map.getDisatnce(start, end);
            if (disatnce == Integer.MAX_VALUE) {
                totalDistance = Integer.MAX_VALUE;
            } else totalDistance += disatnce;
        }
        return totalDistance;
    }
}
