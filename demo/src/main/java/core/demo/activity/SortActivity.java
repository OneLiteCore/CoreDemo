package core.demo.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

import core.demo.R;
import core.demo.activity.sort.AbsSort;
import core.demo.activity.sort.SortView;
import core.demo.util.ClassUtil;
import core.demo.util.RandomUtil;

public class SortActivity extends AppCompatActivity {

    private Map<String, Class> sorts;
    private SortView sortView;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout);
        layout.addView(sortView = new SortView(this));

        sortView.display(getRandomIntArray());

        try {
            List<Class> classes = ClassUtil.getSubClassUnderPackage(AbsSort.class, "core.demo.activity.sort");
            sorts = new ArrayMap<>(classes.size());
            for (Class clz : classes) {
                sorts.put(clz.getSimpleName(), clz);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        try {
            menu.add("Reset");
            for (Map.Entry<String, Class> sort : sorts.entrySet()) {
                menu.add(sort.getKey());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String title = item.getTitle().toString();
        if ("Reset".equals(title)) {
            sortView.display(getRandomIntArray());
        } else {
            Class clz = sorts.get(title);
            startSort(clz);
        }
        return super.onOptionsItemSelected(item);
    }

    /*排序*/

    public static final int INTERVAL = 256;

    public void startSort(Class sort) {
        Toast.makeText(this, "Sort " + sort.getSimpleName(), Toast.LENGTH_LONG).show();
        try {
            AbsSort instance = (AbsSort) sort.newInstance();
            final List<int[]> frames = instance.sort(sortView.getValues());
            handler.postDelayed(new Runnable() {

                int idx = 0;

                @Override
                public void run() {
                    if (idx == frames.size()) {
                        return;
                    }

                    int[] frame = frames.get(idx++);
                    sortView.display(frame);
                    handler.postDelayed(this, INTERVAL);
                }
            }, INTERVAL);
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
