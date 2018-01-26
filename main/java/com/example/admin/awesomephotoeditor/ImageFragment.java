package com.example.admin.awesomephotoeditor;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Admin on 5/25/2017.
 */

public class ImageFragment extends Fragment {

    ImageView imageView;
    int id;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.image_fragment, container, false);
        imageView = (ImageView)v.findViewById(R.id.imageView);
        return v;
    }

    public void changeImage(int id)
    {
        switch (id) {
            case 0:
                imageView.setImageResource(R.drawable.imagee1);
                break;
            case 1:
                imageView.setImageResource(R.drawable.imagee2);
                break;
            case 2:
                imageView.setImageResource(R.drawable.imagee3);
                break;
            default:
                imageView.setImageResource(R.drawable.imagee4);
        }

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
