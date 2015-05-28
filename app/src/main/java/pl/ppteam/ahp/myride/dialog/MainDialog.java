package pl.ppteam.ahp.myride.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;

/**
 * Created by Łukasz on 2015-05-28.
 */
public class MainDialog extends Dialog {

    protected int code;
    protected IDialogListener listener;


    public MainDialog(Activity activity, int code) {
        super(activity);
        this.code = code;
    }

    public IDialogListener getListener() {
        return listener;
    }

    public void setListener(IDialogListener listener) {
        this.listener = listener;
    }

}
