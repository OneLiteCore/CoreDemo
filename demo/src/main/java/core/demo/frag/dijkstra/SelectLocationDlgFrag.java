package core.demo.frag.dijkstra;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

import core.demo.R;
import core.demo.frag.dijkstra.data.Location;
import core.demo.frag.dijkstra.data.Map;
import core.demo.frag.dijkstra.data.MapFactory;
import core.mate.adapter.SimpleAdapter;
import core.mate.adapter.SimpleViewHolder;
import core.mate.app.CoreDlgFrag;
import core.mate.util.Callback;
import core.mate.util.ContextUtil;
import core.mate.util.SimpleCallback;

/**
 * @author DrkCore
 * @since 2017-03-25
 */
public class SelectLocationDlgFrag extends CoreDlgFrag implements RadioGroup.OnCheckedChangeListener,
        AdapterView.OnItemClickListener {

    private ListView listView;
    private ItemAdapter adapter;

    private Location mSelectedLocation;

    public SelectLocationDlgFrag setSelectedLocation(Location selectedLocation) {
        mSelectedLocation = selectedLocation;
        return this;
    }

    private static class ItemAdapter extends SimpleAdapter<Location> {

        public ItemAdapter() {
            super(android.R.layout.simple_list_item_checked);
        }

        @Override
        protected void onViewHolderCreated(SimpleViewHolder holder, int viewType) {
            super.onViewHolderCreated(holder, viewType);
            TextView textView = holder.getCastView();
            textView.setTextColor(Color.WHITE);
        }

        @Override
        protected void bindViewData(SimpleViewHolder holder, int position, Location data, int viewType) {
            TextView textView = holder.getCastView();
            textView.setText(data.getName());
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, 0);
        setWidth(getResources().getDimensionPixelSize(R.dimen.width_frag_nav));
        setHeight(WindowManager.LayoutParams.MATCH_PARENT);
        setWinAnimStyle(core.mate.R.style.CoreWindowAnimSlideRightStyle);
        setGravity(Gravity.START);
        setDimAmount(0.64F);
        setWinBgColor(ContextUtil.getColor(R.color.soft_dark));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //初始化界面控件
        View view = inflater.inflate(R.layout.dlg_selectlocation, container, false);

        listView = (ListView) view.findViewById(R.id.listView_dlg_selectLocation);
        adapter = new ItemAdapter();
        listView.setAdapter(adapter);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        listView.setOnItemClickListener(this);

        //填充地图数据
        List<Map> maps = MapFactory.getInstance().getMaps();
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup_dlg_selectLocation);
        radioGroup.setOnCheckedChangeListener(this);
        for (int i = 0, len = Math.min(radioGroup.getChildCount(), maps.size()); i < len; i++) {
            RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
            radioButton.setText(maps.get(i).getName());
        }

        radioGroup.check(radioGroup.getChildAt(0).getId());

        return view;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        //切换不同地图的节点信息
        int idx = group.indexOfChild(group.findViewById(checkedId));
        List<Location> locations = MapFactory.getInstance().getMaps().get(idx).getVisibleLocations();
        adapter.display(locations);
        int selectedIdx = locations.indexOf(mSelectedLocation);
        if (selectedIdx >= 0) {
            listView.setItemChecked(selectedIdx, true);
        } else {
            listView.clearChoices();
        }
    }

    private Callback<Location> callback;

    public SelectLocationDlgFrag setCallback(Callback<Location> callback) {
        this.callback = callback;
        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        dismiss();
        //当用户选中列表中的项目时将对应的节点通过回调传递给监听者
        SimpleCallback.call(adapter.getItem(position), callback);
    }
}
