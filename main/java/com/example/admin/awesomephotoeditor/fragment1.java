package com.example.admin.awesomephotoeditor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Admin on 5/24/2017.
 */

public class fragment1 extends android.app.Fragment implements View.OnClickListener{


    Uri imageUri;
    View view;
    Button camerabtn,gallerybtn;
    Activity ac;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int PICK_IMAGE = 100;
    private FragmentActivity fragmentActivity;

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
    public void onAttach(Activity activity) {
        fragmentActivity =(FragmentActivity) activity;
        ac = activity;
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        view = inflater.inflate(R.layout.home, container, false);

      //  viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        camerabtn = (Button)view.findViewById(R.id.btn_camera);
        gallerybtn = (Button)view.findViewById(R.id.btn_gallery);
        camerabtn.setOnClickListener(this);
        gallerybtn.setOnClickListener(this);
        if(!hasCamera()){
            camerabtn.setEnabled(false);
        }
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public void openGallery(){
        Intent intentGallery = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intentGallery,PICK_IMAGE);
    }

    public void launchCamera(){
        Intent intent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
        //take a picture
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    //if you want to return image

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
       if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
           Bundle extras = data.getExtras();
           extras.putInt("value",1);
           ((MainActivity)getActivity()).attachFragmentCameraDisplay(extras);
       }
       else if(requestCode == PICK_IMAGE && resultCode == RESULT_OK)
       {
           imageUri = data.getData();
           Bundle extras = new Bundle();
           extras.putParcelable("image",imageUri);
           extras.putInt("value",2);
           ((MainActivity)getActivity()).attachFragmentCameraDisplay(extras);

       }
    }



    public boolean hasCamera(){
        //check if user has a camera
        return fragmentActivity.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_camera:
            {
                //show a fragment
                launchCamera();
                break;
            }
            case R.id.btn_gallery:
            {
                openGallery();
                break;
            }
            default: {
                break;
            }
        }

    }
}
