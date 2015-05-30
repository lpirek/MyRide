package pl.ppteam.ahp.myride.dialog;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import pl.ppteam.ahp.myride.R;
import pl.ppteam.ahp.myride.common.RideCompare;
import pl.ppteam.ahp.myride.common.Direction;
import pl.ppteam.ahp.myride.common.Wage;

/**
 * Created by Piotr Płaczek on 2015-05-29.
 */
public class CompareRideDialog extends MainDialog {

    private RideCompare compare;
    private ViewHolder holder;

    public CompareRideDialog(Activity activity, int code, RideCompare compare) {
        super(activity, code);
        this.compare = compare;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Konstruowanie dialogu
        setContentView(R.layout.dialog_compare_ride);

        holder = new ViewHolder();

        holder.sbr = (SeekBar) this.findViewById(R.id.dialog_ride_sbr);
        holder.img = (ImageView) this.findViewById(R.id.dialog_ride_item_img);
        holder.btn_confirm = (Button) this.findViewById(R.id.dialog_ride_btn_confirm);
        holder.img_dialog_ride1 = (ImageView) this.findViewById(R.id.img_dialog_ride1);
        holder.img_dialog_ride2 = (ImageView) this.findViewById(R.id.img_dialog_ride2);
        holder.from_ride1 = (TextView) this.findViewById(R.id.item_dialog_ride1_from);
        holder.to_ride1 = (TextView) this.findViewById(R.id.item_dialog_ride1_to);
        holder.from_ride2 = (TextView) this.findViewById(R.id.item_dialog_ride2_from);
        holder.to_ride2 = (TextView) this.findViewById(R.id.item_dialog_ride2_to);
        holder.startDate_ride1 = (TextView) this.findViewById(R.id.item_dialog_startDate_ride1);
        holder.startDate_ride2 = (TextView) this.findViewById(R.id.item_dialog_startDate_ride2);
        holder.endDate_ride1 = (TextView) this.findViewById(R.id.item_dialog_endDate_ride1);
        holder.endDate_ride2 = (TextView) this.findViewById(R.id.item_dialog_endDate_ride2);
        holder.item_line_ride1 = (TextView) this.findViewById(R.id.item_dialog_line_ride1);
        holder.item_line_ride2 = (TextView) this.findViewById(R.id.item_dialog_line_ride2);


        holder.img_dialog_ride1.setImageResource(compare.getRide1().getTransportType().getImage());
        holder.img_dialog_ride2.setImageResource(compare.getRide2().getTransportType().getImage());

        holder.from_ride1.setText(compare.getRide1().getFromCity().getName());
        holder.to_ride1.setText(compare.getRide1().getToCity().getName());

        holder.from_ride2.setText(compare.getRide2().getFromCity().getName());
        holder.to_ride2.setText(compare.getRide2().getToCity().getName());

        holder.startDate_ride1.setText(new SimpleDateFormat("yyyy-MM-dd").format(compare.getRide1().getStartDate()));
        holder.startDate_ride2.setText(new SimpleDateFormat("yyyy-MM-dd").format(compare.getRide2().getStartDate()));

        if (compare.getRide1().getEndDate() == null)
        {
            holder.endDate_ride1.setText("");
            holder.endDate_ride1.setVisibility(View.GONE);
            holder.item_line_ride1.setVisibility(View.GONE);
        }else {
            holder.endDate_ride1.setText(new SimpleDateFormat("yyyy-MM-dd").format(compare.getRide1().getEndDate()));
        }

        if(compare.getRide2().getEndDate() == null)
        {
            holder.endDate_ride2.setText("");
            holder.endDate_ride2.setVisibility(View.GONE);
            holder.item_line_ride2.setVisibility(View.GONE);
        }
        else {
            holder.endDate_ride2.setText(new SimpleDateFormat("yyyy-MM-dd").format(compare.getRide2().getEndDate()));
        }

        setProgress();
        setImage();

        holder.sbr.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setImage();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });

        holder.btn_confirm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (listener != null) {
                    setValue();
                    listener.onPositiveClicked(code);
                }
                CompareRideDialog.this.dismiss();
            }

        });


    }

    private void setImage() {
        int value = holder.sbr.getProgress();
        int resourceId;

        switch(value) {
            case 0:
                resourceId = R.drawable.nine_left;
                break;
            case 1:
                resourceId = R.drawable.seven_left;
                break;
            case 2:
                resourceId = R.drawable.five_left;
                break;
            case 3:
                resourceId = R.drawable.three_left;
                break;
            case 5:
                resourceId = R.drawable.three_right;
                break;
            case 6:
                resourceId = R.drawable.five_right;
                break;
            case 7:
                resourceId = R.drawable.seven_right;
                break;
            case 8:
                resourceId = R.drawable.nine_right;
                break;
            default:
                resourceId = R.drawable.equal;
        }

        holder.img.setImageResource(resourceId);
    }

    private void setProgress() {
        int progress;

        switch (compare.getWage()) {
            case W3:
                progress = compare.getDirection() == Direction.Rightside ? 5 : 3;
                break;
            case W5:
                progress = compare.getDirection() == Direction.Rightside ? 6 : 2;
                break;
            case W7:
                progress = compare.getDirection() == Direction.Rightside ? 7 : 1;
                break;
            case W9:
                progress = compare.getDirection() == Direction.Rightside ? 8 : 0;
                break;
            default:
                progress = 4;
        }

        holder.sbr.setProgress(progress);
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
        SeekBar sbr;
        ImageView img;
        Button btn_confirm;

        ImageView img_dialog_ride1;
        ImageView img_dialog_ride2;

        TextView from_ride1;
        TextView to_ride1;

        TextView from_ride2;
        TextView to_ride2;

        TextView startDate_ride1;
        TextView startDate_ride2;

        TextView endDate_ride1;
        TextView endDate_ride2;

        TextView item_line_ride1;
        TextView item_line_ride2;
    }

}
