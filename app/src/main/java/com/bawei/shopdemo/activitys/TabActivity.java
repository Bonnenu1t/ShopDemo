package com.bawei.shopdemo.activitys;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.bawei.shopdemo.R;
import com.bawei.shopdemo.base.IActivity;
import com.bawei.shopdemo.fragment.FirstFragment;
import com.bawei.shopdemo.fragment.FourthFragment;
import com.bawei.shopdemo.fragment.LaMaFragment;
import com.bawei.shopdemo.fragment.SecondFragment;
import com.bawei.shopdemo.fragment.ThirdFragment;
import com.bawei.shopdemo.widget.ButtonLayout;

import java.util.ArrayList;
import java.util.List;

public class TabActivity extends IActivity implements ButtonLayout.OnSelectListener {
    private ButtonLayout buttonLayout;
    private FragmentManager fragmentManager;

    private List<Fragment> fragments = new ArrayList<Fragment>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        fragmentManager = getSupportFragmentManager();

        createFragment(savedInstanceState);



        buttonLayout = (ButtonLayout) findViewById(R.id.button_layout);
//        LinearLayout buttonLayout = (LinearLayout) findViewById(R.id.button_layout);
        buttonLayout.setOnSelectListener(this);


        switchFragment(0);

    }
    public void createFragment(Bundle savedInstanceState){

        FirstFragment firstFragment = (FirstFragment) fragmentManager.findFragmentByTag("FirstFragment");
        SecondFragment secondFragment = (SecondFragment) fragmentManager.findFragmentByTag("SecondFragment");
        LaMaFragment laMaFragment = (LaMaFragment) fragmentManager.findFragmentByTag("LaMaFragment");
        ThirdFragment thirdFragment = (ThirdFragment) fragmentManager.findFragmentByTag("ThirdFragment");
        FourthFragment fourthFragment = (FourthFragment) fragmentManager.findFragmentByTag("FourthFragment");



        if(firstFragment == null){
            firstFragment = new FirstFragment();
        }

        if(secondFragment == null){
            secondFragment = new SecondFragment();
        }

        if (laMaFragment == null){
            laMaFragment = new LaMaFragment();
        }
        if(thirdFragment == null){
            thirdFragment = new ThirdFragment();
        }
        if(fourthFragment == null){
            fourthFragment = new FourthFragment();
        }


        fragments.add(firstFragment);
        fragments.add(secondFragment);
        fragments.add(laMaFragment);
        fragments.add(thirdFragment);
        fragments.add(fourthFragment);


    }


    public void switchFragment(int pos){

        FragmentTransaction transaction =  fragmentManager.beginTransaction() ;


        if(!fragments.get(pos).isAdded()){

            transaction.add(R.id.container,fragments.get(pos),fragments.get(pos).getClass().getSimpleName());
        }

        for(int i=0;i<fragments.size();i++){

            if(i == pos){
                transaction.show(fragments.get(pos));
            }else {
                transaction.hide(fragments.get(i));
            }

        }
        transaction.commit();


    }


    /**
     * 底部导航 点击 回调
     * @param index
     */
    @Override
    public void onSelect(int index) {
        switchFragment(index);
    }
}


