package core.demo.frag.dijkstra;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import core.demo.R;
import core.demo.frag.dijkstra.data.Location;
import core.mate.app.CoreFrag;
import core.mate.util.ToastUtil;

/**
 * @author DrkCore
 * @since 2017-02-26
 */
public class NavFrag extends CoreFrag {

    private Button mStartBtn;
    private Button mEndBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_nav, container, false);

        mStartBtn = (Button) view.findViewById(R.id.button_frag_nav_startLocation);
        mStartBtn.setOnClickListener(this::showLocations);
        mEndBtn = (Button) view.findViewById(R.id.button_frag_nav_endLocation);
        mEndBtn.setOnClickListener(this::showLocations);

        view.findViewById(R.id.button_frag_nav_go).setOnClickListener(v -> go());

        return view;
    }

    //点击后弹出选择楼层和节点的对话框
    private void showLocations(View view) {
        Button btn = (Button) view;
        //使用lambda表达式来简化代码
        new SelectLocationDlgFrag().setCallback(location -> {
            btn.setTag(location);//将选中的节点绑定到按钮上

            //显示选中的楼层和节点名称
            btn.setText(TextUtils.concat("楼层：" + location.getMap().getName(), "\n",
                    btn == mStartBtn ? "起点：" : "终点：", location.getName()));

        }).setSelectedLocation((Location) view.getTag()).show(this);
    }

    public interface OnNavListener {

        void onNav(Location start, Location end);
    }

    private OnNavListener mOnNavListener;

    public NavFrag setOnNavListener(OnNavListener onNavListener) {
        mOnNavListener = onNavListener;
        return this;
    }

    @SuppressWarnings("unchecked")
    private void go() {
        Location startLoc = (Location) mStartBtn.getTag();
        Location endLoc = (Location) mEndBtn.getTag();
        if (startLoc == null || endLoc == null) {
            ToastUtil.show("请补全起止点信息");
        } else if (startLoc == endLoc) {
            ToastUtil.show("起止点一致，无需导航");
        } else if (mOnNavListener != null) {
            mOnNavListener.onNav(startLoc,endLoc);
        }
    }
}
