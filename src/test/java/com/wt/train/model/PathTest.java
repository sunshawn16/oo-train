package com.wt.train.model;

import org.junit.Before;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PathTest {
    private String filePath = "src/main/resource/input.txt";
    private Map map;

    @Before
    public void setUp() throws Exception {
        map = new Map(filePath);
        map.initMap();

    }

    @Test
    public void shouldGet9WhenCalculateFromAtoBToC() throws Exception {
        Path path = new Path(asList("A", "B", "C"));
        assertThat(path.calculateDistance(map, path), is(9));
    }

    @Test
    public void shouldGet5WhenCalculateFromAtoD() throws Exception {
        Path path = new Path(asList("A", "D"));

        assertThat(path.calculateDistance(map, path), is(5));
    }

    @Test
    public void shouldGet13WhenCalculateFromAtoDToC() throws Exception {
        Path path = new Path(asList("A", "D", "C"));
        assertThat(path.calculateDistance(map, path), is(13));
    }

    @Test
    public void shouldGet22WhenCalculateFromAtoEToBToCToD() throws Exception {
        Path path = new Path(asList("A", "E", "B", "C", "D"));
        assertThat(path.calculateDistance(map, path), is(22));
    }

    @Test
    public void shouldGetInfinWhenCalculateFromAtoEToD() throws Exception {
        Path path = new Path(asList("A", "E", "D"));
        assertThat(path.calculateDistance(map, path), is(Integer.MAX_VALUE));
    }


}