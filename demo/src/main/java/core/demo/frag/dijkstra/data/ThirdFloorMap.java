package core.demo.frag.dijkstra.data;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

import core.demo.R;


/**
 * @author DrkCore
 * @since 2017-03-26
 */
public class ThirdFloorMap extends Map {

    public ThirdFloorMap() {
        List<Location> locations = new ArrayList<>();

        Location 南楼西侧楼梯 = new Location("南楼西侧楼梯", new Point(222, 1871));
        locations.add(南楼西侧楼梯);
        setStair(南楼西侧楼梯, STAIR_SOUTH_WEST);
        Location R301 = new Location("R301", new Point(297, 1871));
        locations.add(R301);
        Location R303 = new Location("R303", new Point(352, 1874));
        locations.add(R303);
        Location R305 = new Location("R305", new Point(518, 1871));
        locations.add(R305);
        Location R307 = new Location("R307", new Point(564, 1871));
        locations.add(R307);
        Location R309 = new Location("R309", new Point(682, 1866));
        locations.add(R309);
        Location R313 = new Location("R313", new Point(751, 1865));
        locations.add(R313);
        Location R311 = new Location("R311", new Point(759, 1867));
        locations.add(R311);
        Location R315 = new Location("R315", new Point(829, 1854));
        locations.add(R315);
        Location 南楼大厅 = new Location("南楼大厅", new Point(896, 1854));
        locations.add(南楼大厅);
        Location 南楼楼梯 = new Location("南楼楼梯", new Point(881, 1476));
        locations.add(南楼楼梯);
        setStair(南楼楼梯, STAIR_SOUTH);
        Location 南楼电梯 = new Location("南楼电梯", new Point(1115, 1443));
        locations.add(南楼电梯);
        setStair(南楼电梯, STAIR_SOUTH_ELEVATOR);
        Location R317 = new Location("R317", new Point(996, 1622));
        locations.add(R317);
        Location 南楼洗手间 = new Location("南楼洗手间", new Point(1298, 1608));
        locations.add(南楼洗手间);
        Location R319 = new Location("R319", new Point(1258, 1608));
        locations.add(R319);
        Location R321 = new Location("R321", new Point(1347, 1611));
        locations.add(R321);
        Location R331 = new Location("R331", new Point(1462, 1598));
        locations.add(R331);
        Location R323 = new Location("R323", new Point(1738, 1590));
        locations.add(R323);
        Location R333 = new Location("R333", new Point(1738, 1590));
        locations.add(R333);
        Location R325 = new Location("R325", new Point(1819, 1598));
        locations.add(R325);
        Location R327 = new Location("R327", new Point(1984, 1590));
        locations.add(R327);
        Location R335 = new Location("R335", new Point(1984, 1590));
        locations.add(R335);
        Location 南楼东侧楼梯 = new Location("南楼东侧楼梯", new Point(2056, 1594));
        locations.add(南楼东侧楼梯);
        setStair(南楼东侧楼梯, STAIR_SOUTH_EAST);
        Location R337 = new Location("R337", new Point(2119, 1589));
        locations.add(R337);
        Location R329 = new Location("R329", new Point(880, 1343));
        locations.add(R329);

        Location R326 = new Location("R326", new Point(1244, 341));
        locations.add(R326);
        Location R300 = new Location("R300", new Point(877, 333));
        locations.add(R300);
        Location 北楼楼梯 = new Location("北楼楼梯", new Point(873, 484));
        locations.add(北楼楼梯);
        setStair(北楼楼梯, STAIR_NORTH);
        Location R302 = new Location("R302", new Point(871, 263));
        locations.add(R302);
        Location R304 = new Location("R304", new Point(1123, 344));
        locations.add(R304);
        Location R328 = new Location("R328", new Point(1309, 344));
        locations.add(R328);
        Location R306 = new Location("R306", new Point(1196, 341));
        locations.add(R306);
        Location R308 = new Location("R308", new Point(1460, 338));
        locations.add(R308);
        Location R330 = new Location("R330", new Point(1545, 349));
        locations.add(R330);
        Location R310 = new Location("R310", new Point(1698, 341));
        locations.add(R310);
        Location R312 = new Location("R312", new Point(1787, 340));
        locations.add(R312);
        Location R332 = new Location("R332", new Point(1698, 341));
        locations.add(R332);
        Location R334 = new Location("R334", new Point(1787, 340));
        locations.add(R334);
        Location R314 = new Location("R314", new Point(2042, 251));
        locations.add(R314);
        Location R316 = new Location("R316", new Point(2652, 319));
        locations.add(R316);
        Location R336 = new Location("R336", new Point(1930, 348));
        locations.add(R336);
        Location R318 = new Location("R318", new Point(2652, 319));
        locations.add(R318);
        Location 北楼北侧楼梯 = new Location("北楼北侧楼梯", new Point(2124, 360));
        locations.add(北楼北侧楼梯);
        setStair(北楼北侧楼梯, STAIR_NORTH_NORTH);
        Location R320 = new Location("R320", new Point(2652, 319));
        locations.add(R320);
        Location R322 = new Location("R322", new Point(877, 605));
        locations.add(R322);
        Location R324 = new Location("R324", new Point(1085, 344));
        locations.add(R324);
        Location R338 = new Location("R338", new Point(2257, 405));
        locations.add(R338);
        Location 北楼洗手间 = new Location("北楼洗手间", new Point(2330, 402));
        locations.add(北楼洗手间);
        Location R340 = new Location("R340", new Point(2648, 400));
        locations.add(R340);
        Location 北楼东侧楼梯 = new Location("北楼东侧楼梯", new Point(2719, 400));
        locations.add(北楼东侧楼梯);
        setStair(北楼东侧楼梯, STAIR_NORTH_EAST);

        sortLocationByName(locations);

        EdgeBuilder builder = new EdgeBuilder(locations);
        builder.add(南楼西侧楼梯, R301);
        builder.add(R301, R303);
        builder.add(R303, R305);
        builder.add(R305, R307);
        builder.add(R307, R309);
        builder.add(R309, R313);
        builder.add(R313, R311);
        builder.add(R311, R315);
        builder.add(R315, 南楼大厅);

        builder.add(南楼大厅, 南楼楼梯);
        builder.add(南楼大厅, R317);
        builder.add(南楼楼梯, 南楼电梯);
        builder.add(南楼电梯, R317);
        builder.add(R317, 南楼洗手间);
        builder.add(南楼洗手间, R319);
        builder.add(R319, R321);
        builder.add(R321, R331);
        builder.add(R321, R323);
        builder.add(R323, R333);
        builder.add(R323, R325);
        builder.add(R325, R327);
        builder.add(R327, R335);
        builder.add(R327, 南楼东侧楼梯);
        builder.add(南楼东侧楼梯, R337);

        builder.add(南楼楼梯, R329);
        builder.add(R329, R322);
        builder.add(R322, 北楼楼梯);
        builder.add(北楼楼梯, R300);
        builder.add(R300, R302);
        builder.add(R300, R324);
        builder.add(R324, R304);
        builder.add(R304, R306);
        builder.add(R306, R326);
        builder.add(R326, R328);
        builder.add(R328, R308);
        builder.add(R308, R330);
        builder.add(R330, R310);
        builder.add(R330, R332);
        builder.add(R332, R334);
        builder.add(R334, R312);
        builder.add(R334, R336);
        builder.add(R336, R314);
        builder.add(R314, 北楼北侧楼梯);
        builder.add(北楼北侧楼梯, R338);
        builder.add(R338, 北楼洗手间);
        builder.add(北楼洗手间, R340);
        builder.add(R340, R316);
        builder.add(R340, R318);
        builder.add(R340, R320);
        builder.add(R340, 北楼东侧楼梯);

        int[][] edges = builder.build();
        set("三层", R.drawable.img_map_3, locations, edges);
    }
}
