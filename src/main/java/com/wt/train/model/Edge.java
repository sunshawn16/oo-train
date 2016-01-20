package com.wt.train.model;

public class Edge {
    private Town startTown;
    private Town endTown;
    private int distance;

    public Edge(Town startTown, Town endTown, int distance) {
        this.startTown = startTown;
        this.endTown = endTown;
        this.distance = distance;
    }

    public Town getStartTown() {
        return startTown;
    }

    public void setStartTown(Town startTown) {
        this.startTown = startTown;
    }

    public Town getEndTown() {
        return endTown;
    }

    public void setEndTown(Town endTown) {
        this.endTown = endTown;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
