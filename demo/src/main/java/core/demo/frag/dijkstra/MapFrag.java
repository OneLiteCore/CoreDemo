package core.demo.frag.dijkstra;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;

import core.demo.R;
import core.demo.frag.dijkstra.data.Location;
import core.demo.frag.dijkstra.data.Map;
import core.mate.app.CoreFrag;
import core.mate.util.ViewUtil;

/**
 * @author DrkCore
 * @since 2017-02-26
 */
public class MapFrag extends CoreFrag implements RadioGroup.OnCheckedChangeListener, NavFrag.OnNavListener {

    private RadioGroup mRadioGroup;
    private RadioButton firstTabBtn;
    private RadioButton secondTabBtn;

    private MapView mMapView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_map, container, false);
        mMapView = (MapView) view.findViewById(R.id.mapView_frag_map);

        mRadioGroup = (RadioGroup) view.findViewById(R.id.radioGroup_frag_map);
        firstTabBtn = (RadioButton) mRadioGroup.findViewById(R.id.radioButton_frag_map_first);
        secondTabBtn = (RadioButton) mRadioGroup.findViewById(R.id.radioButton_frag_map_second);
        mRadioGroup.setOnCheckedChangeListener(this);

        return view;
    }

    @Override
    public void onNav(Location start, Location end) {
        Map startMap = start.getMap();
        Map endMap = end.getMap();
        if (startMap == endMap) {//同层导航
            List<Location> path = startMap.findPath(start, end);
            mMapView.set(startMap, path);
            //隐藏用于选择显示楼层的RadioButton
            //隐藏时会带有从屏幕上方滑动到屏幕外的动画
            ViewUtil.hideWithAnim(mRadioGroup, R.anim.core_slide_top_out);
        } else {//跨层导航
            Location stair = startMap.findNearestStair(start);
            setNavData(firstTabBtn, startMap, startMap.findPath(start, stair));

            int stairIdx = startMap.getIndexOfStair(stair);
            Location targetStair = endMap.getStair(stairIdx);
            setNavData(secondTabBtn, endMap, endMap.findPath(targetStair, end));
            //显示用于选择显示楼层的RadioButton
            //显示时会有从屏幕外滑动到屏幕上方的动画
            ViewUtil.showWithAnim(mRadioGroup, R.anim.core_slide_bottom_in);

            if (firstTabBtn.isChecked()) {
                onCheckedChanged(mRadioGroup, firstTabBtn.getId());
            } else {
                firstTabBtn.setChecked(true);
            }
        }
    }

    private void setNavData(RadioButton btn, Map map, List<Location> nav) {
        btn.setTag(new Pair<>(map, nav));
        btn.setText(map.getName());
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        Pair<Map, List<Location>> data = (Pair<Map, List<Location>>) group.findViewById(checkedId).getTag();
        mMapView.set(data);
    }
}
