package com.yuzhua.theloanapplication.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.yuzhua.theloanapplication.R;
import com.yuzhua.theloanapplication.fragments.BlankFragment;
import com.yuzhua.theloanapplication.widgets.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.main_viewpager)
    NoScrollViewPager mainViewpager;
    @BindView(R.id.rg_main)
    RadioGroup rgMain;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setData();

    }

    private void setData() {
        fragments = new ArrayList<>();
        fragments.add(new BlankFragment());
        fragments.add(new BlankFragment());
        fragments.add(new BlankFragment());
        fragments.add(new BlankFragment());
        mainViewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        mainViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                rgMain.check(rgMain.getChildAt(position).getId());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mainViewpager.setOffscreenPageLimit(fragments.size());

        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_index:
                        mainViewpager.setCurrentItem(0);
                        break;
                    case R.id.rb_loan:
                        mainViewpager.setCurrentItem(1);
                        break;
                    case R.id.rb_message:
                        mainViewpager.setCurrentItem(2);
                        break;
                    case R.id.rb_my:
                        mainViewpager.setCurrentItem(3);
                        break;
                }
            }
        });


    }

}
