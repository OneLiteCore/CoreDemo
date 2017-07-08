package core.demo.frag.dijkstra.data;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

import core.demo.R;

/*
 * @author DrkCore
 * @since 2017-03-26
 */
public class SecondFloorMap extends Map {

    public SecondFloorMap() {
        List<Location> locations = new ArrayList<>();

        Location 南楼西侧楼梯 = new Location("南楼西侧楼梯", new Point(259, 1803));
        locations.add(南楼西侧楼梯);
        setStair(南楼西侧楼梯, STAIR_SOUTH_WEST);
        Location R201 = new Location("R201", new Point(302, 1804));
        locations.add(R201);
        Location R203 = new Location("R203", new Point(398, 1804));
        locations.add(R203);
        Location R205 = new Location("R205", new Point(508, 1804));
        locations.add(R205);
        Location R207 = new Location("R207", new Point(562, 1804));
        locations.add(R207);
        Location R209 = new Location("R209", new Point(661, 1804));
        locations.add(R209);
        Location R213 = new Location("R213", new Point(724, 1804));
        locations.add(R213);
        Location R211 = new Location("R211", new Point(759, 1804));
        locations.add(R211);
        Location R215 = new Location("R215", new Point(809, 1804));
        locations.add(R215);
        Location 院团委 = new Location("院团委", new Point(846, 1571));
        locations.add(院团委);
        Location 南楼大厅 = new Location("南楼大厅", new Point(867, 1804));
        locations.add(南楼大厅);
        Location 南楼楼梯 = new Location("南楼楼梯", new Point(851, 1453));
        locations.add(南楼楼梯);
        setStair(南楼楼梯, STAIR_SOUTH);
        Location 南楼电梯 = new Location("南楼电梯", new Point(1069, 1411));
        locations.add(南楼电梯);
        setStair(南楼电梯, STAIR_SOUTH_ELEVATOR);
        Location R217 = new Location("R217", new Point(1053, 1589));
        locations.add(R217);
        Location 南楼洗手间 = new Location("南楼洗手间", new Point(1244, 1575));
        locations.add(南楼洗手间);
        Location R219 = new Location("R219", new Point(1323, 1583));
        locations.add(R219);
        Location R221 = new Location("R221", new Point(1399, 1580));
        locations.add(R221);
        Location R231 = new Location("R231", new Point(1399, 1580));
        locations.add(R231);
        Location R223 = new Location("R223", new Point(1659, 1577));
        locations.add(R223);
        Location R233 = new Location("R233", new Point(1659, 1577));
        locations.add(R233);
        Location R225 = new Location("R225", new Point(1733, 1577));
        locations.add(R225);
        Location R227 = new Location("R227", new Point(1890, 1577));
        locations.add(R227);
        Location HelloWorld社团 = new Location("HelloWorld社团", new Point(1890, 1577));
        locations.add(HelloWorld社团);
        Location R235 = new Location("R235", new Point(1890, 1577));
        locations.add(R235);
        Location 南楼东侧楼梯 = new Location("南楼东侧楼梯", new Point(1948, 1580));
        locations.add(南楼东侧楼梯);
        setStair(南楼东侧楼梯, STAIR_SOUTH_EAST);
        Location R237 = new Location("R237", new Point(2015, 1580));
        locations.add(R237);
        Location R229 = new Location("R229", new Point(840, 1328));
        locations.add(R229);
        Location R226 = new Location("R226", new Point(852, 617));
        locations.add(R226);
        Location R200 = new Location("R200", new Point(842, 335));
        locations.add(R200);
        Location 北楼楼梯 = new Location("北楼楼梯", new Point(848, 485));
        locations.add(北楼楼梯);
        setStair(北楼楼梯, STAIR_NORTH);
        Location R202 = new Location("R202", new Point(842, 271));
        locations.add(R202);
        Location R204 = new Location("R204", new Point(955, 348));
        locations.add(R204);
        Location R228 = new Location("R228", new Point(1060, 354));
        locations.add(R228);
        Location R206 = new Location("R206", new Point(1099, 354));
        locations.add(R206);
        Location R208 = new Location("R208", new Point(1175, 356));
        locations.add(R208);
        Location R230 = new Location("R230", new Point(1280, 367));
        locations.add(R230);
        Location R210 = new Location("R210", new Point(1450, 367));
        locations.add(R210);
        Location R212 = new Location("R212", new Point(1501, 367));
        locations.add(R212);
        Location R232 = new Location("R232", new Point(1546, 367));
        locations.add(R232);
        Location R234 = new Location("R234", new Point(1615, 367));
        locations.add(R234);
        Location R214 = new Location("R214", new Point(1653, 367));
        locations.add(R214);
        Location R216 = new Location("R216", new Point(1725, 367));
        locations.add(R216);
        Location R236 = new Location("R236", new Point(1882, 367));
        locations.add(R236);
        Location R218 = new Location("R218", new Point(1990, 367));
        locations.add(R218);
        Location 北楼北侧楼梯 = new Location("北楼北侧楼梯", new Point(2051, 367));
        locations.add(北楼北侧楼梯);
        setStair(北楼北侧楼梯, STAIR_NORTH_NORTH);
        Location R220 = new Location("R220", new Point(2570, 367));
        locations.add(R220);
        Location R222 = new Location("R222", new Point(2570, 367));
        locations.add(R222);
        Location R224 = new Location("R224", new Point(2570, 367));
        locations.add(R224);
        Location R238 = new Location("R238", new Point(2191, 432));
        locations.add(R238);
        Location 北楼洗手间 = new Location("北楼洗手间", new Point(2268, 432));
        locations.add(北楼洗手间);
        Location R240 = new Location("R240", new Point(2569, 432));
        locations.add(R240);
        Location 北楼东侧楼梯 = new Location("北楼东侧楼梯", new Point(2634, 432));
        locations.add(北楼东侧楼梯);
        setStair(北楼东侧楼梯, STAIR_NORTH_EAST);

        sortLocationByName(locations);

        EdgeBuilder builder = new EdgeBuilder(locations);
        builder.add(南楼西侧楼梯, R201);
        builder.add(R201, R203);
        builder.add(R203, R205);
        builder.add(R205, R207);
        builder.add(R207, R209);
        builder.add(R209, R213);
        builder.add(R213, R211);
        builder.add(R211, R215);
        builder.add(R215, 南楼大厅);

        builder.add(南楼大厅, 院团委);
        builder.add(院团委, 南楼楼梯);
        builder.add(院团委, 南楼电梯);
        builder.add(院团委, R217);
        builder.add(院团委, 南楼电梯);
        builder.add(南楼楼梯, 南楼电梯);
        builder.add(南楼楼梯, R217);
        builder.add(南楼电梯, R217);
        builder.add(R217, 南楼洗手间);
        builder.add(南楼洗手间, R219);
        builder.add(R219, R221);
        builder.add(R221, R231);
        builder.add(R221, R223);
        builder.add(R223, R233);
        builder.add(R223, R225);
        builder.add(R225, R227);
        builder.add(R227, HelloWorld社团);
        builder.add(R227, R235);
        builder.add(R227, 南楼东侧楼梯);
        builder.add(南楼东侧楼梯, R237);

        builder.add(南楼楼梯, R229);
        builder.add(R229, R226);
        builder.add(R226, 北楼楼梯);
        builder.add(北楼楼梯, R200);
        builder.add(R200, R202);
        builder.add(R200, R204);
        builder.add(R204, R228);
        builder.add(R228, R206);
        builder.add(R206, R208);
        builder.add(R208, R230);
        builder.add(R230, R210);
        builder.add(R210, R212);
        builder.add(R212, R232);
        builder.add(R232, R234);
        builder.add(R234, R214);
        builder.add(R214, R216);
        builder.add(R216, R236);
        builder.add(R236, R218);
        builder.add(R218, 北楼北侧楼梯);
        builder.add(北楼北侧楼梯, R238);
        builder.add(R238, 北楼洗手间);
        builder.add(北楼洗手间, R240);
        builder.add(R240, R220);
        builder.add(R240, R222);
        builder.add(R240, R224);
        builder.add(R240, 北楼东侧楼梯);

        int[][] edges = builder.build();
        set("二层", R.drawable.img_map_2, locations, edges);
    }
}
