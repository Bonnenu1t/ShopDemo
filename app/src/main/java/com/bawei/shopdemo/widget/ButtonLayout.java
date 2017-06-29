package com.bawei.shopdemo.widget;

import android.content.Context;
import android.support.annotation.IdRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bawei.shopdemo.R;

/**
 * Created by Bonnenu1t丶 on 2017/6/20.
 */

public class ButtonLayout extends LinearLayout{
    public ButtonLayout(Context context) {
        this(context,null);
    }

    public ButtonLayout(Context context,  AttributeSet attrs) {
        this(context, attrs,0);

    }

    public ButtonLayout(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.tab_layout,this);
        RadioGroup radioGroup = (RadioGroup)  view.findViewById(R.id.tab_radiogroup);
        RadioButton radioButtonFirst = (RadioButton) view.findViewById(R.id.radiobutton_home);
        RadioButton radioButtonSecond = (RadioButton) view.findViewById(R.id.radiobutton_discover);
        RadioButton radioButtonMy = (RadioButton) view.findViewById(R.id.radiobutton_lama);
        RadioButton radioButtonThird = (RadioButton) view.findViewById(R.id.radiobutton_feed);
        RadioButton radioButtonFourth = (RadioButton) view.findViewById(R.id.radiobutton_me);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                switch (checkedId) {
                    case R.id.radiobutton_home:
                        setListener(0);
                        break;

                    case R.id.radiobutton_discover:
                        setListener(1);
                        break;

                    case R.id.radiobutton_lama:
                        setListener(2);
                        break;

                    case R.id.radiobutton_feed:
                        setListener(3);
                        break;

                    case R.id.radiobutton_me:
                        setListener(4);
                        break;
                }
            }
        });
    }

    private void setListener(int index) {
        if (listener != null){
            listener.onSelect(index);
        }

    }

    public interface OnSelectListener{
            void onSelect(int index);
    }

    public OnSelectListener listener;

    public void setOnSelectListener(OnSelectListener listener){
        this.listener = listener;
    }
}
