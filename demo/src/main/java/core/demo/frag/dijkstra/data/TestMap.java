package core.demo.frag.dijkstra.data;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

import core.demo.R;

/**
 * @author DrkCore
 * @since 2017-03-26
 */
public class TestMap extends Map {

    public TestMap() {
        List<Location> locations = new ArrayList<>();

        Location A = new Location("A", new Point(33, 107));
        locations.add(A);
        Location B = new Location("B", new Point(122, 24));
        locations.add(B);
        Location C = new Location("C", new Point(145, 177));
        locations.add(C);
        Location D = new Location("D", new Point(204, 78));
        locations.add(D);
        Location E = new Location("E", new Point(275, 172));
        locations.add(E);
        Location F = new Location("F", new Point(311, 53));
        locations.add(F);

        EdgeBuilder builder = new EdgeBuilder(locations);
        builder.add(A, B);
        builder.add(A, C);
        builder.add(B, D);
        builder.add(B, C);
        builder.add(C, D);
        builder.add(C, E);
        builder.add(D, F);
        builder.add(D, E);
        builder.add(E, F);

        set("TEST", R.drawable.img_map_test,
                locations, builder.build());
    }
}
