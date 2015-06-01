package pl.ppteam.ahp.myride;

import com.activeandroid.ActiveAndroid;

/**
 * Created by Łukasz on 2015-06-01.
 */
public class MyRideApplication extends com.activeandroid.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }
}
