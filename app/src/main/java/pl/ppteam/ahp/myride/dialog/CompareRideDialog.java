package pl.ppteam.ahp.myride.dialog;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import pl.ppteam.ahp.myride.R;
import pl.ppteam.ahp.myride.common.Criterium;
import pl.ppteam.ahp.myride.common.RideCompare;
import pl.ppteam.ahp.myride.common.Direction;
import pl.ppteam.ahp.myride.common.Wage;

/**
 * Created by Piotr Płaczek on 2015-05-29.
 */
public class CompareRideDialog extends MainDialog {

    private Criterium criterium;
    private RideCompare compare;
    private ViewHolder holder;

    public CompareRideDialog(Activity activity, int code, Criterium criterium, RideCompare compare) {
        super(activity, code);
        this.criterium = criterium;
        this.compare = compare;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Konstruowanie dialogu
        setContentView(R.layout.dialog_compare_ride);

        holder = new ViewHolder();

        holder.sbr = (SeekBar) this.findViewById(R.id.dialog_ride_sbr);
        holder.img = (ImageView) this.findViewById(R.id.dialog_ride_item_img);
        holder.btn_confirm = (Button) this.findViewById(R.id.dialog_ride_btn_confirm);
        holder.img_dialog_ride1 = (ImageView) this.findViewById(R.id.img_dialog_ride1);
        holder.img_dialog_ride2 = (ImageView) this.findViewById(R.id.img_dialog_ride2);
        holder.text = (TextView) findViewById(R.id.dialog_item_text);
        holder.from_ride1 = (TextView) this.findViewById(R.id.item_dialog_ride1_from);
        holder.to_ride1 = (TextView) this.findViewById(R.id.item_dialog_ride1_to);
        holder.from_ride2 = (TextView) this.findViewById(R.id.item_dialog_ride2_from);
        holder.to_ride2 = (TextView) this.findViewById(R.id.item_dialog_ride2_to);
        holder.startDate_ride1 = (TextView) this.findViewById(R.id.item_dialog_startDate_ride1);
        holder.startDate_ride2 = (TextView) this.findViewById(R.id.item_dialog_startDate_ride2);
        holder.title1 = (TextView) this.findViewById(R.id.item_dialog_feature_title1);
        holder.title2 = (TextView) this.findViewById(R.id.item_dialog_feature_title2);
        holder.value1 = (TextView) this.findViewById(R.id.item_dialog_feature_value1);
        holder.value2 = (TextView) this.findViewById(R.id.item_dialog_feature_value2);

        holder.img_dialog_ride1.setImageResource(compare.getRide1().getTransportType().getImage());
        holder.img_dialog_ride2.setImageResource(compare.getRide2().getTransportType().getImage());

        holder.from_ride1.setText(compare.getRide1().getFromCity().getName());
        holder.to_ride1.setText(compare.getRide1().getToCity().getName());

        holder.from_ride2.setText(compare.getRide2().getFromCity().getName());
        holder.to_ride2.setText(compare.getRide2().getToCity().getName());

        holder.startDate_ride1.setText(compare.getRide1().getFormatStartDate());
        holder.startDate_ride2.setText(compare.getRide2().getFormatStartDate());

        setFeatures();
        setProgress();
        setImage();
        setText();

        holder.sbr.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setImage();
                setText();
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


    private void setFeatures() {

        switch(criterium.getSymbol()) {
            case Criterium.priceSymbol:
                setFeatures("Koszt:",
                        new DecimalFormat("0.00").format(compare.getRide1().getPrice()) + " zł",
                        new DecimalFormat("0.00").format(compare.getRide2().getPrice()) + " zł");
                break;
            case Criterium.arriveSymbol:
                setFeatures("Przyjazd:", compare.getRide1().getFormatEndDate(), compare.getRide2().getFormatEndDate());
                break;
            case Criterium.comfortSymbol:
                setFeatures("", "", "");
                break;
            case Criterium.luggageSymbol:
                setFeatures("", "", "");
                break;
            case Criterium.startSymbol:
                setFeatures("", "", "");
                break;
            case Criterium.timeSymbol:
                setFeatures("Długość podróży:", compare.getRide1().getFormatRideTime(), compare.getRide2().getFormatRideTime());
                break;
            case Criterium.toiletSymbol:
                setFeatures("", "", "");
                break;
            default:
                setFeatures("", "", "");

        }
    }

    private void setFeatures(String title, String value1, String value2) {
        holder.title1.setText(title);
        holder.title2.setText(title);
        holder.value1.setText(value1);
        holder.value2.setText(value2);
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

    private void setText() {
        int value = holder.sbr.getProgress();
        String text = "";

        switch(value) {
            case 0:
                text = Wage.W9.getDescription();
                break;
            case 1:
                text = Wage.W7.getDescription();
                break;
            case 2:
                text = Wage.W5.getDescription();
                break;
            case 3:
                text = Wage.W3.getDescription();
                break;
            case 5:
                text = Wage.W3.getDescription();
                break;
            case 6:
                text = Wage.W5.getDescription();
                break;
            case 7:
                text = Wage.W7.getDescription();
                break;
            case 8:
                text = Wage.W9.getDescription();
                break;
            default:
                text = Wage.W1.getDescription();
        }

        holder.text.setText(text);
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

        TextView text;

        ImageView img_dialog_ride1;
        ImageView img_dialog_ride2;

        TextView from_ride1;
        TextView to_ride1;

        TextView from_ride2;
        TextView to_ride2;

        TextView startDate_ride1;
        TextView startDate_ride2;

        TextView value1;
        TextView value2;

        TextView title1;
        TextView title2;
    }

}
