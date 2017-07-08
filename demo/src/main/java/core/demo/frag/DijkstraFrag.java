package core.demo.frag;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import core.demo.R;
import core.demo.frag.dijkstra.MapFrag;
import core.demo.frag.dijkstra.NavFrag;

import static core.mate.app.CoreActivity.FULL_SCREEN_UI_OPTION;

/**
 * @author DrkCore
 * @since 2017-07-08
 */
public class DijkstraFrag extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity activity = getActivity();
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        activity.getWindow().getDecorView().setSystemUiVisibility(FULL_SCREEN_UI_OPTION);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_dijkstra, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentManager fragMgr = getChildFragmentManager();
        NavFrag navFrag = (NavFrag) fragMgr.findFragmentById(R.id.fragment_dijkstra_nav);
        MapFrag mapFrag = (MapFrag) fragMgr.findFragmentById(R.id.fragment_dijkstra_map);

        navFrag.setOnNavListener(mapFrag);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View toolbar = getActivity().findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setVisibility(View.GONE);
        }
    }
}
