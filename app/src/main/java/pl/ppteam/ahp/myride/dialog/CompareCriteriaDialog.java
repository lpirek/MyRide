package pl.ppteam.ahp.myride.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import pl.ppteam.ahp.myride.R;
import pl.ppteam.ahp.myride.common.CriteriaCompare;
import pl.ppteam.ahp.myride.common.Direction;
import pl.ppteam.ahp.myride.common.Wage;

/**
 * Created by Łukasz on 2015-05-28.
 */
public class CompareCriteriaDialog extends MainDialog {

    private CriteriaCompare compare;
    private ViewHolder holder;

    public CompareCriteriaDialog(Activity activity, int code, CriteriaCompare compare) {
        super(activity, code);
        this.compare = compare;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Konstruowanie dialogu
        setContentView(R.layout.dialog_compare_criteria);

        holder = new ViewHolder();

        holder.criterium1 = (TextView) this.findViewById(R.id.dialog_item_criterium1);
        holder.criterium2 = (TextView) this.findViewById(R.id.dialog_item_criterium2);
        holder.sbr = (SeekBar) this.findViewById(R.id.dialog_sbr);
        holder.btn_confirm = (Button) this.findViewById(R.id.dialog_btn_confirm);

        holder.criterium1.setText(compare.getCriterium1().getName());
        holder.criterium2.setText(compare.getCriterium2().getName());
        holder.sbr.setProgress(getValue());

        holder.btn_confirm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (listener != null) {
                    setValue();
                    listener.onPositiveClicked(code);
                }
                CompareCriteriaDialog.this.dismiss();
            }

        });


    }

    private int getValue() {
        switch (compare.getWage()) {
            case W3:
                return compare.getDirection() == Direction.Rightside ? 5 : 3;
            case W5:
                return compare.getDirection() == Direction.Rightside ? 6 : 2;
            case W7:
                return compare.getDirection() == Direction.Rightside ? 7 : 1;
            case W9:
                return compare.getDirection() == Direction.Rightside ? 8 : 0;
            default:
                return 4;
        }
    }

    private void setValue() {
        int value = holder.sbr.getProgress();

        Wage wage = null;
        Direction direction = null;

        switch(value) {
            case 0:
                wage = Wage.W9;
                direction = Direction.Leftside;
                break;
            case 1:
                wage = Wage.W7;
                direction = Direction.Leftside;
                break;
            case 2:
                wage = Wage.W5;
                direction = Direction.Leftside;
                break;
            case 3:
                wage = Wage.W3;
                direction = Direction.Leftside;
                break;
            case 5:
                wage = Wage.W3;
                direction = Direction.Rightside;
                break;
            case 6:
                wage = Wage.W5;
                direction = Direction.Rightside;
                break;
            case 7:
                wage = Wage.W7;
                direction = Direction.Rightside;
                break;
            case 8:
                wage = Wage.W9;
                direction = Direction.Rightside;
                break;
            default:
                wage = Wage.W1;
                direction = Direction.Equals;
        }

        compare.setWage(wage);
        compare.setDirection(direction);
    }

    class ViewHolder {
        TextView criterium1;
        TextView criterium2;
        SeekBar sbr;
        Button btn_confirm;
    }

}

