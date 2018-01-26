package com.example.admin.awesomephotoeditor;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.ImageView;

/**
 * Created by Admin on 5/22/2017.
 */

public class ImageFragmentAdaptor extends FragmentStatePagerAdapter {

    public ImageFragmentAdaptor(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        ImageView imageView;
        int imgResId;

        ImageFragment fragmentt= new ImageFragment();
        switch (position) {
            case 0:
                fragmentt.changeImage(0);
                break;
            case 1:
                fragmentt.changeImage(1);
                break;
            case 2:
                fragmentt.changeImage(2);
                break;
            default:
                fragmentt.changeImage(3);
        }
        return fragmentt;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
