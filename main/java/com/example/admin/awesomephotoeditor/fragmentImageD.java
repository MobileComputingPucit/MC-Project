package com.example.admin.awesomephotoeditor;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.provider.MediaStore;

import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Umer on 23/07/2017.
 */

public class fragmentImageD extends android.app.Fragment implements View.OnClickListener {
    View view;
    private FragmentActivity fragmentActivity;
    Bundle extras;
    ImageView imageV;
    Bitmap bitmap;
    Drawable buckDrawable;
    Activity activity1;
    ImageView imageView1,imageView2,imageView3,imageView4,imageView5,imageView6;
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
        super.onAttach(activity);
        this.activity1 = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        extras = getArguments();
        view = inflater.inflate(R.layout.fragmentimage, container, false);
        //  viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        imageV = (ImageView) view.findViewById(R.id.imageView);
        imageView1 = (ImageView) view.findViewById(R.id.horizontalViewImage1);
        imageView2 = (ImageView) view.findViewById(R.id.horizontalViewImage2);
        imageView3 = (ImageView) view.findViewById(R.id.horizontalViewImage3);
        imageView4 = (ImageView) view.findViewById(R.id.horizontalViewImage4);
        imageView5 = (ImageView) view.findViewById(R.id.horizontalViewImage5);
        imageView6 = (ImageView) view.findViewById(R.id.horizontalViewImage6);

        imageView1.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        imageView3.setOnClickListener(this);
        imageView4.setOnClickListener(this);
        imageView5.setOnClickListener(this);
        imageView6.setOnClickListener(this);

        return view;
    }

    public void invertImage(){

        buckDrawable = imageV.getDrawable();
        bitmap = ((BitmapDrawable) buckDrawable).getBitmap();
        Bitmap newPhoto = invertData(bitmap);
        imageV.setImageBitmap(newPhoto);

    }

    public static Bitmap invertData(Bitmap original){
        Bitmap finalImage = Bitmap.createBitmap(original.getWidth(),original.getHeight(),original.getConfig());
        int A, R, G, B;
        int pixelColor;
        int height = original.getHeight();
        int width = original.getWidth();
        for(int i = 0 ; i < height ; i++)
        {
            for(int j = 0; j < width ; j++){
                pixelColor = original.getPixel(j,i);
                A = Color.alpha(pixelColor);
                R = 255 - Color.red(pixelColor);
                G = 255 - Color.green(pixelColor);
                B = 255 - Color.blue(pixelColor);
                finalImage.setPixel(j,i,Color.argb(A,R,G,B));

            }
        }
        return finalImage;
    }

    public void saveImage(){
        Toast.makeText(activity1,"Image saved!",Toast.LENGTH_LONG).show();
        String filePath = Environment.getExternalStorageDirectory().toString();
       /* ContentValues values = new ContentValues();



        values.put(MediaStore.Images.Media.DATE_TAKEN,System.currentTimeMillis());
        values.put(MediaStore.Images.Media.MIME_TYPE,"imagee/jpeg");
        values.put(MediaStore.MediaColumns.DATA, filePath);
        MediaStore.Images.Media.insertImage(activity1.getContentResolver(),MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);*/

        imageV.setDrawingCacheEnabled(true);
        Bitmap b = imageV.getDrawingCache();
        MediaStore.Images.Media.insertImage(getActivity().getContentResolver(),b,filePath,"heyhey");

       // MediaStore.Images.Media.insertImage(fragmentActivity.getContentResolver(),((BitmapDrawable) imageV.getDrawable()).getBitmap(),"title1","sugar and spice");
    }

    public void sendData(Bundle bun){
        this.extras = bun;
        if(extras.getInt("value") == 1) {
            Bitmap photo = (Bitmap) extras.get("data");
            imageV.setImageBitmap(photo);
        }
        else if(extras.getInt("value") == 2)
        {
            Uri imageUri = extras.getParcelable("image");
            imageV.setImageURI(imageUri);

        }
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(extras.getInt("value") == 1) {
            Bitmap photo = (Bitmap) extras.get("data");
            imageV.setImageBitmap(photo);
        }
        else if(extras.getInt("value") == 2)
        {
            Uri imageUri = extras.getParcelable("image");
            imageV.setImageURI(imageUri);
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.horizontalViewImage1:{
                invertImage();
                break;

            }
            case R.id.horizontalViewImage2:{
                break;
            }
            case R.id.horizontalViewImage3:{
                break;
            }
            case R.id.horizontalViewImage4:{
                break;
            }
            case R.id.horizontalViewImage5:{
                break;
            }
            case R.id.horizontalViewImage6:{
                break;
            }
            default:
            {
                break;
            }

        }
    }

}
