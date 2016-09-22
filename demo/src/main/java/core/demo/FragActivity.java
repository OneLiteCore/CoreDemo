package core.demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

import core.mate.app.CoreActivity;

/**
 * @author DrkCore
 * @since 2016-09-22
 */
public class FragActivity extends CoreActivity {

    public static void start(Context context, Class frag) {
        context.startActivity(new Intent(context, FragActivity.class).putExtra(Fragment.class.getCanonicalName(), frag));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Class clz = (Class) getIntent().getSerializableExtra(Fragment.class.getCanonicalName());
        getFragHelper().findOrAddFragment(R.id.frameLayout_frag_container, clz);
    }
}
