package com.wt.train.model;

public class Town {
    private String name;

    public Town(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Town town = (Town) o;

        return name.equals(town.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
