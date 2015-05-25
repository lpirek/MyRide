package pl.ppteam.ahp.myride.tool;

import android.app.Activity;
import android.util.Log;

import java.text.MessageFormat;

/**
 * Created by £ukasz on 2015-05-23.
 */
public class Logger {

    private static final String TAG = "PPTEAM";

    public static void info(String msg) {
        Log.i(TAG, msg);
    }

    public static void info(String msg, Activity source) {
        Log.i(TAG, MessageFormat.format("{0} -> {1}", msg, source.getClass().getSimpleName()));
    }

    public static void error(String msg) {
        Log.e(TAG, msg);
    }

    public static void error(String msg, Activity source) {
        Log.e(TAG, MessageFormat.format("{0} -> {1}", msg, source.getClass().getSimpleName()));
    }

    public static void error(Exception e) {
        Log.e(TAG, e.getMessage());
    }

    public static void error(Exception e, Activity source) {
        Log.e(TAG, MessageFormat.format("{0} -> {1}", e.getMessage(), source.getClass().getSimpleName()));
    }

    public static void debug(String msg) {
        Log.d(TAG, msg);
    }

    public static void debug(String msg, Activity source) {
        Log.d(TAG, MessageFormat.format("{0} -> {1}", msg, source.getClass().getSimpleName()));
    }

}
