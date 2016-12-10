package core.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import core.demo.frag.EditFrag;
import core.demo.frag.OverWatchFrag;
import core.demo.frag.SortFrag;
import core.demo.frag.WaveFrag;

public class MainActivity extends AppCompatActivity {
    
    private static final Class[] FRAGS = {EditFrag.class, OverWatchFrag.class, SortFrag.class, WaveFrag.class};
    
    private class FragAdapter extends BaseAdapter {
        
        @Override
        public int getCount() {
            return FRAGS.length;
        }
        
        @Override
        public Class getItem(int position) {
            return FRAGS[position];
        }
        
        @Override
        public long getItemId(int position) {
            return position;
        }
        
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView = (TextView) convertView;
            if (textView == null) {
                textView = (TextView) getLayoutInflater().inflate(android.R.layout.simple_list_item_1, parent, false);
            }
            
            textView.setText(getItem(position).getSimpleName());
            
            return textView;
        }
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        
        ListView listView = (ListView) findViewById(R.id.listView_main);
        FragAdapter adapter = new FragAdapter();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> FragActivity.start(this, FRAGS[position]));
    }
}
