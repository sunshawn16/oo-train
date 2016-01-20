package com.wt.train.model;

import com.wt.train.utils.Converter;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Path {
    private List<Town> towns;

    public Path() {
        this.towns = new ArrayList<>();
    }

    public Path(List<String> towns) {
        this.towns = towns.stream().map(Converter::convertToTown).collect(toList());
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
        for (int town = 0; town < path.count() - 1; town++) {
            Town start = path.getTowns().get(town);
            Town end = path.getTowns().get(town + 1);
            int disatnce = map.getDisatnce(start, end);
            if (disatnce == Integer.MAX_VALUE) {
                totalDistance = Integer.MAX_VALUE;
            } else totalDistance += disatnce;
        }
        return totalDistance;
    }

    public int count() {
        return this.getTowns().size();
    }
}
