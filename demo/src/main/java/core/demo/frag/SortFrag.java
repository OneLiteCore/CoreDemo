package core.demo.frag;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Map;

import core.demo.frag.sort.AbsSort;
import core.demo.frag.sort.SortView;
import core.mate.util.ClassUtil;
import core.mate.util.RandomUtil;
import core.mate.util.ToastUtil;

public class SortFrag extends Fragment {

    private Map<String, Class> sorts;

    private Handler handler = new Handler();

    private SortView sortView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        sortView = new SortView(getContext());

        sortView.display(getRandomIntArray());

        try {
            List<Class> classes = ClassUtil.getSubClassUnderPackage(AbsSort.class, "core.demo.frag.sort");
            sorts = new ArrayMap<>(classes.size());
            for (Class clz : classes) {
                sorts.put(clz.getSimpleName(), clz);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sortView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        try {
            menu.add("Reset");
            for (Map.Entry<String, Class> sort : sorts.entrySet()) {
                menu.add(sort.getKey());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String title = item.getTitle().toString();
        if ("Reset".equals(title)) {
            handler.removeCallbacksAndMessages(null);
            sortView.display(getRandomIntArray());
        } else {
            Class clz = sorts.get(title);
            startSort(clz);
        }
        return super.onOptionsItemSelected(item);
    }

    /*排序*/

    public static final int TIME = 16 * 1000;

    public void startSort(Class sort) {
        ToastUtil.toastShort("Sort " + sort.getSimpleName());
        try {
            AbsSort instance = (AbsSort) sort.newInstance();
            final List<int[]> frames = instance.sort(sortView.getValues());
            final int interval = frames.size() <= 8 ? 128 : (int) (TIME / (float) frames.size());
            handler.postDelayed(new Runnable() {

                int idx = 0;

                @Override
                public void run() {
                    if (idx == frames.size()) {
                        ToastUtil.toastShort("Sort Complete");
                        return;
                    }

                    int[] frame = frames.get(idx++);
                    sortView.display(frame);
                    handler.postDelayed(this, interval);
                }
            }, interval);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int[] getRandomIntArray() {
        return getRandomIntArray(SortView.SIZE, 0, SortView.MAX);
    }

    public static int[] getRandomIntArray(int size, int min, int max) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = RandomUtil.nextInt(min, max);
        }
        return array;
    }
}
