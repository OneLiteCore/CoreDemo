package core.demo.frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import core.demo.frag.overwatch.OverWatchView;

/**
 * 鎏金哦开哦酷嘞！！！
 *
 * @author DrkCore
 * @since 2016-09-22
 */
public class OverWatchFrag extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return new OverWatchView(getContext());
    }

}
