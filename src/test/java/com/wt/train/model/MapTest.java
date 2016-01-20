package com.wt.train.model;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MapTest {
    private String filePath = "src/main/resource/input.txt";
    private Map map;

    @Before
    public void setUp() throws Exception {
        map = new Map(filePath);
        map.initMap();
    }

    @Test
    public void shouldFind2PathWhenFromCToCMax3Stops() throws Exception {
        int num = 0;
        for (int i = 2; i <= 3; i++) {
            num += map.findPathInit("C", "C", i).size();
        }
        assertThat(num, is(2));
    }

    @Test
    public void shouldFind3PathWhenFromAToCGiven4Stops() throws Exception {
        assertThat(map.findPathInit("A", "C", 4).size(), is(3));
    }


}