package core.demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import core.demo.R;
import core.mate.util.ClassUtil;

public class MainActivity extends AppCompatActivity {

    private static class Adapter extends BaseAdapter {

        private final List<Class> acts;

        public Adapter(List<Class> acts) {
            this.acts = acts;
        }

        @Override
        public int getCount() {
            return acts.size();
        }

        @Override
        public Class getItem(int position) {
            return acts.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        private LayoutInflater inflater;

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                if (inflater == null) {
                    inflater = LayoutInflater.from(parent.getContext());
                }
                convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            }
            TextView textView = (TextView) convertView;
            textView.setText(getItem(position).getSimpleName());
            return textView;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout);
        ListView listView = new ListView(this);
        layout.addView(listView);

        try {//动态获取activity包下的所有Activity
            List<Class> acts = ClassUtil.getSubClassUnderPackage(AppCompatActivity.class, getClass().getPackage().toString() );
            acts.remove(MainActivity.class);
            listView.setAdapter(new Adapter(acts));
        } catch (Throwable e) {
            e.printStackTrace();
        }

        //点击进入Activity
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Adapter adapter = (Adapter) parent.getAdapter();
                Class act = adapter.getItem(position);
                startActivity(new Intent(MainActivity.this, act));
            }
        });
    }
}
