package core.demo;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import core.demo.frag.FragFactory;
import core.mate.adapter.SimpleAdapter;
import core.mate.adapter.SimpleViewHolder;
import core.mate.app.CoreActivity;

public class MainActivity extends CoreActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		ListView listView = (ListView) findViewById(R.id.listView_main);
		List<Class> frags = FragFactory.getFrags();
		listView.setAdapter(new SimpleAdapter<Class>(android.R.layout.simple_list_item_1, frags) {
			@Override
			protected void bindViewData(SimpleViewHolder<Class> holder, int position, Class data, int viewType) {
				TextView textView = holder.getCastView();
				textView.setText(data.getSimpleName());
			}
		});

		//点击进入Activity
		listView.setOnItemClickListener((parent, view, position, id) -> {
			SimpleAdapter<Class> adapter = (SimpleAdapter<Class>) parent.getAdapter();
			Class clz = adapter.getItem(position);
			FragActivity.start(this, clz);
		});
	}
}
