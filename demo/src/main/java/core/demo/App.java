package core.demo;

import android.app.Application;

import core.mate.Core;

public class App extends Application {

    private static App instance;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        Core.getInstance().init(this);
        Core.getInstance().setDevModeEnable();
    }
}
