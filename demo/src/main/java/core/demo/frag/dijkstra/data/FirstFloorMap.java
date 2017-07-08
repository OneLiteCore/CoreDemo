package core.demo.frag.dijkstra.data;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

import core.demo.R;


/**
 * @author DrkCore
 * @since 2017-03-26
 */
public class FirstFloorMap extends Map {

    FirstFloorMap() {
        List<Location> locations = new ArrayList<>();

        Location 入口交接点 = new Location("入口交接点", new Point(640, 840)).setInvisible();
        locations.add(入口交接点);
        Location 电梯口交接点_1 = new Location("电梯口交接点_1", new Point(748, 1263)).setInvisible();
        locations.add(电梯口交接点_1);
        Location 电梯口交接点_2 = new Location("电梯口交接点_2", new Point(760, 1380)).setInvisible();
        locations.add(电梯口交接点_2);
        Location 右下角楼梯交接点 = new Location("右下角楼梯交接点", new Point(1652, 1374)).setInvisible();
        locations.add(右下角楼梯交接点);
        Location 去北楼的交接点 = new Location("去北楼的交接点", new Point(1776, 1368)).setInvisible();
        locations.add(去北楼的交接点);
        Location 入口1 = new Location("入口1", new Point(420, 840));
        locations.add(入口1);
        Location 入口2 = new Location("入口2", new Point(974, 840));
        locations.add(入口2);
        Location 南楼电梯 = new Location("南楼电梯", new Point(881, 1233));
        locations.add(南楼电梯);
        setStair(南楼电梯, STAIR_SOUTH_ELEVATOR);

        Location 南楼楼梯 = new Location("南楼楼梯", new Point(649, 1266));
        locations.add(南楼楼梯);
        setStair(南楼楼梯, STAIR_SOUTH);
        Location 南楼西侧楼梯 = new Location("南楼西侧楼梯", new Point(155, 1567));
        locations.add(南楼西侧楼梯);
        setStair(南楼西侧楼梯, STAIR_SOUTH_WEST);
        Location 南楼东侧楼梯 = new Location("南楼东侧楼梯", new Point(1654, 1432));
        locations.add(南楼东侧楼梯);
        setStair(南楼东侧楼梯, STAIR_SOUTH_EAST);
        Location 南楼大厅 = new Location("南楼大厅", new Point(758, 1564));
        locations.add(南楼大厅);
        Location 南楼洗手间 = new Location("南楼洗手间", new Point(1044, 1371));
        locations.add(南楼洗手间);

        Location 北楼楼梯 = new Location("北楼楼梯", new Point(644, 426));
        locations.add(北楼楼梯);
        setStair(北楼楼梯, STAIR_NORTH);
        Location 北楼北侧楼梯 = new Location("北楼北侧楼梯", new Point(1757, 249));
        locations.add(北楼北侧楼梯);
        setStair(北楼北侧楼梯, STAIR_NORTH_NORTH);
        Location 去南楼的交接点 = new Location("去南楼的交接点", new Point(1770, 424)).setInvisible();
        locations.add(去南楼的交接点);
        Location 北楼洗手间 = new Location("北楼洗手间", new Point(1939, 382));
        locations.add(北楼洗手间);

        Location 北楼东边楼梯交接点 = new Location("北楼东边楼梯交接点", new Point(2266, 388)).setInvisible();
        locations.add(北楼东边楼梯交接点);
        Location 北楼东侧楼梯 = new Location("北楼东侧楼梯", new Point(2269, 436));
        locations.add(北楼东侧楼梯);
        setStair(北楼东侧楼梯, STAIR_NORTH_EAST);

        Location R101 = new Location("R101", new Point(238, 1566));
        locations.add(R101);
        Location R103 = new Location("R103", new Point(283, 1565));
        locations.add(R103);
        Location R105 = new Location("R105", new Point(412, 1567));
        locations.add(R105);
        Location R107 = new Location("R107", new Point(455, 1566));
        locations.add(R107);
        Location R109 = new Location("R109", new Point(543, 1566));
        locations.add(R109);
        Location R111 = new Location("R111", new Point(601, 1566));
        locations.add(R111);
        Location R113 = new Location("R113", new Point(660, 1564));
        locations.add(R113);
        Location R115 = new Location("R115", new Point(883, 1374));
        locations.add(R115);
        Location R117 = new Location("R117", new Point(1113, 1373));
        locations.add(R117);
        Location R119 = new Location("R119", new Point(1175, 1374));
        locations.add(R119);
        Location R121 = new Location("R121", new Point(1401, 1371));
        locations.add(R121);
        Location R123 = new Location("R123", new Point(1467, 1371));
        locations.add(R123);
        Location R125 = new Location("R125", new Point(1594, 1373));
        locations.add(R125);
        Location R127 = new Location("R127", new Point(1175, 1374));
        locations.add(R127);
        Location R129 = new Location("R129", new Point(1401, 1374));
        locations.add(R129);
        Location R131 = new Location("R131", new Point(1594, 1373));
        locations.add(R131);

        Location R100 = new Location("R100", new Point(693, 289));
        locations.add(R100);
        Location R102 = new Location("R102", new Point(691, 244));
        locations.add(R102);
        Location R120 = new Location("R120", new Point(860, 305));
        locations.add(R120);
        Location R104 = new Location("R104", new Point(906, 305));
        locations.add(R104);
        Location R106 = new Location("R106", new Point(987, 311));
        locations.add(R106);
        Location R108 = new Location("R108", new Point(1203, 310));
        locations.add(R108);
        Location R122 = new Location("R122", new Point(1260, 307));
        locations.add(R122);
        Location R110 = new Location("R110", new Point(1463, 314));
        locations.add(R110);
        Location R124 = new Location("R124", new Point(1591, 307));
        locations.add(R124);
        Location R112 = new Location("R112", new Point(1698, 301));
        locations.add(R112);

        Location R126 = new Location("R126", new Point(1887, 387));
        locations.add(R126);

        Location R128 = new Location("R128", new Point(2215, 384));
        locations.add(R128);
        Location R114 = new Location("R114", new Point(2210, 295));
        locations.add(R114);
        Location R116 = new Location("R116", new Point(2210, 295));
        locations.add(R116);
        Location R118 = new Location("R118", new Point(2210, 295));
        locations.add(R118);

        sortLocationByName(locations);

        EdgeBuilder builder = new EdgeBuilder(locations);

        builder.add(入口1, 入口交接点);
        builder.add(入口2, 入口交接点);
        builder.add(入口交接点, 南楼楼梯);
        builder.add(南楼楼梯, 电梯口交接点_1);
        builder.add(电梯口交接点_1, 南楼电梯);
        builder.add(电梯口交接点_1, 电梯口交接点_2);

        builder.add(电梯口交接点_2, 南楼大厅);
        builder.add(南楼大厅, R113);
        builder.add(R113, R111);
        builder.add(R111, R109);
        builder.add(R109, R107);
        builder.add(R107, R105);
        builder.add(R105, R103);
        builder.add(R103, R101);
        builder.add(R101, 南楼西侧楼梯);

        builder.add(电梯口交接点_2, R115);
        builder.add(R115, 南楼洗手间);
        builder.add(南楼洗手间, R117);
        builder.add(R117, R119);
        builder.add(R119, R127);
        builder.add(R119, R121);
        builder.add(R121, R129);
        builder.add(R121, R123);
        builder.add(R123, R125);
        builder.add(R125, R131);
        builder.add(R125, 右下角楼梯交接点);
        builder.add(右下角楼梯交接点, 南楼东侧楼梯);
        builder.add(右下角楼梯交接点, 去北楼的交接点);

        builder.add(入口交接点, 北楼楼梯);
        builder.add(北楼楼梯, R100);
        builder.add(R100, R102);
        builder.add(R100, R120);
        builder.add(R120, R104);
        builder.add(R104, R106);
        builder.add(R106, R108);
        builder.add(R108, R122);
        builder.add(R122, R110);
        builder.add(R110, R124);
        builder.add(R124, R112);
        builder.add(R112, 北楼北侧楼梯);
        builder.add(R112, 去南楼的交接点);
        builder.add(去南楼的交接点, 去北楼的交接点);
        builder.add(去南楼的交接点, 北楼洗手间);
        builder.add(R112, R126);
        builder.add(R126, 北楼洗手间);
        builder.add(北楼洗手间, R128);
        builder.add(R128, R114);
        builder.add(R128, R116);
        builder.add(R128, R118);
        builder.add(R128, 北楼东边楼梯交接点);
        builder.add(北楼东边楼梯交接点, 北楼东侧楼梯);

        int[][] edges = builder.build();
        set("一层", R.drawable.img_map_1, locations, edges);
    }
}
